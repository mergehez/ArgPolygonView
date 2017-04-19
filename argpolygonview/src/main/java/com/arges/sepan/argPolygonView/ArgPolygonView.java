package com.arges.sepan.argPolygonView;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;


 /*
    PolygonView is square.
    Children's width and height have to be defined in xml layout.
    Rotation is optional. If not defined, first vertex is placed top of PolygonView
    useCenter is optional. If defined as true, first child is placed in the center of PolygonView
 */
public class ArgPolygonView extends SquareViewGroup {
    private int childWidth = 0;
    private int childHeight = 0;
    private int width =0;
    private int height =0;
    private int rotate = 0;
    private boolean useCenter = false;
    public ArgPolygonView(Context context) {
        super(context);
        init(context,null);
    }

    public ArgPolygonView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ArgPolygonView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    public void setRotate(int rotate){
        this.rotate = rotate;
    }
    public void setUseCenter(boolean useCenter){
        this.useCenter = useCenter;
    }
    public void setChildWidth(int widthInPx){
        this.childWidth = widthInPx;
    }
    public void setChildHeight(int heightInPx){
        this.childHeight = heightInPx;
    }

    public int getRotate() {
        return rotate;
    }
    public boolean getUseCenter(){
        return useCenter;
    }
    public int getChildWidth(){
        return childWidth;
    }
    public int getChildHeight(){
        return childHeight;
    }
    /*
        Is called after setter methods to change layout
     */
    public void applyChanges(){
        requestLayout();
    }

    private void init(Context context, AttributeSet attrs){
        this.setWillNotDraw(false);
        if(attrs != null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ArgChild, 0, 0);
            try {
                childHeight = (int) ta.getDimension(R.styleable.ArgChild_childs_height, 0f);
                childWidth = (int) ta.getDimension(R.styleable.ArgChild_childs_width, 0f);
                useCenter = ta.getBoolean(R.styleable.ArgChild_use_center,false);
                rotate = ta.getInteger(R.styleable.ArgChild_rotate,0);
            } finally {
                ta.recycle();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int visiblecount = 0;
        for (int i = 0; i < count; i++) {
            if(getChildAt(i).getVisibility()!=GONE) visiblecount++;
        }
        Point[] ps = getCenterPoints(visiblecount, useCenter, rotate,
                width,height, childWidth, childHeight,
                getPaddingTop(), getPaddingBottom(), getPaddingLeft(), getPaddingRight());

        //Placing childs
        for (int i = 0; i < count; i++) {
            // We got center positions but to place we need top-left positons of children.
            // @param pc is the start position of the child
            Point pc = Point.shift(ps[i],0-childWidth/2, 0-childHeight/2);
            final View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                // Place the child.
                child.layout(
                        (int)pc.getX(),
                        (int)pc.getY(),
                        (int)pc.getX()+childWidth,
                        (int)pc.getY()+childHeight);
            }
        }

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    /**
     *
     * @param count         Child count
     * @param useMiddle     If true first child will be placed middle of PolygonView
     * @param rotation    Start angle that the first child will be placed
     * @param parentWidth   PolygonView's gross width
     * @param parentHeight  PolygonView's gross height
     * @param childWidth    Width attribute that defined in xml layout
     * @param childHeight   Height attribute that defined in xml layout
     * @param pdTop         Padding Top of PolygonView
     * @param pdBottom      Padding Bottom of PolygonView
     * @param pdLeft        Padding Left of PolygonView
     * @param pdRight       Padding Right of PolygonView
     * @return              Returns center positions of children
     */
    private static Point[] getCenterPoints(int count, boolean useMiddle, int rotation,
                                           int parentWidth, int parentHeight, int childWidth, int childHeight,
                                           float pdTop, float pdBottom, float pdLeft, float pdRight){
        Point[] ps = new Point[count];

        //X and Y positions of Centroid. G is centroid
        float gx = (parentWidth-pdRight)/2 + pdLeft/2;
        float gy = (parentHeight-pdBottom)/2 + pdTop/2;
        Point G = new Point(gx, gy);

        //Absolute paddings
        float verticalPadding = pdTop + pdBottom + childHeight;
        float horizantalPadding = pdLeft + pdRight + childWidth;

        // Radius of virtual circle. All children are placed r(distance) away from the centroid and on this absolute circle.
        float r = (parentHeight - (verticalPadding>horizantalPadding?verticalPadding:horizantalPadding))/2;// radius

        /*
         Angle is used for finding each vertex of the poligon.
         If we do not add 180, first vertex will be placed bottom.
         If we do not use rotation first vertex will be always placed top
          */
        float angle = (180+rotation)%360; //degree for finding each vertex of the polygon
        float angleChange = 360/count;
        int i =0;
        /*
         When useMiddle is true, first child is placed in center of PolygonView
         So, one edge is decreased. To solve this problem, we should change angleChange variable
         */
        if(useMiddle){
            ps[0] = G;
            i = 1;
            angleChange = 360/(count-1);
        }
        /*
            Defining all remaining center positions.
            To understand better, look at the shape in description. (usage of sin and cosin)
         */
        for(; i < count; i++, angle += angleChange){
            ps[i] = new Point(sin(angle)*r + G.getX(), cos(angle)*r + G.getY());
        }
        return ps;
    }
    static float sin(float degree){
        return (float)Math.sin(Math.toRadians(degree));
    }
    static float cos(float degree){
        return (float)Math.cos(Math.toRadians(degree));
    }
}
