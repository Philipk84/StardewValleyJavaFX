package com.example.stardewvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Duck extends Entity{
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idles;

    private int height, width;

    private Position position;
    private int frame;


    public Duck (Canvas canvas, int height, int width) {
        super(canvas,height, width);
        this.canvas = canvas;
        this.frame = 0;
        this.graphicsContext=canvas.getGraphicsContext2D();

        this.idles = new ArrayList<>();


        this.position = new Position(250,250 );

        for (int i = 0; i <= 3; i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.animals/duck/idle/duck-0" + i + ".png"), 40, 40, true, false);
            this.idles.add(image);
        }
    }

    public void paint() {
        this.graphicsContext.drawImage(idles.get(frame%3), position.getX(), position.getY());
        frame++;
    }

    public Rectangle getBounds() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }
}
