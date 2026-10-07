# ui.fx

[English](README.md) | [简体中文](README.zh-CN.md)

[ui 协议](https://github.com/normlanguage/ui)的 JavaFX 实现。JavaFX 原生类型与渲染适配器属于同一个模块。

## 使用

在 `module.norm` 中声明正式发布的模块：

```norm
dependency(repository: "github", name: "ui.fx", version: 6)
```

使用 [DesktopApp](ui/fx/desktop.norm)承载 `ui.Widget`。[基础示例](samples/foundation/application.norm)展示状态、主题切换与布局，其[模块声明](samples/foundation/module.norm)默认解析正式包。

原生扩展通过 `ui.fx.native.Node`、`ui.fx.native.Button` 和 `ui.fx.native.buttonNew` 等构造函数使用 JavaFX。[nativeComponent](ui/fx/native.norm)统一管理初始化、更新、子节点、配置、主题与释放。内部布局绑定不对外导出。公开 API 以 [module.norm](ui/fx/module.norm)为准。

## 构建与验证

运行 `./scripts/prepare.ps1` 构建 Java 适配器并验证固定摘要与模块，不修改依赖固定值。修改适配器后，明确运行 `./scripts/update-pin.ps1` 更新摘要。JavaFX 版本与可复现归档设置统一由 [build.gradle.kts](build.gradle.kts)管理。

布局契约使用 `./gradlew.bat test --tests dev.normlanguage.ui.fx.LayoutPaneTest`；后端契约使用 `norm test ui/fx`；真实窗口流程使用 `norm test samples/foundation`。[发布流程](.github/workflows/package.yml)负责发布验证。本地源码包可放入独立 Norm home，示例声明仍指向正式发布依赖。

## 源码索引

- [布局实现](ui/fx/layout.norm)与 [Java 适配器](src/main/java/dev/normlanguage/ui/fx/LayoutPane.java)
- [主题与配置投影](ui/fx/theme.norm)
- [窗口生命周期](ui/fx/application.norm)与 [JavaFX 运行时](ui/fx/runtime.norm)
- [退出确认](samples/foundation/application.norm)与 [关闭生命周期契约](ui/fx/tests/test/lifecycle/case.norm)
- [示例职责](samples/README.zh-CN.md)

## 许可证

[MPL-2.0](LICENSE)。适配器归档在 `META-INF/LICENSE` 携带许可证。
