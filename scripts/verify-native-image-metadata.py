import argparse
import json
import re
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
if issues:
    parser.exit(1, "\n".join(issues) + "\n")
print(f"Verified {len(loaders)} shader loaders, reflection signatures, and objects in {expected_name}")
