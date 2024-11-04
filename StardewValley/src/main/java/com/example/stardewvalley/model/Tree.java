package com.example.stardewvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Tree extends Entity {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idles;

    private Position position;
    private int frame;

    private int height, width;


    public Tree (Canvas canvas, int height, int width){
        super(canvas,height,width);
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.frame = 0;

        this.idles = new ArrayList<>();

        this.position = new Position(400, 200);

        for (int i = 0; i <= 3; i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.resources/tree/idle/idle_" + i + ".png"), 150, 150, true, false);
            this.idles.add(image);
        }
    }

    public void paint() {
        this.graphicsContext.drawImage(idles.get(frame%3), position.getX(), position.getY());
        frame++;
    }

}