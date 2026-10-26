# ui.desktop Skill

Use this skill to work with Norm module `ui.desktop` version 8. Use only declarations listed below.

## `ui.desktop`

Source: `application.norm`

### `CloseRequest`

```norm
class CloseRequest
```

#### `CloseRequest`

```norm
CloseRequest(Function<Void(Boolean)> complete)
```

Parameters:

- `complete`: `Function<Void(Boolean)>`

#### `accept`

```norm
Void accept()
```

Returns: `Void`

#### `cancel`

```norm
Void cancel()
```

Returns: `Void`

### `FxApplication`

```norm
class FxApplication
```

#### `FxApplication`

```norm
FxApplication(String title, String? stylesheet, Double width, Double height)
```

Parameters:

- `title`: `String`
- `stylesheet`: `String?`
- `width`: `Double`
- `height`: `Double`

#### `run`

```norm
Void run(Function<Void()> build, Function<Void()> dispose)
```

Parameters:

- `build`: `Function<Void()>`
- `dispose`: `Function<Void()>`

Returns: `Void`

#### `root`

```norm
Void root(FxNode node)
```

Parameters:

- `node`: `FxNode`

Returns: `Void`

#### `scene`

```norm
Scene scene()
```

Returns: `Scene`

#### `text`

```norm
FxNode text()
```

Returns: `FxNode`

#### `button`

```norm
FxNode button()
```

Returns: `FxNode`

#### `input`

```norm
FxNode input()
```

Returns: `FxNode`

#### `textArea`

```norm
FxNode textArea()
```

Returns: `FxNode`

#### `toggle`

```norm
FxNode toggle()
```

Returns: `FxNode`

#### `adopt`

```norm
FxNode adopt(Node node)
```

Parameters:

- `node`: `Node`

Returns: `FxNode`

#### `component`

```norm
FxNode component()
```

Returns: `FxNode`

#### `interceptClose`

```norm
Void interceptClose(Function<Void(CloseRequest)> callback)
```

Parameters:

- `callback`: `Function<Void(CloseRequest)>`

Returns: `Void`

#### `hide`

```norm
Void hide()
```

Returns: `Void`

#### `show`

```norm
Void show()
```

Returns: `Void`

#### `requestClose`

```norm
Void requestClose()
```

Returns: `Void`

#### `close`

```norm
Void close()
```

Returns: `Void`

#### `later`

```norm
Void later(Function<Void()> action)
```

Parameters:

- `action`: `Function<Void()>`

Returns: `Void`

#### `trackTermination`

```norm
Void trackTermination(Task<Unit> termination)
```

Parameters:

- `termination`: `Task<Unit>`

Returns: `Void`

#### `reportFailure`

```norm
Void reportFailure(String message)
```

Parameters:

- `message`: `String`

Returns: `Void`

#### `dispatch`

```norm
Void dispatch(Function<Void()> action)
```

Parameters:

- `action`: `Function<Void()>`

Returns: `Void`

## `ui.desktop`

Source: `backend.norm`

### `JavaFxScheduler`

```norm
class JavaFxScheduler implements UiScheduler
```

#### `JavaFxScheduler`

```norm
JavaFxScheduler(FxApplication application)
```

Parameters:

- `application`: `FxApplication`

#### `track`

```norm
Void track(Task<Unit> termination)
```

Parameters:

- `termination`: `Task<Unit>`

Returns: `Void`

#### `later`

```norm
Void later(Function<Void()> action)
```

Parameters:

- `action`: `Function<Void()>`

Returns: `Void`

#### `report`

```norm
Void report(Exception error)
```

Parameters:

- `error`: `Exception`

Returns: `Void`

### `JavaFxBackend`

```norm
class JavaFxBackend implements LayoutBackend
```

#### `JavaFxBackend`

```norm
JavaFxBackend(FxApplication application)
```

Parameters:

- `application`: `FxApplication`

#### `fxApplication`

```norm
FxApplication fxApplication()
```

Returns: `FxApplication`

#### `createLayout`

```norm
RenderNode createLayout(LayoutSpec specification)
```

Parameters:

- `specification`: `LayoutSpec`

Returns: `RenderNode`

#### `create`

```norm
RenderNode create(ViewContent content)
```

Parameters:

- `content`: `ViewContent`

Returns: `RenderNode`

### `FxNodePeer`

```norm
interface FxNodePeer extends RenderNode
```

#### `fxNode`

```norm
FxNode fxNode()
```

Returns: `FxNode`

### `FxRenderNode`

```norm
class FxRenderNode implements TextFieldRenderNode, FxNodePeer, ThemedRenderNode, ConfiguredRenderNode
```

#### `theme`

```norm
Void theme(Theme? theme)
```

Parameters:

- `theme`: `Theme?`

Returns: `Void`

#### `configure`

```norm
Void configure(UiConfiguration configuration)
```

Parameters:

- `configuration`: `UiConfiguration`

Returns: `Void`

#### `FxRenderNode`

```norm
FxRenderNode(FxNode peer)
```

Parameters:

- `peer`: `FxNode`

#### `fxNode`

```norm
FxNode fxNode()
```

Returns: `FxNode`

#### `textFieldHandle`

```norm
TextFieldHandle textFieldHandle()
```

Returns: `TextFieldHandle`

#### `update`

```norm
Void update(View view)
```

Parameters:

- `view`: `View`

Returns: `Void`

#### `children`

```norm
Void children(List<RenderNode> children)
```

Parameters:

- `children`: `List<RenderNode>`

Returns: `Void`

#### `close`

```norm
Void close()
```

Returns: `Void`

### `FxTextFieldControl`

```norm
class FxTextFieldControl implements TextFieldControl
```

#### `FxTextFieldControl`

```norm
FxTextFieldControl(FxNode peer)
```

