package com.demo.opencvdemo;

public interface Shape {
    int area();
}

class Rectangle implements Shape {
    int x = 100;
    int y = 200;
    int width = 120;
    int height = 120;

    @Override
    public int area() {
        return width*height;
    }

    @Override
    public String toString() {
        return  "I am rect"+this.x+","+this.y;
    }
}

class Circle implements Shape {
    int x = 100;
    int y = 200;
    int radius = 120;

    @Override
    public int area() {
        return (int) (radius*radius* Math.PI);
    }

    @Override
    public String toString() {
        return "I am Circle"+radius;
    }
}