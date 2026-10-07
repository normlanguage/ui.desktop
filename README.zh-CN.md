# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

模块身份和依赖见 [module.norm](ui/fx/module.norm)，发布使用的工具链见[工作流](.github/workflows/package.yml)。

构建：`norm package ui/fx --output build/repository`。

测试：`norm test ui/fx`。

已在 Windows x64 验证 JVM 执行和 Native 应用启动。JavaFX 制品从 Maven Central 解析；Norm 包通过 GitHub Releases 分发。

[示例归属](samples/README.zh-CN.md)。

原生 JavaFX 节点接入和应用样式配置入口见 [application.norm](ui/fx/application.norm) 与 [node.norm](ui/fx/node.norm)。

Rendering adapter: [backend.norm](ui/fx/backend.norm). Desktop entry points: [desktop.norm](ui/fx/desktop.norm). Core contracts: [ui](https://github.com/normlanguage/ui).

布局实现：[协议适配](ui/fx/layout.norm)、[布局节点](src/main/java/dev/normlanguage/ui/fx/LayoutPane.java)。主题与配置投影：[theme.norm](ui/fx/theme.norm)。通用原生控件接入：[native.norm](ui/fx/native.norm)。

[基础示例](samples/foundation/application.norm)只使用 `ui`、`theme` 与本后端，不依赖组件库。

先运行 `./gradlew.bat publish`，将 `build/repository` 内容放入 Norm Maven 缓存，再打包模块。完整 CI 步骤以[工作流](.github/workflows/package.yml)为准。验收入口：[Java 布局契约](src/test/java/dev/normlanguage/ui/fx/LayoutPaneTest.java)、[Norm 场景集成](ui/fx/tests/test/layout/case.norm)。