Parameters:

- `peer`: `FxNode`

#### `readText`

```norm
String readText()
```

Returns: `String`

#### `clear`

```norm
Void clear()
```

Returns: `Void`

#### `focus`

```norm
Void focus()
```

Returns: `Void`

### `fxHandle`

```norm
FxNode fxHandle(RenderNode node)
```

Parameters:

- `node`: `RenderNode`

Returns: `FxNode`

## `ui.desktop`

Source: `desktop.norm`

### `DesktopApp`

```norm
value DesktopApp<C = Unit>
```

Type parameters:

- `C` = `Unit`

#### `title`

```norm
String title
```

Type: `String`

#### `home`

```norm
Widget home
```

Type: `Widget`

#### `config`

```norm
C? config
```

Type: `C?`

#### `width`

```norm
Double width
```

Type: `Double`

#### `height`

```norm
Double height
```

Type: `Double`

#### `onCloseRequested`

```norm
Function<Void(CloseRequest)>? onCloseRequested
```

Type: `Function<Void(CloseRequest)>?`

#### `run`

```norm
Void run()
```

Returns: `Void`

### `runApp`

```norm
Void runApp<C>(DesktopApp<C> app)
```

Type parameters:

- `C`

Parameters:

- `app`: `DesktopApp<C>`

Returns: `Void`

### `runApp`

```norm
Void runApp(String title, Function<View(ViewScope)> body, Container? container, Double width, Double height, Function<Void(CloseRequest)>? onCloseRequested)
```

Parameters:

- `title`: `String`
- `body`: `Function<View(ViewScope)>`
- `container`: `Container?`
- `width`: `Double`
- `height`: `Double`
- `onCloseRequested`: `Function<Void(CloseRequest)>?`

Returns: `Void`

## `ui.desktop.internal`

Source: `internal/CrossAlignment.norm`

### `CrossAlignment`

```norm
enum CrossAlignment
```

#### `CENTER`

```norm
CENTER
```

#### `END`

```norm
END
```

#### `START`

```norm
START
```

#### `STRETCH`

```norm
STRETCH
```

## `ui.desktop.internal`

Source: `internal/DimensionMode.norm`

### `DimensionMode`

```norm
enum DimensionMode
```

#### `CONTENT`

```norm
CONTENT
```

#### `FILL`

```norm
FILL
```

#### `FIXED`

```norm
FIXED
```

## `ui.desktop.internal`

Source: `internal/DockArea.norm`

### `DockArea`

```norm
enum DockArea
```

#### `BOTTOM`

```norm
BOTTOM
```

#### `CENTER`

```norm
CENTER
```

#### `LEFT`

```norm
LEFT
```

#### `RIGHT`

```norm
RIGHT
```

#### `TOP`

```norm
TOP
```

## `ui.desktop.internal`

Source: `internal/LayoutMode.norm`

### `LayoutMode`

```norm
enum LayoutMode
```

#### `BOX`

```norm
BOX
```

#### `DOCK`

```norm
DOCK
```

#### `FLEX`

```norm
FLEX
```

#### `GRID`

```norm
GRID
```

#### `MASONRY`

```norm
MASONRY
```

#### `STACK`

```norm
STACK
```

## `ui.desktop.internal`

Source: `internal/LayoutPane.norm`

### `LayoutPane`

```norm
class LayoutPane extends Pane implements EventTarget, Styleable
```

#### `LayoutPane`

