package dev.normlanguage.ui.fx;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public final class LayoutPane extends Pane {
    private final LayoutMode mode;
    private boolean vertical;
    private boolean wrap;
    private double gap;
    private MainAlignment main = MainAlignment.START;
    private CrossAlignment cross = CrossAlignment.START;
    private CrossAlignment horizontal = CrossAlignment.STRETCH;
    private CrossAlignment stackVertical = CrossAlignment.STRETCH;
    private int columns = 1;
    private double minimumColumnWidth = 180;
    private final Map<Node, FlexSizing> flexItems = new IdentityHashMap<>();
    private final Map<Node, Integer> gridItems = new IdentityHashMap<>();
    private final Map<Node, DockArea> dockItems = new IdentityHashMap<>();
    private final TreeMap<Double, Integer> breakpoints = new TreeMap<>();
    private record FlexSizing(double grow, double shrink) {}
    private record GridPlacement(Node node, int row, int column, int span) {}

    public LayoutPane(LayoutMode mode) {
        this.mode = java.util.Objects.requireNonNull(mode);
        getChildren().addListener((javafx.collections.ListChangeListener<Node>) change -> {
            flexItems.keySet().retainAll(getChildren());
            gridItems.keySet().retainAll(getChildren());
            dockItems.keySet().retainAll(getChildren());
        });
    }
    public void configureFlex(boolean vertical, double gap, MainAlignment main, CrossAlignment cross, boolean wrap) {
        this.vertical = vertical; this.gap = gap; this.main = main; this.cross = cross; this.wrap = wrap; requestLayout();
    }
    public void flexItem(Node child, double grow, double shrink) { flexItems.put(child, new FlexSizing(grow, shrink)); requestLayout(); }
    public void configureGrid(int columns, double gap) { this.columns = columns; this.gap = gap; requestLayout(); }
    public void gridItem(Node child, int span) { gridItems.put(child, span); requestLayout(); }
    public void clearBreakpoints() { breakpoints.clear(); requestLayout(); }
    public void breakpoint(double minimumWidth, int columns) { breakpoints.put(minimumWidth, columns); requestLayout(); }
    public void configureStack(CrossAlignment horizontal, CrossAlignment vertical) { this.horizontal = horizontal; stackVertical = vertical; requestLayout(); }
    public void dockItem(Node child, DockArea area) { dockItems.put(child, area); requestLayout(); }
    public void configureMasonry(double minimumColumnWidth, double gap) { this.minimumColumnWidth = minimumColumnWidth; this.gap = gap; requestLayout(); }
    @Override public Orientation getContentBias() { return vertical && mode == LayoutMode.FLEX ? Orientation.VERTICAL : Orientation.HORIZONTAL; }
    public void configureWidth(DimensionMode mode, double value, double minimum, double maximum) {
        setMinWidth(mode == DimensionMode.FIXED ? value : minimum);
        setPrefWidth(mode == DimensionMode.FIXED ? value : USE_COMPUTED_SIZE);
        boxWidth = mode; boxMaxWidth = maximum; setMaxWidth(USE_COMPUTED_SIZE); requestLayout();
    }
    public void configureHeight(DimensionMode mode, double value, double minimum, double maximum) {
        setMinHeight(mode == DimensionMode.FIXED ? value : minimum);
        setPrefHeight(mode == DimensionMode.FIXED ? value : USE_COMPUTED_SIZE);
        boxHeight = mode; boxMaxHeight = maximum; setMaxHeight(USE_COMPUTED_SIZE); requestLayout();
    }
    private DimensionMode boxWidth = DimensionMode.FILL, boxHeight = DimensionMode.FILL;
    private double boxMaxWidth = Double.MAX_VALUE, boxMaxHeight = Double.MAX_VALUE;
    @Override protected double computeMaxWidth(double height) {
        return mode == LayoutMode.BOX ? Math.min(boxMaxWidth, boxWidth == DimensionMode.FILL ? Double.MAX_VALUE : prefWidth(height)) : Double.MAX_VALUE;
    }
    @Override protected double computeMaxHeight(double width) {
        return mode == LayoutMode.BOX ? Math.min(boxMaxHeight, boxHeight == DimensionMode.FILL ? Double.MAX_VALUE : prefHeight(width)) : Double.MAX_VALUE;
    }
    @Override protected double computeMinWidth(double height) {
        var children = getManagedChildren();
        double insets = snappedLeftInset() + snappedRightInset();
        if (mode == LayoutMode.FLEX && !vertical && !wrap) return insets + children.stream().mapToDouble(n -> n.minWidth(height)).sum() + Math.max(0, children.size()-1)*gap;
        if (mode == LayoutMode.GRID) return insets + columns*children.stream().mapToDouble(n -> n.minWidth(height)/gridItems.getOrDefault(n,1)).max().orElse(0) + Math.max(0,columns-1)*gap;
        if (mode == LayoutMode.DOCK) {
            double middle=0,edges=0;
            for(var child:children) { var area=dockItems.getOrDefault(child,DockArea.CENTER); if(area==DockArea.TOP || area==DockArea.BOTTOM) edges=Math.max(edges,child.minWidth(height)); else middle+=child.minWidth(height); }
            return insets+Math.max(middle,edges);
        }
        return insets + children.stream().mapToDouble(n -> n.minWidth(height)).max().orElse(0);
    }
    @Override protected double computeMinHeight(double width) {
        var children = getManagedChildren();
        double insets = snappedTopInset() + snappedBottomInset();
        double available = width < 0 ? -1 : Math.max(0,width-snappedLeftInset()-snappedRightInset());
        if (mode == LayoutMode.FLEX && vertical && !wrap) return insets + children.stream().mapToDouble(n -> n.minHeight(available)).sum() + Math.max(0,children.size()-1)*gap;
        double resolvedWidth=available < 0 ? computePrefWidth(-1)-snappedLeftInset()-snappedRightInset():available;
        if (mode == LayoutMode.GRID) return insets+grid(children,resolvedWidth,false,true);
        if (mode == LayoutMode.MASONRY) return insets+masonry(children,resolvedWidth,false,true);
        if (mode == LayoutMode.DOCK) {
            double middle=0,edges=0;
            for(var child:children) { var area=dockItems.getOrDefault(child,DockArea.CENTER); if(area==DockArea.TOP || area==DockArea.BOTTOM) edges+=child.minHeight(available); else middle=Math.max(middle,child.minHeight(available)); }
            return insets+middle+edges;
        }
        return insets + children.stream().mapToDouble(n -> n.minHeight(available)).max().orElse(0);
    }
    @Override protected double computePrefWidth(double height) {
        double insets = snappedLeftInset() + snappedRightInset();
        var children = getManagedChildren();
        return insets + switch (mode) {
            case FLEX -> vertical ? verticalFlexWidth(children,height < 0 ? -1 : Math.max(0,height-snappedTopInset()-snappedBottomInset()))
                    : children.stream().mapToDouble(n -> n.prefWidth(-1)).sum() + Math.max(0,children.size()-1)*gap;
            case GRID -> columns * children.stream().mapToDouble(n -> n.prefWidth(-1) / gridItems.getOrDefault(n,1)).max().orElse(0) + Math.max(0,columns-1)*gap;
            case MASONRY -> minimumColumnWidth;
            case DOCK -> dockPreferredWidth(children);
            case BOX, STACK -> children.stream().mapToDouble(n -> n.prefWidth(-1)).max().orElse(0);
        };
    }
    @Override protected double computePrefHeight(double width) {
        double insets = snappedTopInset()+snappedBottomInset();
        double available = width < 0 ? computePrefWidth(-1)-snappedLeftInset()-snappedRightInset() : Math.max(0,width-snappedLeftInset()-snappedRightInset());
        var children=getManagedChildren();
        return insets + switch(mode) {
            case FLEX -> vertical ? children.stream().mapToDouble(n -> bounded(n.minHeight(available),n.prefHeight(available),n.maxHeight(available))).sum()+Math.max(0,children.size()-1)*gap : flexHeight(children,available);
            case GRID -> grid(children,available,false,false);
            case MASONRY -> masonry(children,available,false,false);
            case DOCK -> dockPreferredHeight(children,available);
            case BOX,STACK -> children.stream().mapToDouble(n -> n.prefHeight(available)).max().orElse(0);
        };
    }
    @Override protected void layoutChildren() {
        double width=Math.max(0,getWidth()-snappedLeftInset()-snappedRightInset());
        double height=Math.max(0,getHeight()-snappedTopInset()-snappedBottomInset());
        var children=getManagedChildren();
        switch(mode) {
            case FLEX -> flex(children,width,height);
            case GRID -> grid(children,width,true,false);
            case MASONRY -> masonry(children,width,true,false);
            case BOX -> children.forEach(child -> place(child,snappedLeftInset(),snappedTopInset(),width,height,CrossAlignment.STRETCH,CrossAlignment.STRETCH));
            case STACK -> children.forEach(child -> place(child,snappedLeftInset(),snappedTopInset(),width,height,horizontal,stackVertical));
            case DOCK -> dock(children,width,height);
        }
    }
    private double base(Node child, double cross) { return vertical ? bounded(child.minHeight(cross),child.prefHeight(cross),child.maxHeight(cross)) : bounded(child.minWidth(cross),child.prefWidth(cross),child.maxWidth(cross)); }
    private double crossSize(Node child,double mainSize) { return vertical ? child.prefWidth(mainSize) : child.prefHeight(mainSize); }
    private List<List<Node>> lines(List<Node> children,double available,double availableCross) {
        var lines=new ArrayList<List<Node>>(); var line=new ArrayList<Node>(); double used=0;
        for(var child:children) {
            double size=base(child,availableCross);
            if(wrap && !line.isEmpty() && used+gap+size>available) { lines.add(line); line=new ArrayList<>(); used=0; }
            used+=size+(line.isEmpty()?0:gap); line.add(child);
        }
        if(!line.isEmpty()) lines.add(line);
        return lines;
    }
    private double verticalFlexWidth(List<Node> children,double available) {
        if(available < 0) return children.stream().mapToDouble(n -> bounded(n.minWidth(-1),n.prefWidth(-1),n.maxWidth(-1))).max().orElse(0);
        double result=0;
        for(var line:lines(children,available,-1)) {
            double[] sizes=flexSizes(line,available,-1); double lineWidth=0;
            for(int i=0;i<sizes.length;i++) lineWidth=Math.max(lineWidth,bounded(line.get(i).minWidth(sizes[i]),line.get(i).prefWidth(sizes[i]),line.get(i).maxWidth(sizes[i])));
            result=wrap?result+lineWidth+gap:Math.max(result,lineWidth);
        }
        return wrap?Math.max(0,result-(children.isEmpty()?0:gap)):result;
    }
    private double flexHeight(List<Node> children,double available) {
        double result=0;
        for(var line:lines(children,available,-1)) {
            double[] sizes = flexSizes(line,available,-1);
            double lineHeight=0;
            for(int i=0;i<sizes.length;i++) lineHeight=Math.max(lineHeight,bounded(line.get(i).minHeight(sizes[i]),line.get(i).prefHeight(sizes[i]),line.get(i).maxHeight(sizes[i])));
            result+=lineHeight+gap;
        }
        return Math.max(0,result-(children.isEmpty()?0:gap));
    }
    private void flex(List<Node> children,double width,double height) {
        double available=vertical?height:width, availableCross=vertical?width:height;
        var lines=lines(children,available,availableCross); double crossPosition=vertical?snappedLeftInset():snappedTopInset();
        for(var line:lines) {
            double[] sizes=flexSizes(line,available,availableCross);
            double extra=Math.max(0,available-Math.max(0,line.size()-1)*gap-Arrays.stream(sizes).sum());
            double offset=switch(main) { case CENTER -> extra/2; case END -> extra; case SPACE_AROUND -> line.isEmpty()?0:extra/(2*line.size()); case SPACE_EVENLY -> extra/(line.size()+1); default -> 0; };
            double step=gap+switch(main) { case SPACE_BETWEEN -> line.size()>1?extra/(line.size()-1):0; case SPACE_AROUND -> line.isEmpty()?0:extra/line.size(); case SPACE_EVENLY -> extra/(line.size()+1); default -> 0; };
            double lineCross=wrap?0:availableCross;
            if(wrap) for(int i=0;i<sizes.length;i++) lineCross=Math.max(lineCross,crossSize(line.get(i),sizes[i]));
            double position=(vertical?snappedTopInset():snappedLeftInset())+offset;
            for(int i=0;i<sizes.length;i++) {
                var child=line.get(i); double wanted=cross==CrossAlignment.STRETCH?lineCross:Math.min(lineCross,crossSize(child,sizes[i]));
                double maximum=vertical?child.maxWidth(sizes[i]):child.maxHeight(sizes[i]);
                double minimum=vertical?child.minWidth(sizes[i]):child.minHeight(sizes[i]);
                double size=Math.max(minimum,Math.min(maximum,wanted));
                double crossOffset=crossPosition+alignmentOffset(cross,lineCross-size);
                if(vertical) child.resizeRelocate(crossOffset,position,size,sizes[i]); else child.resizeRelocate(position,crossOffset,sizes[i],size);
                position+=sizes[i]+step;
            }
            crossPosition+=lineCross+gap;
        }
    }
    private double[] flexSizes(List<Node> line,double available,double availableCross) {
            double[] sizes=line.stream().mapToDouble(n -> base(n,availableCross)).toArray();
            double remaining=available-Math.max(0,line.size()-1)*gap-Arrays.stream(sizes).sum();
            double originalRemaining=remaining;
            boolean[] frozen=new boolean[sizes.length];
            for(int round=0;round<sizes.length && Math.abs(remaining)>.0001;round++) {
                double weight=0;
                for(int i=0;i<sizes.length;i++) if(!frozen[i]) {
                    var sizing=flexItems.getOrDefault(line.get(i),new FlexSizing(0,1));
                    weight+=originalRemaining>0?sizing.grow:sizing.shrink*base(line.get(i),availableCross);
                }
                if(weight==0) break;
                double consumed=0;
                for(int i=0;i<sizes.length;i++) if(!frozen[i]) {
                    var child=line.get(i); var sizing=flexItems.getOrDefault(child,new FlexSizing(0,1));
                    double proportion=originalRemaining>0?sizing.grow:sizing.shrink*base(child,availableCross);
                    double desired=sizes[i]+remaining*proportion/weight;
                    double minimum=vertical?child.minHeight(availableCross):child.minWidth(availableCross);
                    double maximum=vertical?child.maxHeight(availableCross):child.maxWidth(availableCross);
                    double next=Math.max(minimum,Math.min(maximum,desired));
                    consumed+=next-sizes[i]; sizes[i]=next;
                    if(next!=desired || proportion==0) frozen[i]=true;
                }
                remaining-=consumed;
            }
        return sizes;
    }
    private static double bounded(double minimum,double preferred,double maximum) { return Math.max(minimum,Math.min(maximum,preferred)); }
    private int columnCount(double width) { var entry=breakpoints.floorEntry(width); return entry==null?columns:entry.getValue(); }
    private double grid(List<Node> children,double width,boolean place,boolean minimum) {
        int count=columnCount(width), row=0,column=0; var placements=new ArrayList<GridPlacement>();
        for(var child:children) {
            int span=Math.min(count,gridItems.getOrDefault(child,1));
            if(column+span>count) { row++; column=0; }
            placements.add(new GridPlacement(child,row,column,span)); column+=span;
            if(column==count) { row++; column=0; }
        }
        int rows=placements.isEmpty()?0:placements.getLast().row+1; double[] heights=new double[rows];
        double cell=Math.max(0,(width-(count-1)*gap)/count);
        for(var item:placements) heights[item.row]=Math.max(heights[item.row],minimum ? item.node.minHeight(item.span*cell+(item.span-1)*gap) : bounded(item.node.minHeight(item.span*cell+(item.span-1)*gap),item.node.prefHeight(item.span*cell+(item.span-1)*gap),item.node.maxHeight(item.span*cell+(item.span-1)*gap)));
        double[] positions=new double[rows]; double total=0;
        for(int i=0;i<rows;i++) { positions[i]=total; total+=heights[i]+gap; }
        if(place) for(var item:placements) place(item.node,snappedLeftInset()+item.column*(cell+gap),snappedTopInset()+positions[item.row],item.span*cell+(item.span-1)*gap,heights[item.row],CrossAlignment.STRETCH,CrossAlignment.START);
        return Math.max(0,total-(rows==0?0:gap));
    }
    private double masonry(List<Node> children,double width,boolean place,boolean minimum) {
        int count=Math.max(1,(int)Math.floor((width+gap)/(minimumColumnWidth+gap)));
        double cell=Math.max(0,(width-(count-1)*gap)/count); double[] heights=new double[count];
        for(var child:children) {
            int column=0; for(int i=1;i<count;i++) if(heights[i]<heights[column]) column=i;
            double height=minimum ? child.minHeight(cell) : bounded(child.minHeight(cell),child.prefHeight(cell),child.maxHeight(cell));
            if(place) place(child,snappedLeftInset()+column*(cell+gap),snappedTopInset()+heights[column],cell,height,CrossAlignment.STRETCH,CrossAlignment.START);
            heights[column]+=height+gap;
        }
        return Math.max(0,Arrays.stream(heights).max().orElse(0)-(children.isEmpty()?0:gap));
    }
    private void dock(List<Node> children,double width,double height) {
        double x=snappedLeftInset(),y=snappedTopInset();
        for(var area:List.of(DockArea.TOP,DockArea.BOTTOM,DockArea.LEFT,DockArea.RIGHT)) for(var child:children) if(dockItems.getOrDefault(child,DockArea.CENTER)==area) {
            if(area==DockArea.TOP || area==DockArea.BOTTOM) {
                double used=Math.min(height,child.prefHeight(width));
                place(child,x,area==DockArea.TOP?y:y+height-used,width,used,CrossAlignment.STRETCH,CrossAlignment.STRETCH);
                if(area==DockArea.TOP) y+=used; height-=used;
            } else {
                double used=Math.min(width,child.prefWidth(height));
                place(child,area==DockArea.LEFT?x:x+width-used,y,used,height,CrossAlignment.STRETCH,CrossAlignment.STRETCH);
                if(area==DockArea.LEFT) x+=used; width-=used;
            }
        }
        for(var child:children) if(dockItems.getOrDefault(child,DockArea.CENTER)==DockArea.CENTER) place(child,x,y,width,height,CrossAlignment.STRETCH,CrossAlignment.STRETCH);
    }
    private double dockPreferredWidth(List<Node> children) {
        double middle=0,edges=0;
        for(var child:children) { var area=dockItems.getOrDefault(child,DockArea.CENTER); if(area==DockArea.TOP || area==DockArea.BOTTOM) edges=Math.max(edges,child.prefWidth(-1)); else middle+=child.prefWidth(-1); }
        return Math.max(middle,edges);
    }
    private double dockPreferredHeight(List<Node> children,double width) {
        double middle=0,edges=0;
        for(var child:children) { var area=dockItems.getOrDefault(child,DockArea.CENTER); if(area==DockArea.TOP || area==DockArea.BOTTOM) edges+=child.prefHeight(width); else middle=Math.max(middle,child.prefHeight(-1)); }
        return middle+edges;
    }
    private static double alignmentOffset(CrossAlignment alignment,double extra) { return switch(alignment) { case CENTER -> extra/2; case END -> extra; default -> 0; }; }
    private static void place(Node child,double x,double y,double width,double height,CrossAlignment horizontal,CrossAlignment vertical) {
        double wantedWidth=horizontal==CrossAlignment.STRETCH?width:Math.min(width,child.prefWidth(height));
        double actualWidth=Math.max(child.minWidth(height),Math.min(child.maxWidth(height),wantedWidth));
        double wantedHeight=vertical==CrossAlignment.STRETCH?height:Math.min(height,child.prefHeight(actualWidth));
        double actualHeight=Math.max(child.minHeight(actualWidth),Math.min(child.maxHeight(actualWidth),wantedHeight));
        child.resizeRelocate(x+alignmentOffset(horizontal,width-actualWidth),y+alignmentOffset(vertical,height-actualHeight),actualWidth,actualHeight);
    }
}
