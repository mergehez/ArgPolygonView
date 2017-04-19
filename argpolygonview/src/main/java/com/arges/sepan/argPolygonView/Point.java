package com.arges.sepan.argPolygonView;


class Point {
    private float x;
    private float y;
    Point(float x, float y){
        this.x = x;
        this.y = y;
    }

    float getX() {
        return x;
    }
    float getY() {
        return y;
    }
    static Point shift(Point p, float shiftX, float shiftY){
        return new Point(p.getX()+shiftX, p.getY()+shiftY);
    }
}
