package dev.normlanguage.ui.desktop;

import javafx.geometry.Insets;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LayoutPaneTest extends FxTest {
    private static Region item(double width, double height) {
        var item = new Region();
        item.setMinSize(0, 0);
        item.setPrefSize(width, height);
        return item;
    }
    @Test void distributesWeightedGrowthAndShrink() throws Exception {
        fx(() -> {
            var pane = new LayoutPane(LayoutMode.FLEX);
            pane.configureFlex(false, 10, MainAlignment.START, CrossAlignment.STRETCH, false);
            var a = item(100, 30); var b = item(100, 30);
            pane.getChildren().addAll(a, b);
            pane.flexItem(a, 1, 1); pane.flexItem(b, 3, 1);
            pane.resize(410, 80); pane.layout();
            assertEquals(150, a.getWidth(), .01); assertEquals(250, b.getWidth(), .01);
            assertEquals(80, a.getHeight(), .01);
            pane.resize(110, 80); pane.layout();
            assertEquals(50, a.getWidth(), .01); assertEquals(50, b.getWidth(), .01);
            assertEquals(60, b.getLayoutX(), .01);
        });
    }
    @Test void wrapsAndDistributesEachLine() throws Exception {
        fx(() -> {
            var pane = new LayoutPane(LayoutMode.FLEX);
            pane.configureFlex(false, 10, MainAlignment.SPACE_BETWEEN, CrossAlignment.START, true);
            var a = item(80, 30); var b = item(80, 40); var c = item(80, 20);
            pane.getChildren().addAll(a, b, c);
            pane.resize(200, 100); pane.layout();
            assertEquals(120, b.getLayoutX(), .01); assertEquals(50, c.getLayoutY(), .01);
        });
    }
    @Test void recomputesResponsiveGridAndSpansWithoutReplacingChildren() throws Exception {
        fx(() -> {
            var pane = new LayoutPane(LayoutMode.GRID);
            pane.configureGrid(1, 10);
            pane.breakpoint(400, 3);
            var a = item(100, 30); var b = item(100, 50); var c = item(100, 20);
            pane.getChildren().addAll(a,b,c); pane.gridItem(a,2);
            pane.resize(500, 100); pane.layout();
            assertEquals(330, a.getWidth(), .01); assertEquals(340, b.getLayoutX(), .01);
            assertEquals(60, c.getLayoutY(), .01);
            pane.resize(300, 180); pane.layout();
            assertEquals(300, a.getWidth(), .01); assertEquals(40, b.getLayoutY(), .01);
            assertSame(a, pane.getChildren().getFirst());
        });
    }
    @Test void measuresMasonryAndBoxPadding() throws Exception {
        fx(() -> {
            var pane = new LayoutPane(LayoutMode.MASONRY); pane.configureMasonry(100,10);
            var a = item(100,80); var b=item(100,30); var c=item(100,40);
            pane.getChildren().addAll(a,b,c); pane.resize(210,200); pane.layout();
            assertEquals(110,c.getLayoutX(),.01); assertEquals(40,c.getLayoutY(),.01);
            assertEquals(80,pane.prefHeight(210),.01);
            var box = new LayoutPane(LayoutMode.BOX); box.setPadding(new Insets(4,8,12,16));
            var child = item(100,30); box.getChildren().add(child); box.resize(200,100); box.layout();
            assertEquals(16,child.getLayoutX(),.01); assertEquals(176,child.getWidth(),.01);
        });
    }
    @Test void showsRealSceneAndPositionsDockRegions() throws Exception {
        fx(() -> {
            var pane=new LayoutPane(LayoutMode.DOCK);
            var top=item(100,20); var left=item(40,50); var center=item(100,100);
            pane.getChildren().addAll(top,left,center); pane.dockItem(top,DockArea.TOP); pane.dockItem(left,DockArea.LEFT); pane.dockItem(center,DockArea.CENTER);
            var scene=new Scene(pane,300,200); pane.applyCss(); pane.resize(300,200); pane.layout();
            assertEquals(20,left.getLayoutY(),.01); assertEquals(40,center.getLayoutX(),.01); assertEquals(260,center.getWidth(),.01);
            assertNotNull(scene.snapshot(null));
        });
    }
    @Test void respectsIntrinsicLimitsAndRedistributesSaturatedGrowth() throws Exception {
        fx(() -> {
            var pane=new LayoutPane(LayoutMode.FLEX);
            pane.configureFlex(false,10,MainAlignment.START,CrossAlignment.STRETCH,false);
            var a=item(100,20); var b=item(100,20);
            a.setMinWidth(60); a.setMaxWidth(120); b.setMinWidth(40);
            pane.getChildren().addAll(a,b); pane.flexItem(a,1,1); pane.flexItem(b,1,1);
            assertEquals(110,pane.minWidth(-1),.01);
            pane.resize(410,100); pane.layout();
            assertEquals(120,a.getWidth(),.01); assertEquals(280,b.getWidth(),.01);
            pane.resize(100,100); pane.layout();
            assertEquals(60,a.getWidth(),.01); assertEquals(40,b.getWidth(),.01);
        });
    }
    @Test void measuresContentBiasAfterShrinkingAndConstrainsBoxes() throws Exception {
        fx(() -> {
            var pane=new LayoutPane(LayoutMode.FLEX);
            pane.configureFlex(false,0,MainAlignment.START,CrossAlignment.START,false);
            Region biased=new Region() {
                @Override public javafx.geometry.Orientation getContentBias() { return javafx.geometry.Orientation.HORIZONTAL; }
                @Override protected double computePrefWidth(double height) { return 200; }
                @Override protected double computePrefHeight(double width) { return width < 0 ? 20 : 4000/width; }
            };
            biased.setMinWidth(0); biased.setMinHeight(0);
            pane.getChildren().add(biased);
            assertEquals(40,pane.prefHeight(100),.01);
            assertEquals(20,pane.prefHeight(-1),.01);
            var box=new LayoutPane(LayoutMode.BOX); box.getChildren().add(item(100,30));
            box.configureWidth(DimensionMode.CONTENT,0,0,80); box.configureHeight(DimensionMode.FILL,0,0,200);
            assertEquals(80,box.maxWidth(-1),.01);
            box.configureWidth(DimensionMode.FIXED,60,0,200); box.configureHeight(DimensionMode.FIXED,40,0,200);
            assertEquals(60,box.minWidth(-1),.01); assertEquals(60,box.maxWidth(-1),.01);
        });
    }
    @Test void gridAndMasonryMeasureChildMinimumHeights() throws Exception {
        fx(() -> {
            for(var mode:java.util.List.of(LayoutMode.GRID,LayoutMode.MASONRY)) {
                var pane=new LayoutPane(mode); pane.configureGrid(1,0); pane.configureMasonry(100,0);
                var item=item(100,20); item.setMinHeight(60); pane.getChildren().add(item);
                assertEquals(60,pane.prefHeight(100),.01);
                assertEquals(60,pane.minHeight(100),.01);
            }
        });
    }
    @Test void measuresWrappedVerticalFlexWidth() throws Exception {
        fx(() -> {
            var pane=new LayoutPane(LayoutMode.FLEX);
            pane.configureFlex(true,10,MainAlignment.START,CrossAlignment.START,true);
            pane.getChildren().addAll(item(20,80),item(20,80),item(20,80));
            assertEquals(80,pane.prefWidth(100),.01);
            pane.resize(100,100); pane.layout();
            assertEquals(60,pane.getChildren().get(2).getLayoutX(),.01);
        });
    }
    @Test void measuresMinimumGridRowsAndDockRegions() throws Exception {
        fx(() -> {
            var grid=new LayoutPane(LayoutMode.GRID); grid.configureGrid(1,10);
            var a=item(100,20); a.setMinHeight(60); var b=item(100,20); b.setMinHeight(60);
            grid.getChildren().addAll(a,b);
            assertEquals(130,grid.minHeight(100),.01);
            var dock=new LayoutPane(LayoutMode.DOCK);
            var left=item(40,20); left.setMinWidth(40); var center=item(100,20); center.setMinWidth(100);
            var top=item(100,20); top.setMinHeight(20); center.setMinHeight(50);
            dock.getChildren().addAll(top,left,center); dock.dockItem(top,DockArea.TOP); dock.dockItem(left,DockArea.LEFT); dock.dockItem(center,DockArea.CENTER);
            assertEquals(140,dock.minWidth(-1),.01); assertEquals(70,dock.minHeight(140),.01);
        });
    }
}
