# ui.desktop

[English](README.md) | [简体中文](README.zh-CN.md)

JavaFX implementation of the [ui protocol](https://github.com/normlanguage/ui). JavaFX native types and the rendering adapter belong to this module.

## Use

Declare the released module in `module.norm`:

```norm
dependency(repository: "github", name: "ui.desktop", version: 7)
```

Use [DesktopApp](ui/desktop/desktop.norm) with `ui.Widget` controls. The [foundation application](samples/foundation/application.norm) demonstrates state, theme switching and layouts. Its [module](samples/foundation/module.norm) resolves released packages by default.

Native integration uses `ui.desktop.native.Node`, `ui.desktop.native.Button` and constructors such as `ui.desktop.native.buttonNew`. [nativeComponent](ui/desktop/native.norm) owns native initialization, updates, children, configuration, theme and disposal. Private layout adapter bindings are not exported. The exact public surface is defined in [module.norm](ui/desktop/module.norm).

## Build and verify

Run `./scripts/prepare.ps1` to build the Java adapter and verify its pinned digest and module. It does not change dependency pins. Run `./scripts/update-pin.ps1` intentionally after changing the adapter. JavaFX dependency versions and reproducible archive settings are owned by [build.gradle.kts](build.gradle.kts).

Run `./gradlew.bat test --tests dev.normlanguage.ui.desktop.LayoutPaneTest` for layout contracts, `norm test ui/desktop` for backend contracts, and `norm test samples/foundation` for the real window flow. The [package workflow](.github/workflows/package.yml) owns release validation. Local development packages may be placed in an isolated Norm home; the sample descriptor remains a released dependency declaration.

Windows Native Image reachability declarations live in [module resources](ui/desktop/resources/META-INF/native-image/org.openjfx). Run `./gradlew.bat verifyNativeImageMetadata` with Python 3 to compare toolkit and shader registrations against the resolved JavaFX JAR.

## Sources

- [Layout implementation](ui/desktop/layout.norm) and [Java layout adapter](src/main/java/dev/normlanguage/ui/desktop/LayoutPane.java)
- [Theme and configuration projection](ui/desktop/theme.norm)
- [Window lifecycle](ui/desktop/application.norm) and [JavaFX runtime](ui/desktop/runtime.norm)
- [Close confirmation](samples/foundation/application.norm) and [close lifecycle contracts](ui/desktop/tests/test/lifecycle/case.norm)
- [Sample ownership](samples/README.md)

## License

[MPL-2.0](LICENSE). The adapter archive carries the license in `META-INF/LICENSE`.