```norm
LayoutPane(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `breakpoint`

```norm
Void breakpoint(Double arg0, Integer arg1)
```

Parameters:

- `arg0`: `Double`
- `arg1`: `Integer`

Returns: `Void`

#### `clearBreakpoints`

```norm
Void clearBreakpoints()
```

Returns: `Void`

#### `configureFlex`

```norm
Void configureFlex(Boolean arg0, Double arg1, MainAlignment? arg2, CrossAlignment? arg3, Boolean arg4)
```

Parameters:

- `arg0`: `Boolean`
- `arg1`: `Double`
- `arg2`: `MainAlignment?`
- `arg3`: `CrossAlignment?`
- `arg4`: `Boolean`

Returns: `Void`

#### `configureGrid`

```norm
Void configureGrid(Integer arg0, Double arg1)
```

Parameters:

- `arg0`: `Integer`
- `arg1`: `Double`

Returns: `Void`

#### `configureHeight`

```norm
Void configureHeight(DimensionMode? arg0, Double arg1, Double arg2, Double arg3)
```

Parameters:

- `arg0`: `DimensionMode?`
- `arg1`: `Double`
- `arg2`: `Double`
- `arg3`: `Double`

Returns: `Void`

#### `configureMasonry`

```norm
Void configureMasonry(Double arg0, Double arg1)
```

Parameters:

- `arg0`: `Double`
- `arg1`: `Double`

Returns: `Void`

#### `configureStack`

```norm
Void configureStack(CrossAlignment? arg0, CrossAlignment? arg1)
```

Parameters:

- `arg0`: `CrossAlignment?`
- `arg1`: `CrossAlignment?`

Returns: `Void`

#### `configureWidth`

```norm
Void configureWidth(DimensionMode? arg0, Double arg1, Double arg2, Double arg3)
```

Parameters:

- `arg0`: `DimensionMode?`
- `arg1`: `Double`
- `arg2`: `Double`
- `arg3`: `Double`

Returns: `Void`

#### `dockItem`

```norm
Void dockItem(Node? arg0, DockArea? arg1)
```

Parameters:

- `arg0`: `Node?`
- `arg1`: `DockArea?`

Returns: `Void`

#### `flexItem`

```norm
Void flexItem(Node? arg0, Double arg1, Double arg2)
```

Parameters:

- `arg0`: `Node?`
- `arg1`: `Double`
- `arg2`: `Double`

Returns: `Void`

#### `gridItem`

```norm
Void gridItem(Node? arg0, Integer arg1)
```

Parameters:

- `arg0`: `Node?`
- `arg1`: `Integer`

Returns: `Void`

### `layoutPaneNew`

```norm
LayoutPane layoutPaneNew(LayoutMode? arg0)
```

Parameters:

- `arg0`: `LayoutMode?`

Returns: `LayoutPane`

## `ui.desktop.internal`

Source: `internal/MainAlignment.norm`

### `MainAlignment`

```norm
enum MainAlignment
```

#### `CENTER`

```norm
CENTER
```

#### `END`

```norm
END
```

#### `SPACE_AROUND`

```norm
SPACE_AROUND
```

#### `SPACE_BETWEEN`

```norm
SPACE_BETWEEN
```

#### `SPACE_EVENLY`

```norm
SPACE_EVENLY
```

#### `START`

```norm
START
```

## `ui.desktop.javafx.animation`

Source: `javafx/animation/Interpolatable.norm`

### `Interpolatable`

```norm
interface Interpolatable<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.beans.binding`

Source: `javafx/beans/binding/BooleanExpression.norm`

### `BooleanExpression`

```norm
class BooleanExpression implements ObservableBooleanValue
```

#### `BooleanExpression`

```norm
BooleanExpression(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.binding`

Source: `javafx/beans/binding/IntegerExpression.norm`

### `IntegerExpression`

```norm
class IntegerExpression extends NumberExpressionBase implements ObservableIntegerValue, NumberExpression
```

#### `IntegerExpression`

```norm
IntegerExpression(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.binding`

Source: `javafx/beans/binding/NumberExpression.norm`

### `NumberExpression`

```norm
interface NumberExpression extends ObservableNumberValue
```

## `ui.desktop.javafx.beans.binding`

Source: `javafx/beans/binding/NumberExpressionBase.norm`

### `NumberExpressionBase`

```norm
class NumberExpressionBase implements NumberExpression
```

#### `NumberExpressionBase`

```norm
NumberExpressionBase(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.binding`

Source: `javafx/beans/binding/StringExpression.norm`

### `StringExpression`

```norm
class StringExpression implements ObservableStringValue
```

#### `StringExpression`

```norm
StringExpression(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.property`

Source: `javafx/beans/property/Property.norm`

### `Property`

```norm
interface Property<T> extends ReadOnlyProperty<T>, WritableValue<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.beans.property`

Source: `javafx/beans/property/ReadOnlyBooleanProperty.norm`

### `ReadOnlyBooleanProperty`

```norm
class ReadOnlyBooleanProperty extends BooleanExpression implements ReadOnlyProperty<Boolean>, ObservableBooleanValue
```

#### `ReadOnlyBooleanProperty`

```norm
ReadOnlyBooleanProperty(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.property`

Source: `javafx/beans/property/ReadOnlyIntegerProperty.norm`

### `ReadOnlyIntegerProperty`

```norm
class ReadOnlyIntegerProperty extends IntegerExpression implements ReadOnlyProperty<Number>, ObservableIntegerValue, NumberExpression
```

#### `ReadOnlyIntegerProperty`

```norm
ReadOnlyIntegerProperty(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.property`

Source: `javafx/beans/property/ReadOnlyProperty.norm`

### `ReadOnlyProperty`

```norm
interface ReadOnlyProperty<T> extends ObservableValue<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.beans.property`

Source: `javafx/beans/property/ReadOnlyStringProperty.norm`

### `ReadOnlyStringProperty`

```norm
class ReadOnlyStringProperty extends StringExpression implements ReadOnlyProperty<String>, ObservableStringValue
```

#### `ReadOnlyStringProperty`

```norm
ReadOnlyStringProperty(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/ObservableBooleanValue.norm`

### `ObservableBooleanValue`

```norm
interface ObservableBooleanValue extends ObservableValue<Boolean>
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/ObservableIntegerValue.norm`

### `ObservableIntegerValue`

```norm
interface ObservableIntegerValue extends ObservableNumberValue
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/ObservableNumberValue.norm`

### `ObservableNumberValue`

```norm
interface ObservableNumberValue extends ObservableValue<Number>
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/ObservableObjectValue.norm`

### `ObservableObjectValue`

```norm
interface ObservableObjectValue<T> extends ObservableValue<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/ObservableStringValue.norm`

### `ObservableStringValue`

```norm
interface ObservableStringValue extends ObservableObjectValue<String>
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/WritableBooleanValue.norm`

### `WritableBooleanValue`

```norm
interface WritableBooleanValue extends WritableValue<Boolean>
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/WritableObjectValue.norm`

### `WritableObjectValue`

```norm
interface WritableObjectValue<T> extends WritableValue<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/WritableStringValue.norm`

### `WritableStringValue`

```norm
interface WritableStringValue extends WritableObjectValue<String>
```

## `ui.desktop.javafx.beans.value`

Source: `javafx/beans/value/WritableValue.norm`

### `WritableValue`

```norm
interface WritableValue<T>
```

Type parameters:

- `T`

## `ui.desktop.javafx.event`

Source: `javafx/event/ActionEvent.norm`

### `ActionEvent`

```norm
class ActionEvent extends Event implements Cloneable, Serializable
```

#### `ActionEvent`

```norm
ActionEvent(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/ButtonBase.norm`

### `ButtonBase`

```norm
class ButtonBase extends Labeled implements Skinnable, EventTarget, Styleable
```

#### `ButtonBase`

```norm
ButtonBase(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/ButtonType.norm`

### `ButtonType`

```norm
class ButtonType
```

#### `ButtonType`

```norm
ButtonType(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/Control.norm`

### `Control`

```norm
class Control extends Region implements Skinnable, EventTarget, Styleable
```

#### `Control`

```norm
Control(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/Dialog.norm`

### `Dialog`

```norm
class Dialog<R> implements EventTarget
```

Type parameters:

- `R`

#### `Dialog`

```norm
Dialog(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/DialogEvent.norm`

### `DialogEvent`

```norm
class DialogEvent extends Event implements Cloneable, Serializable
```

#### `DialogEvent`

```norm
DialogEvent(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/SelectionModel.norm`

### `SelectionModel`

```norm
class SelectionModel<T>
```

Type parameters:

- `T`

#### `SelectionModel`

```norm
SelectionModel(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.javafx.scene.control`

Source: `javafx/scene/control/Skinnable.norm`

### `Skinnable`

```norm
interface Skinnable
```

## `ui.desktop.javafx.stage`

Source: `javafx/stage/WindowEvent.norm`

### `WindowEvent`

```norm
class WindowEvent extends Event implements Cloneable, Serializable
```

#### `WindowEvent`

```norm
WindowEvent(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop`

Source: `native.norm`

### `nativeComponent`

```norm
View nativeComponent<T>(String kind, Function<T()> create, Function<Node(T)> node, Function<Void(T, ViewScope)> update, Function<Void(T)> close, List<Widget> children, Function<Void(T, List<Node>)>? arrange, Function<List<Widget>(T)>? childrenBuilder, NodeKey? key, Boolean enabled, Function<Void(T, Theme?)>? applyTheme, Function<Void(T, UiConfiguration)>? applyConfiguration)
```

Type parameters:

- `T`

Parameters:

- `kind`: `String`
- `create`: `Function<T()>`
- `node`: `Function<Node(T)>`
- `update`: `Function<Void(T, ViewScope)>`
- `close`: `Function<Void(T)>`
- `children`: `List<Widget>`
- `arrange`: `Function<Void(T, List<Node>)>?`
- `childrenBuilder`: `Function<List<Widget>(T)>?`
- `key`: `NodeKey?`
- `enabled`: `Boolean`
- `applyTheme`: `Function<Void(T, Theme?)>?`
- `applyConfiguration`: `Function<Void(T, UiConfiguration)>?`

Returns: `View`

## `ui.desktop.native`

Source: `native/Alert.norm`

### `Alert`

```norm
class Alert extends Dialog<ButtonType> implements EventTarget
```

#### `Alert`

```norm
Alert(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `close`

```norm
Void close()
```

Returns: `Void`

#### `initOwner`

```norm
Void initOwner(Window? arg0)
```

Parameters:

- `arg0`: `Window?`

Returns: `Void`

#### `setContentText`

```norm
Void setContentText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setHeaderText`

```norm
Void setHeaderText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setOnHidden`

```norm
Void setOnHidden(Function<Void(DialogEvent?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(DialogEvent?)>?`

Returns: `Void`

#### `setTitle`

```norm
Void setTitle(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `show`

```norm
Void show()
```

Returns: `Void`

### `alertNew`

```norm
Alert alertNew(AlertAlertType? arg0)
```

Parameters:

- `arg0`: `AlertAlertType?`

Returns: `Alert`

## `ui.desktop.native`

Source: `native/AlertAlertType.norm`

### `AlertAlertType`

```norm
enum AlertAlertType
```

#### `CONFIRMATION`

```norm
CONFIRMATION
```

#### `ERROR`

```norm
ERROR
```

#### `INFORMATION`

```norm
INFORMATION
```

#### `NONE`

```norm
NONE
```

#### `WARNING`

```norm
WARNING
```

## `ui.desktop.native`

Source: `native/BooleanProperty.norm`

### `BooleanProperty`

```norm
class BooleanProperty extends ReadOnlyBooleanProperty implements Property<Boolean>, WritableBooleanValue, ReadOnlyProperty<Boolean>, ObservableBooleanValue
```

#### `BooleanProperty`

```norm
BooleanProperty(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.native`

Source: `native/Button.norm`

### `Button`

```norm
class Button extends ButtonBase implements Skinnable, EventTarget, Styleable
```

#### `Button`

```norm
Button(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `fire`

```norm
Void fire()
```

Returns: `Void`

#### `setOnAction`

```norm
Void setOnAction(Function<Void(ActionEvent?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(ActionEvent?)>?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

### `buttonNew`

```norm
Button buttonNew()
```

Returns: `Button`

## `ui.desktop.native`

Source: `native/CheckBox.norm`

### `CheckBox`

```norm
class CheckBox extends ButtonBase implements Skinnable, EventTarget, Styleable
```

#### `CheckBox`

```norm
CheckBox(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `isSelected`

```norm
Boolean isSelected()
```

Returns: `Boolean`

#### `selectedProperty`

```norm
BooleanProperty? selectedProperty()
```

Returns: `BooleanProperty?`

#### `setMaxWidth`

```norm
Void setMaxWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setSelected`

```norm
Void setSelected(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

### `checkBoxNew`

```norm
CheckBox checkBoxNew()
```

Returns: `CheckBox`

## `ui.desktop.native`

Source: `native/Event.norm`

### `Event`

```norm
class Event extends EventObject implements Cloneable, Serializable
```

#### `Event`

```norm
Event(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `consume`

```norm
Void consume()
```

Returns: `Void`

## `ui.desktop.native`

Source: `native/EventTarget.norm`

### `EventTarget`

```norm
interface EventTarget
```

## `ui.desktop.native`

Source: `native/FXCollections.norm`

### `FXCollections`

```norm
class FXCollections
```

#### `FXCollections`

```norm
FXCollections(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

### `fxCollectionsObservableArrayList`

```norm
ObservableList<E>? fxCollectionsObservableArrayList<E>()
```

Type parameters:

- `E`

Returns: `ObservableList<E>?`

### `fxCollectionsSort`

```norm
Void fxCollectionsSort<T>(ObservableList<T>? arg0, Function<Integer(T?, T?)>? arg1)
```

Type parameters:

- `T`

Parameters:

- `arg0`: `ObservableList<T>?`
- `arg1`: `Function<Integer(T?, T?)>?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/HBox.norm`

### `HBox`

```norm
class HBox extends Pane implements EventTarget, Styleable
```

#### `HBox`

```norm
HBox(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getChildren`

```norm
ObservableList<Node>? getChildren()
```

Returns: `ObservableList<Node>?`

### `hBoxNew`

```norm
HBox hBoxNew(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `HBox`

### `hBoxGetHgrow`

```norm
Priority? hBoxGetHgrow(Node? arg0)
```

Parameters:

- `arg0`: `Node?`

Returns: `Priority?`

### `hBoxSetHgrow`

```norm
Void hBoxSetHgrow(Node? arg0, Priority? arg1)
```

Parameters:

- `arg0`: `Node?`
- `arg1`: `Priority?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/Insets.norm`

### `Insets`

```norm
class Insets implements Interpolatable<Insets>
```

#### `Insets`

```norm
Insets(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

### `insetsNew`

```norm
Insets insetsNew(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Insets`

### `insetsNew`

```norm
Insets insetsNew(Double arg0, Double arg1, Double arg2, Double arg3)
```

Parameters:

- `arg0`: `Double`
- `arg1`: `Double`
- `arg2`: `Double`
- `arg3`: `Double`

Returns: `Insets`

## `ui.desktop.native`

Source: `native/Label.norm`

### `Label`

```norm
class Label extends Labeled implements Skinnable, EventTarget, Styleable
```

#### `Label`

```norm
Label(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

### `labelNew`

```norm
Label labelNew()
```

Returns: `Label`

## `ui.desktop.native`

Source: `native/Labeled.norm`

### `Labeled`

```norm
class Labeled extends Control implements Skinnable, EventTarget, Styleable
```

#### `Labeled`

```norm
Labeled(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getText`

```norm
String? getText()
```

Returns: `String?`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setWrapText`

```norm
Void setWrapText(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

## `ui.desktop.native`

Source: `native/Node.norm`

### `Node`

```norm
class Node implements EventTarget, Styleable
```

#### `Node`

```norm
Node(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `applyCss`

```norm
Void applyCss()
```

Returns: `Void`

#### `getId`

```norm
String? getId()
```

Returns: `String?`

#### `getLayoutX`

```norm
Double getLayoutX()
```

Returns: `Double`

#### `getLayoutY`

```norm
Double getLayoutY()
```

Returns: `Double`

#### `getNodeOrientation`

```norm
NodeOrientation? getNodeOrientation()
```

Returns: `NodeOrientation?`

#### `getStyle`

```norm
String? getStyle()
```

Returns: `String?`

#### `getStyleClass`

```norm
ObservableList<String>? getStyleClass()
```

Returns: `ObservableList<String>?`

#### `isDisabled`

```norm
Boolean isDisabled()
```

Returns: `Boolean`

#### `maxHeight`

```norm
Double maxHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `maxWidth`

```norm
Double maxWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `minHeight`

```norm
Double minHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `minWidth`

```norm
Double minWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `prefHeight`

```norm
Double prefHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `prefWidth`

```norm
Double prefWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Double`

#### `requestFocus`

```norm
Void requestFocus()
```

Returns: `Void`

#### `resize`

```norm
Void resize(Double arg0, Double arg1)
```

Parameters:

- `arg0`: `Double`
- `arg1`: `Double`

Returns: `Void`

#### `resizeRelocate`

```norm
Void resizeRelocate(Double arg0, Double arg1, Double arg2, Double arg3)
```

Parameters:

- `arg0`: `Double`
- `arg1`: `Double`
- `arg2`: `Double`
- `arg3`: `Double`

Returns: `Void`

#### `setDisable`

```norm
Void setDisable(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setId`

```norm
Void setId(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setNodeOrientation`

```norm
Void setNodeOrientation(NodeOrientation? arg0)
```

Parameters:

- `arg0`: `NodeOrientation?`

Returns: `Void`

#### `setStyle`

```norm
Void setStyle(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/NodeOrientation.norm`

### `NodeOrientation`

```norm
enum NodeOrientation
```

#### `INHERIT`

```norm
INHERIT
```

#### `LEFT_TO_RIGHT`

```norm
LEFT_TO_RIGHT
```

#### `RIGHT_TO_LEFT`

```norm
RIGHT_TO_LEFT
```

## `ui.desktop.native`

Source: `native/Observable.norm`

### `Observable`

```norm
interface Observable
```

#### `addListener`

```norm
Void addListener(Function<Void(Observable?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(Observable?)>?`

Returns: `Void`

#### `removeListener`

```norm
Void removeListener(Function<Void(Observable?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(Observable?)>?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/ObservableList.norm`

### `ObservableList`

```norm
interface ObservableList<E> extends JavaList<E>, Observable
```

Type parameters:

- `E`

## `ui.desktop.native`

Source: `native/ObservableValue.norm`

### `ObservableValue`

```norm
interface ObservableValue<T> extends Observable
```

Type parameters:

- `T`

#### `getValue`

```norm
T? getValue()
```

Returns: `T?`

## `ui.desktop.native`

Source: `native/Orientation.norm`

### `Orientation`

```norm
enum Orientation
```

#### `HORIZONTAL`

```norm
HORIZONTAL
```

#### `VERTICAL`

```norm
VERTICAL
```

## `ui.desktop.native`

Source: `native/Pane.norm`

### `Pane`

```norm
class Pane extends Region implements EventTarget, Styleable
```

#### `Pane`

```norm
Pane(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getChildren`

```norm
ObservableList<Node>? getChildren()
```

Returns: `ObservableList<Node>?`

## `ui.desktop.native`

Source: `native/Parent.norm`

### `Parent`

```norm
class Parent extends Node implements EventTarget, Styleable
```

#### `Parent`

```norm
Parent(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `layout`

```norm
Void layout()
```

Returns: `Void`

## `ui.desktop.native`

Source: `native/PasswordField.norm`

### `PasswordField`

```norm
class PasswordField extends TextField implements Skinnable, EventTarget, Styleable
```

#### `PasswordField`

```norm
PasswordField(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getText`

```norm
String? getText()
```

Returns: `String?`

#### `getText`

```norm
String? getText(Integer arg0, Integer arg1)
```

Parameters:

- `arg0`: `Integer`
- `arg1`: `Integer`

Returns: `String?`

#### `setPromptText`

```norm
Void setPromptText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `textProperty`

```norm
StringProperty? textProperty()
```

Returns: `StringProperty?`

### `passwordFieldNew`

```norm
PasswordField passwordFieldNew()
```

Returns: `PasswordField`

## `ui.desktop.native`

Source: `native/Platform.norm`

### `Platform`

```norm
class Platform
```

#### `Platform`

```norm
Platform(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

### `platformExit`

```norm
Void platformExit()
```

Returns: `Void`

### `platformIsFxApplicationThread`

```norm
Boolean platformIsFxApplicationThread()
```

Returns: `Boolean`

### `platformRunLater`

```norm
Void platformRunLater(Function<Void()>? arg0)
```

Parameters:

- `arg0`: `Function<Void()>?`

Returns: `Void`

### `platformSetImplicitExit`

```norm
Void platformSetImplicitExit(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

### `platformStartup`

```norm
Void platformStartup(Function<Void()>? arg0)
```

Parameters:

- `arg0`: `Function<Void()>?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/Priority.norm`

### `Priority`

```norm
enum Priority
```

#### `ALWAYS`

```norm
ALWAYS
```

#### `NEVER`

```norm
NEVER
```

#### `SOMETIMES`

```norm
SOMETIMES
```

## `ui.desktop.native`

Source: `native/Region.norm`

### `Region`

```norm
class Region extends Parent implements EventTarget, Styleable
```

#### `Region`

```norm
Region(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getHeight`

```norm
Double getHeight()
```

Returns: `Double`

#### `getPadding`

```norm
Insets? getPadding()
```

Returns: `Insets?`

#### `getWidth`

```norm
Double getWidth()
```

Returns: `Double`

#### `setMaxHeight`

```norm
Void setMaxHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setMaxWidth`

```norm
Void setMaxWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setMinHeight`

```norm
Void setMinHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setMinWidth`

```norm
Void setMinWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setPadding`

```norm
Void setPadding(Insets? arg0)
```

Parameters:

- `arg0`: `Insets?`

Returns: `Void`

#### `setPrefHeight`

```norm
Void setPrefHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setPrefWidth`

```norm
Void setPrefWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

## `ui.desktop.native`

Source: `native/Scene.norm`

### `Scene`

```norm
class Scene implements EventTarget
```

#### `Scene`

```norm
Scene(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getStylesheets`

```norm
ObservableList<String>? getStylesheets()
```

Returns: `ObservableList<String>?`

#### `lookup`

```norm
Node? lookup(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Node?`

### `sceneNew`

```norm
Scene sceneNew(Parent? arg0, Double arg1, Double arg2)
```

Parameters:

- `arg0`: `Parent?`
- `arg1`: `Double`
- `arg2`: `Double`

Returns: `Scene`

## `ui.desktop.native`

Source: `native/ScrollPane.norm`

### `ScrollPane`

```norm
class ScrollPane extends Control implements Skinnable, EventTarget, Styleable
```

#### `ScrollPane`

```norm
ScrollPane(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getContent`

```norm
Node? getContent()
```

Returns: `Node?`

#### `getHbarPolicy`

```norm
ScrollPaneScrollBarPolicy? getHbarPolicy()
```

Returns: `ScrollPaneScrollBarPolicy?`

#### `getVbarPolicy`

```norm
ScrollPaneScrollBarPolicy? getVbarPolicy()
```

Returns: `ScrollPaneScrollBarPolicy?`

#### `isFitToHeight`

```norm
Boolean isFitToHeight()
```

Returns: `Boolean`

#### `isFitToWidth`

```norm
Boolean isFitToWidth()
```

Returns: `Boolean`

#### `setContent`

```norm
Void setContent(Node? arg0)
```

Parameters:

- `arg0`: `Node?`

Returns: `Void`

#### `setFitToHeight`

```norm
Void setFitToHeight(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setFitToWidth`

```norm
Void setFitToWidth(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setHbarPolicy`

```norm
Void setHbarPolicy(ScrollPaneScrollBarPolicy? arg0)
```

Parameters:

- `arg0`: `ScrollPaneScrollBarPolicy?`

Returns: `Void`

#### `setVbarPolicy`

```norm
Void setVbarPolicy(ScrollPaneScrollBarPolicy? arg0)
```

Parameters:

- `arg0`: `ScrollPaneScrollBarPolicy?`

Returns: `Void`

### `scrollPaneNew`

```norm
ScrollPane scrollPaneNew()
```

Returns: `ScrollPane`

## `ui.desktop.native`

Source: `native/ScrollPaneScrollBarPolicy.norm`

### `ScrollPaneScrollBarPolicy`

```norm
enum ScrollPaneScrollBarPolicy
```

#### `ALWAYS`

```norm
ALWAYS
```

#### `AS_NEEDED`

```norm
AS_NEEDED
```

#### `NEVER`

```norm
NEVER
```

## `ui.desktop.native`

Source: `native/SingleSelectionModel.norm`

### `SingleSelectionModel`

```norm
class SingleSelectionModel<T> extends SelectionModel<T>
```

Type parameters:

- `T`

#### `SingleSelectionModel`

```norm
SingleSelectionModel(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getSelectedIndex`

```norm
Integer getSelectedIndex()
```

Returns: `Integer`

#### `select`

```norm
Void select(Integer arg0)
```

Parameters:

- `arg0`: `Integer`

Returns: `Void`

#### `selectedIndexProperty`

```norm
ReadOnlyIntegerProperty? selectedIndexProperty()
```

Returns: `ReadOnlyIntegerProperty?`

## `ui.desktop.native`

Source: `native/StackPane.norm`

### `StackPane`

```norm
class StackPane extends Pane implements EventTarget, Styleable
```

#### `StackPane`

```norm
StackPane(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getChildren`

```norm
ObservableList<Node>? getChildren()
```

Returns: `ObservableList<Node>?`

#### `setPadding`

```norm
Void setPadding(Insets? arg0)
```

Parameters:

- `arg0`: `Insets?`

Returns: `Void`

### `stackPaneNew`

```norm
StackPane stackPaneNew()
```

Returns: `StackPane`

## `ui.desktop.native`

Source: `native/Stage.norm`

### `Stage`

```norm
class Stage extends Window implements EventTarget
```

#### `Stage`

```norm
Stage(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `close`

```norm
Void close()
```

Returns: `Void`

#### `hide`

```norm
Void hide()
```

Returns: `Void`

#### `setIconified`

```norm
Void setIconified(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setMinHeight`

```norm
Void setMinHeight(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setMinWidth`

```norm
Void setMinWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setOnCloseRequest`

```norm
Void setOnCloseRequest(Function<Void(WindowEvent?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(WindowEvent?)>?`

Returns: `Void`

#### `setScene`

```norm
Void setScene(Scene? arg0)
```

Parameters:

- `arg0`: `Scene?`

Returns: `Void`

#### `setTitle`

```norm
Void setTitle(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `show`

```norm
Void show()
```

Returns: `Void`

#### `toFront`

```norm
Void toFront()
```

Returns: `Void`

### `stageNew`

```norm
Stage stageNew()
```

Returns: `Stage`

## `ui.desktop.native`

Source: `native/StringProperty.norm`

### `StringProperty`

```norm
class StringProperty extends ReadOnlyStringProperty implements Property<String>, WritableStringValue, ReadOnlyProperty<String>, ObservableStringValue
```

#### `StringProperty`

```norm
StringProperty(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop.native`

Source: `native/Styleable.norm`

### `Styleable`

```norm
interface Styleable
```

## `ui.desktop.native`

Source: `native/Tab.norm`

### `Tab`

```norm
class Tab implements EventTarget, Styleable
```

#### `Tab`

```norm
Tab(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getContent`

```norm
Node? getContent()
```

Returns: `Node?`

#### `setClosable`

```norm
Void setClosable(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setContent`

```norm
Void setContent(Node? arg0)
```

Parameters:

- `arg0`: `Node?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

### `tabNew`

```norm
Tab tabNew()
```

Returns: `Tab`

## `ui.desktop.native`

Source: `native/TabPane.norm`

### `TabPane`

```norm
class TabPane extends Control implements Skinnable, EventTarget, Styleable
```

#### `TabPane`

```norm
TabPane(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getSelectionModel`

```norm
SingleSelectionModel<Tab>? getSelectionModel()
```

Returns: `SingleSelectionModel<Tab>?`

#### `getTabs`

```norm
ObservableList<Tab>? getTabs()
```

Returns: `ObservableList<Tab>?`

### `tabPaneNew`

```norm
TabPane tabPaneNew()
```

Returns: `TabPane`

## `ui.desktop.native`

Source: `native/TextArea.norm`

### `TextArea`

```norm
class TextArea extends TextInputControl implements Skinnable, EventTarget, Styleable
```

#### `TextArea`

```norm
TextArea(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `clear`

```norm
Void clear()
```

Returns: `Void`

#### `getText`

```norm
String? getText()
```

Returns: `String?`

#### `getText`

```norm
String? getText(Integer arg0, Integer arg1)
```

Parameters:

- `arg0`: `Integer`
- `arg1`: `Integer`

Returns: `String?`

#### `setEditable`

```norm
Void setEditable(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `setMaxWidth`

```norm
Void setMaxWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setPrefRowCount`

```norm
Void setPrefRowCount(Integer arg0)
```

Parameters:

- `arg0`: `Integer`

Returns: `Void`

#### `setPromptText`

```norm
Void setPromptText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setWrapText`

```norm
Void setWrapText(Boolean arg0)
```

Parameters:

- `arg0`: `Boolean`

Returns: `Void`

#### `textProperty`

```norm
StringProperty? textProperty()
```

Returns: `StringProperty?`

### `textAreaNew`

```norm
TextArea textAreaNew()
```

Returns: `TextArea`

## `ui.desktop.native`

Source: `native/TextField.norm`

### `TextField`

```norm
class TextField extends TextInputControl implements Skinnable, EventTarget, Styleable
```

#### `TextField`

```norm
TextField(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `clear`

```norm
Void clear()
```

Returns: `Void`

#### `getText`

```norm
String? getText()
```

Returns: `String?`

#### `getText`

```norm
String? getText(Integer arg0, Integer arg1)
```

Parameters:

- `arg0`: `Integer`
- `arg1`: `Integer`

Returns: `String?`

#### `setMaxWidth`

```norm
Void setMaxWidth(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `Void`

#### `setOnAction`

```norm
Void setOnAction(Function<Void(ActionEvent?)>? arg0)
```

Parameters:

- `arg0`: `Function<Void(ActionEvent?)>?`

Returns: `Void`

#### `setPromptText`

```norm
Void setPromptText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `textProperty`

```norm
StringProperty? textProperty()
```

Returns: `StringProperty?`

### `textFieldNew`

```norm
TextField textFieldNew()
```

Returns: `TextField`

## `ui.desktop.native`

Source: `native/TextInputControl.norm`

### `TextInputControl`

```norm
class TextInputControl extends Control implements Skinnable, EventTarget, Styleable
```

#### `TextInputControl`

```norm
TextInputControl(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getText`

```norm
String? getText()
```

Returns: `String?`

#### `getText`

```norm
String? getText(Integer arg0, Integer arg1)
```

Parameters:

- `arg0`: `Integer`
- `arg1`: `Integer`

Returns: `String?`

#### `setPromptText`

```norm
Void setPromptText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `setText`

```norm
Void setText(String? arg0)
```

Parameters:

- `arg0`: `String?`

Returns: `Void`

#### `textProperty`

```norm
StringProperty? textProperty()
```

Returns: `StringProperty?`

## `ui.desktop.native`

Source: `native/VBox.norm`

### `VBox`

```norm
class VBox extends Pane implements EventTarget, Styleable
```

#### `VBox`

```norm
VBox(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

#### `getChildren`

```norm
ObservableList<Node>? getChildren()
```

Returns: `ObservableList<Node>?`

### `vBoxNew`

```norm
VBox vBoxNew(Double arg0)
```

Parameters:

- `arg0`: `Double`

Returns: `VBox`

### `vBoxSetVgrow`

```norm
Void vBoxSetVgrow(Node? arg0, Priority? arg1)
```

Parameters:

- `arg0`: `Node?`
- `arg1`: `Priority?`

Returns: `Void`

## `ui.desktop.native`

Source: `native/Window.norm`

### `Window`

```norm
class Window implements EventTarget
```

#### `Window`

```norm
Window(__JarBindingToken token)
```

Parameters:

- `token`: `__JarBindingToken`

## `ui.desktop`

Source: `node.norm`

### `FxNode`

```norm
class FxNode implements Resource
```

#### `FxNode`

```norm
FxNode(FxApplication application, Node node, Boolean managed)
```

Parameters:

- `application`: `FxApplication`
- `node`: `Node`
- `managed`: `Boolean`

#### `node`

```norm
Node node()
```

Returns: `Node`

#### `nativeNode`

```norm
Node nativeNode()
```

Returns: `Node`

#### `dispatch`

```norm
Void dispatch(Function<Void()> callback)
```

Parameters:

- `callback`: `Function<Void()>`

Returns: `Void`

#### `focus`

```norm
Void focus()
```

Returns: `Void`

#### `readText`

```norm
String readText()
```

Returns: `String`

#### `clear`

```norm
Void clear()
```

Returns: `Void`

#### `enabled`

```norm
Void enabled(Boolean enabled)
```

Parameters:

- `enabled`: `Boolean`

Returns: `Void`

#### `text`

```norm
Void text(String text)
```

Parameters:

- `text`: `String`

Returns: `Void`

#### `placeholder`

```norm
Void placeholder(String text)
```

Parameters:

- `text`: `String`

Returns: `Void`

#### `checked`

```norm
Void checked(Boolean selected)
```

Parameters:

- `selected`: `Boolean`

Returns: `Void`

#### `textAreaConfiguration`

```norm
Void textAreaConfiguration(Integer rows, Boolean editable)
```

Parameters:

- `rows`: `Integer`
- `editable`: `Boolean`

Returns: `Void`

#### `onAction`

```norm
Void onAction(Function<Void()> callback)
```

Parameters:

- `callback`: `Function<Void()>`

Returns: `Void`

#### `onText`

```norm
Void onText(Function<Void(String)> callback)
```

Parameters:

- `callback`: `Function<Void(String)>`

Returns: `Void`

#### `onToggle`

```norm
Void onToggle(Function<Void(Boolean)> callback)
```

Parameters:

- `callback`: `Function<Void(Boolean)>`

Returns: `Void`

#### `appearance`

```norm
Void appearance(View view)
```

Parameters:

- `view`: `View`

Returns: `Void`

#### `children`

```norm
Void children(List<FxNode> children)
```

Parameters:

- `children`: `List<FxNode>`

Returns: `Void`

#### `close`

```norm
Void close()
```

Returns: `Void`

## `ui.desktop`

Source: `runtime.norm`

### `FxRuntime`

```norm
class FxRuntime
```

### `withFxRuntime`

```norm
Void withFxRuntime(Function<Void()> action)
```

Parameters:

- `action`: `Function<Void()>`

Returns: `Void`

### `checkFxThread`

```norm
Void checkFxThread()
```

Returns: `Void`

## `ui.desktop`

Source: `theme.norm`

### `fxConfigurationStyle`

```norm
String fxConfigurationStyle(UiConfiguration configuration)
```

Parameters:

- `configuration`: `UiConfiguration`

Returns: `String`

### `fxConfigureNode`

```norm
Void fxConfigureNode(Node node, UiConfiguration configuration)
```

Parameters:

- `node`: `Node`
- `configuration`: `UiConfiguration`

Returns: `Void`

### `fxThemeStyle`

```norm
String fxThemeStyle(Theme snapshot, WidgetRole role)
```

Parameters:

- `snapshot`: `Theme`
- `role`: `WidgetRole`

Returns: `String`

### `toneName`

```norm
String toneName(Tone tone)
```

Parameters:

- `tone`: `Tone`

Returns: `String`

### `appearanceName`

```norm
String appearanceName(Appearance appearance)
```

Parameters:

- `appearance`: `Appearance`

Returns: `String`

### `themeCss`

```norm
String themeCss(Theme theme)
```

Parameters:

- `theme`: `Theme`

Returns: `String`

