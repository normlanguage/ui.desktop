# Window icons

Default icon selection and validation: [application.norm](application.norm). Public desktop configuration: [desktop.norm](desktop.norm). Real-window coverage: [tests](tests/test/icons/case.norm).

PNG resources in [resources/ui/fx/icons](resources/ui/fx/icons) are the unmodified Norm brand assets from [Norm revision 6ca5d48](https://github.com/normlanguage/Norm/tree/6ca5d48bf360d3a2d3f9c4a676bbd5edcdfe81dd/docs/public/brand). Update them from that source; do not redraw them. The package includes the images as classpath resources, so source execution and packaged applications share the same defaults.

`icons` accepts JavaFX Image resource names or URLs. Omit it for Norm defaults, provide a list to replace them, or an empty list for platform defaults. Invalid images fail application startup.
