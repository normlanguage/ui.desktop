# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

模块身份和依赖见 [module.norm](ui/fx/module.norm)，发布使用的工具链见[工作流](.github/workflows/package.yml)。

构建：`norm package ui/fx --output build/repository`。

测试：`norm test ui/fx`。

已在 Windows x64 验证 JVM 执行和 Native 应用启动。JavaFX 制品从 Maven Central 解析；Norm 包通过 GitHub Releases 分发。

[示例归属](samples/README.zh-CN.md)。

原生 JavaFX 节点接入和应用样式配置入口见 [application.norm](ui/fx/application.norm) 与 [node.norm](ui/fx/node.norm)。

Rendering adapter: [backend.norm](ui/fx/backend.norm). Desktop entry points: [desktop.norm](ui/fx/desktop.norm). Core contracts: [ui](https://github.com/normlanguage/ui).

[窗口图标配置与资源来源](ui/fx/icons.md)。
