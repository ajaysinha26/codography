package com.sinhaj.rectangle;

/**
 * Created by ajaysinha on 27/12/14.
 */
public class Rectangle {
    private int x;
    private int y;
    private int width;
    private int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean doesIntersect(Rectangle that) {
        return ((this.x <= (that.x + that.width)) &&
                ((this.x + this.width) >= that.x) &&
                (this.y <= (that.y + that.height)) &&
                ((this.y + this.height) >= that.y));
    }

    public Rectangle findIntersection(Rectangle that) {
        if(doesIntersect(that)) {
            return new Rectangle(Math.max(this.x, that.x), Math.max(this.y, that.y),
                    Math.min(this.x + this.width, that.x + that.width) - Math.max(this.x, that.x),
                    Math.min(this.y + this.height, that.y + that.height) - Math.max(this.y, that.y));
        }
        return null;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
