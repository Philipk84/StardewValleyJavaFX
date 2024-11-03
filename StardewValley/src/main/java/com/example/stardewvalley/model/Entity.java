package com.example.stardewvalley.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public abstract class Entity {
    private Canvas canvas;
    private GraphicsContext graphicsContext;


    private Position position;
    private int frame;
    private int height;
    private int width;

    public Entity(Canvas canvas,int height,int width) {
        this.canvas = canvas;
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.frame = 0;
        this.height = height;
        this.width = width;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }


}
