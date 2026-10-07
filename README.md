# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

Module identity and dependencies: [module.norm](ui/fx/module.norm). Package toolchain: [workflow](.github/workflows/package.yml).

Build: `norm package ui/fx --output build/repository`.

Tests: `norm test ui/fx`.

Validated on Windows x64 with JVM execution and Native application startup. JavaFX artifacts are resolved from Maven Central; Norm packages are distributed through GitHub Releases.

[Sample ownership](samples/README.md).

Native JavaFX node adoption and application stylesheet configuration are defined in [application.norm](ui/fx/application.norm) and [node.norm](ui/fx/node.norm).

Rendering adapter: [backend.norm](ui/fx/backend.norm). Desktop entry points: [desktop.norm](ui/fx/desktop.norm). Core contracts: [ui](https://github.com/normlanguage/ui).

[Window icon configuration and asset provenance](ui/fx/icons.md).
