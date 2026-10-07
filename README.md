# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

Module identity and dependencies: [module.norm](ui/fx/module.norm). Package toolchain: [workflow](.github/workflows/package.yml).

Build: `norm package ui/fx --output build/repository`.

Tests: `norm test ui/fx`.

Validated on Windows x64 with JVM execution and Native application startup. JavaFX artifacts are resolved from Maven Central; Norm packages are distributed through GitHub Releases.

[Sample ownership](samples/README.md).

Native JavaFX node adoption and application stylesheet configuration are defined in [application.norm](ui/fx/application.norm) and [node.norm](ui/fx/node.norm).

Rendering adapter: [backend.norm](ui/fx/backend.norm). Desktop entry points: [desktop.norm](ui/fx/desktop.norm). Core contracts: [ui](https://github.com/normlanguage/ui).

Layouts: [public protocol implementation](ui/fx/layout.norm), [layout adapter](src/main/java/dev/normlanguage/ui/fx/LayoutPane.java). Theme and configuration projection: [theme.norm](ui/fx/theme.norm). Generic native component integration: [native.norm](ui/fx/native.norm).

The [foundation sample](samples/foundation/application.norm) uses `ui`, `theme`, and this backend without a component kit.

Build the Java adapter with `./gradlew.bat publish`, copy `build/repository` into the Norm Maven cache, then package the module. [Package workflow](.github/workflows/package.yml) owns the reproducible CI sequence. Verification: [Java layout contracts](src/test/java/dev/normlanguage/ui/fx/LayoutPaneTest.java), [Norm scene integration](ui/fx/tests/test/layout/case.norm).
