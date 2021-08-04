package com.interra.screentesting.model;

import java.nio.file.Path;

public class FingerPath {
    private int color;
    private int strokeWidth;
    private android.graphics.Path path;

    public FingerPath(int color, int strokeWidth, android.graphics.Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(int strokeWidth) {
        this.strokeWidth = strokeWidth;
    }

    public android.graphics.Path getPath() {
        return path;
    }

    public void setPath(android.graphics.Path path) {
        this.path = path;
    }
}
