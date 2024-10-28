package com.example.stardewvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Player {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList <Image> idles;
    private ArrayList <Image> runs;
    private int frame;

    private boolean rightPressed, leftPressed, upPressed, downPressed;

    private Position position;
    private int state;

    public Player (Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.state=0;
        this.frame=0;

        this.idles = new ArrayList<>();
        this.runs = new ArrayList<>();

        this.position = new Position(100,100);

        for (int i = 0;i<=3;i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.player/idle/idle_" +i+".png"), 100,100,false,false);
            this.idles.add(image);
        }

        for (int i = 0;i<=2;i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.player/run/run_0" +i+".png"), 100,100,false,false);
            this.runs.add(image);
        }

    }

    public void paint(){
        onMove();
        if (state==0) {
            graphicsContext.drawImage(idles.get(frame%3),position.getX(),position.getY());
            frame++;
        }else if (state==1) {
            graphicsContext.drawImage(runs.get(frame%2),position.getX(),position.getY());
            frame++;
        }
    }

    public void onKeyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                state=1;
                upPressed=true;
            }
            case DOWN -> {
                state=1;
                downPressed=true;
            }
            case LEFT -> {
                state=1;
                leftPressed=true;
            }
            case RIGHT -> {
                state=1;
                rightPressed=true;
            }
        }
    }

    public void onMove(){
        if (rightPressed) {
            position.setX(position.getX()+10);
        }else if (leftPressed) {
            position.setX(position.getX()-10);
        }else if (upPressed) {
            position.setY(position.getY()-10);
        }else if (downPressed) {
            position.setY(position.getY()+10);
        }
    }

    public void onKeyReleased(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP -> {
                state=0;
                upPressed=false;
            }
            case DOWN -> {
                state=0;
                downPressed=false;
            }
            case LEFT -> {
                state=0;
                leftPressed=false;
            }
            case RIGHT -> {
                state=0;
                rightPressed=false;
            }
        }
    }

}
