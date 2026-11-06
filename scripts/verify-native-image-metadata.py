import argparse
import json
import re
import subprocess
from collections import Counter
from pathlib import Path
from zipfile import ZipFile


parser = argparse.ArgumentParser()
parser.add_argument("graphics_jar", type=Path)
parser.add_argument("--metadata", type=Path)
args = parser.parse_args()

root = Path(__file__).resolve().parents[1]
module = (root / "build.gradle.kts").read_text(encoding="utf-8")
match = re.search(r'javafx\s*\{\s*version\s*=\s*"([^"]+)"', module)
if match is None:
    parser.error("build.gradle.kts does not declare the OpenJFX version")
expected_name = f"javafx-graphics-{match.group(1)}-win.jar"
if args.graphics_jar.name != expected_name:
    parser.error(f"expected {expected_name}, got {args.graphics_jar.name}")

metadata_path = args.metadata or root / "ui/desktop/resources/META-INF/native-image/org.openjfx/javafx-graphics/reachability-metadata.json"
metadata = json.loads(metadata_path.read_text(encoding="utf-8"))
with ZipFile(args.graphics_jar) as archive:
    entries = set(archive.namelist())

loader_prefix = "com/sun/prism/shader/"
shader_prefix = "com/sun/prism/d3d/hlsl/"
loaders = {name[len(loader_prefix):-len("_Loader.class")] for name in entries if name.startswith(loader_prefix) and name.endswith("_Loader.class")}
objects = {name[len(shader_prefix):-len(".obj")] for name in entries if name.startswith(shader_prefix) and name.endswith(".obj")}
expected_signature = ["com.sun.prism.ps.ShaderFactory", "java.lang.String", "java.io.InputStream"]
reflections = [entry for entry in metadata["reflection"] if entry["type"].startswith("com.sun.prism.shader.") and entry["type"].endswith("_Loader")]
resources = [entry["glob"] for entry in metadata["resources"] if entry.get("glob", "").startswith(shader_prefix) and entry["glob"].endswith(".obj")]
reflected = [entry["type"][len("com.sun.prism.shader."):-len("_Loader")] for entry in reflections]
included = [path[len(shader_prefix):-len(".obj")] for path in resources]
invalid_methods = [entry["type"] for entry in reflections if entry.get("methods") != [{"name": "loadShader", "parameterTypes": expected_signature}]]
issues = []
# Glass looks these callbacks up through JNI when Windows UI Automation reads a
# text control. Missing entries fail only at runtime, sometimes with a segfault.
text_range_type = "com.sun.glass.ui.win.WinTextRangeProvider"
signatures = subprocess.check_output(
    ["javap", "-private", "-classpath", str(args.graphics_jar), text_range_type], text=True
)
required_callbacks = []
for line in signatures.splitlines():
    callback = re.match(r"\s*private [\w.$\[\]]+ ([A-Z]\w*)\(([^)]*)\);", line)
    if callback:
        required_callbacks.append({"name": callback[1], "parameterTypes": callback[2].split(", ") if callback[2] else []})
text_ranges = [entry for entry in metadata["reflection"] if entry["type"] == text_range_type]
if not required_callbacks or len(text_ranges) != 1 or not text_ranges[0].get("jniAccessible"):
    issues.append("Windows text accessibility JNI callbacks are not registered")
else:
    missing_callbacks = [method for method in required_callbacks if method not in text_ranges[0].get("methods", [])]
    if missing_callbacks:
        issues.append(f"missing Windows text accessibility JNI callbacks: {missing_callbacks}")
toolkit = [entry for entry in metadata["reflection"] if entry["type"] == "com.sun.javafx.tk.quantum.QuantumToolkit"]
if len(toolkit) != 1 or {"name": "<init>", "parameterTypes": []} not in toolkit[0].get("methods", []):
    issues.append("the dynamically loaded JavaFX toolkit constructor is not registered")
if not loaders or not objects:
    issues.append("the JAR contains no shader loader classes or objects")
if loaders != objects:
    issues.append(f"JAR shader loader/object mismatch: loaders-only={sorted(loaders - objects)}, objects-only={sorted(objects - loaders)}")
if set(reflected) != loaders or len(reflected) != len(set(reflected)):
    issues.append(f"reflected loaders mismatch: missing={sorted(loaders - set(reflected))}, extra={sorted(set(reflected) - loaders)}, duplicates={sorted(name for name, count in Counter(reflected).items() if count > 1)}")
if set(included) != objects or len(included) != len(set(included)):
    issues.append(f"included shader objects mismatch: missing={sorted(objects - set(included))}, extra={sorted(set(included) - objects)}, duplicates={sorted(name for name, count in Counter(included).items() if count > 1)}")
if invalid_methods:
    issues.append(f"incorrect loadShader reflection signatures: {sorted(invalid_methods)}")
# GlassDnD.cpp and GlassClipboard.cpp resolve these members through JNI when
# pointer input activates the Windows drop target in a text control.
clipboard_callbacks = {
    "com.sun.glass.ui.win.WinDnDClipboard": ({"getInstance": [], "getDragButton": [],
        "setDragButton": ["int"], "setSourceSupportedActions": ["int"]}, []),
    "com.sun.glass.ui.win.WinSystemClipboard": ({"fosSerialize": ["java.lang.String", "long"]}, ["ptr"]),
    "com.sun.glass.ui.Clipboard": ({"contentChanged": [], "actionPerformed": ["int"]}, ["name"]),
    "java.lang.Throwable": ({}, []),
}
for owner, (methods, fields) in clipboard_callbacks.items():
    entry = next((item for item in metadata["reflection"] if item["type"] == owner), {})
    registered = entry.get("methods", [])
    if not entry.get("jniAccessible") or any(
        {"name": name, "parameterTypes": args} not in registered for name, args in methods.items()
    ) or any({"name": name} not in entry.get("fields", []) for name in fields):
        issues.append(f"Missing Windows clipboard JNI registration on {owner}")
if issues:
    parser.exit(1, "\n".join(issues) + "\n")
print(f"Verified {len(loaders)} shader loaders, reflection signatures, and objects in {expected_name}")
