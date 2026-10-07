# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

JavaFX implementation of the [ui protocol](https://github.com/normlanguage/ui). JavaFX native types and the rendering adapter belong to this module.

## Use

Declare the released module in `module.norm`:

```norm
dependency(repository: "github", name: "ui.fx", version: 7)
```

Use [DesktopApp](ui/fx/desktop.norm) with `ui.Widget` controls. The [foundation application](samples/foundation/application.norm) demonstrates state, theme switching and layouts. Its [module](samples/foundation/module.norm) resolves released packages by default.

Native integration uses `ui.fx.native.Node`, `ui.fx.native.Button` and constructors such as `ui.fx.native.buttonNew`. [nativeComponent](ui/fx/native.norm) owns native initialization, updates, children, configuration, theme and disposal. Private layout adapter bindings are not exported. The exact public surface is defined in [module.norm](ui/fx/module.norm).

## Build and verify

Run `./scripts/prepare.ps1` to build the Java adapter and verify its pinned digest and module. It does not change dependency pins. Run `./scripts/update-pin.ps1` intentionally after changing the adapter. JavaFX dependency versions and reproducible archive settings are owned by [build.gradle.kts](build.gradle.kts).

Run `./gradlew.bat test --tests dev.normlanguage.ui.fx.LayoutPaneTest` for layout contracts, `norm test ui/fx` for backend contracts, and `norm test samples/foundation` for the real window flow. The [package workflow](.github/workflows/package.yml) owns release validation. Local development packages may be placed in an isolated Norm home; the sample descriptor remains a released dependency declaration.

## Sources

- [Layout implementation](ui/fx/layout.norm) and [Java layout adapter](src/main/java/dev/normlanguage/ui/fx/LayoutPane.java)
- [Theme and configuration projection](ui/fx/theme.norm)
- [Window lifecycle](ui/fx/application.norm) and [JavaFX runtime](ui/fx/runtime.norm)
- [Close confirmation](samples/foundation/application.norm) and [close lifecycle contracts](ui/fx/tests/test/lifecycle/case.norm)
- [Sample ownership](samples/README.md)

## License

[MPL-2.0](LICENSE). The adapter archive carries the license in `META-INF/LICENSE`.
