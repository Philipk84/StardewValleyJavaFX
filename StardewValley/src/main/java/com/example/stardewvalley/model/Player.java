package com.example.stardewvalley.model;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Player extends Entity {

    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList <Image> idles;
    private ArrayList <Image> runs;
    private ArrayList <Image> attacks;
    private ArrayList <Image> swordAttack;
    private ArrayList <Image> runsIz;
    private ArrayList <Image> idlesHand;
    private ArrayList <Image> runsHand;
    private int frame;

    private boolean rightPressed, leftPressed, upPressed, downPressed, ePressed;

    private int width;
    private int height;

    private Position position;
    private int state;
    private boolean isFacingLeft;

    public Player (Canvas canvas, int height, int width ) {
        super(canvas, height, width);
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.state=0;
        this.frame=0;

        this.idles = new ArrayList<>();
        this.runs = new ArrayList<>();
        this.attacks = new ArrayList<>();
        this.swordAttack = new ArrayList<>();
        this.runsIz = new ArrayList<>();
        this.idlesHand = new ArrayList<>();
        this.runsHand = new ArrayList<>();

        this.position = new Position(100,100);

        for (int i = 0;i<=8;i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.player/idle/idle_" +i+".png"), 200,200,true,false);
            this.idles.add(image);
            Image image2 = new Image(getClass().getResourceAsStream("/animations.player/tools_idle/idle_" +i+".png"), 200,200,true,false);
            this.idlesHand.add(image2);

        }

        for (int i = 0;i<=5;i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.player/run/run_0" +i+".png"), 200,200,true,false);
            this.runs.add(image);
            Image image2 = new Image(getClass().getResourceAsStream("/animations.player/tools_run/run_" +i+".png"), 200,200,true,false);
            this.runsHand.add(image2);
        }

        for (int i = 0;i<=7;i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.player/attack/attack_0" +i+".png"),200,200,true,false);
            this.attacks.add(image);
            Image image2 = new Image(getClass().getResourceAsStream("/animations.player/sword_attack/sword_" +i+".png"),200,200,true,false);
            this.swordAttack.add(image2);
            Image image3 = new Image(getClass().getResourceAsStream("/animations.player/runIz/run_0" +i+".png"),200,200,true,false);
            this.runsIz.add(image3);
        }

    }

    public void paint(){
        onMove();
        if (isFacingLeft) {
            graphicsContext.save();
            graphicsContext.scale(-1, 1);

            if (state==0) {
                graphicsContext.drawImage(idles.get(frame%8),-position.getX()-idles.get(frame%8).getWidth(),position.getY());
                graphicsContext.drawImage(idlesHand.get(frame%8),-position.getX()-idlesHand.get(frame%8).getWidth(),position.getY());
                frame++;
            }else if (state==1) {
                graphicsContext.drawImage(runs.get(frame % 5), -position.getX()-runs.get(frame%5).getWidth(), position.getY());
                graphicsContext.drawImage(runsHand.get(frame % 5), -position.getX()-runsHand.get(frame%5).getWidth(), position.getY());
                frame++;
            }else if (state==3){
                graphicsContext.drawImage(runs.get(frame % 5), -position.getX()-runs.get(frame%5).getWidth(), position.getY());
                graphicsContext.drawImage(runsHand.get(frame % 5), -position.getX()-runsHand.get(frame%5).getWidth(), position.getY());
                frame++;
            }else if (state==2) {
                graphicsContext.drawImage(attacks.get(frame%7),-position.getX()-attacks.get(frame%7).getWidth(),position.getY());
                graphicsContext.drawImage(swordAttack.get(frame%7),-position.getX()-swordAttack.get(frame%7).getWidth(),position.getY());
                frame++;
            }

            graphicsContext.restore();
        }
        else {
            if (state==0) {
                graphicsContext.drawImage(idles.get(frame%8),position.getX(),position.getY());
                graphicsContext.drawImage(idlesHand.get(frame%8),position.getX(),position.getY());
                frame++;
            }else if (state==1) {
                graphicsContext.drawImage(runs.get(frame % 5), position.getX(), position.getY());
                graphicsContext.drawImage(runsHand.get(frame % 5), position.getX(), position.getY());
                frame++;
            }else if (state==3){
                graphicsContext.save();
                graphicsContext.scale(-1,1);
                graphicsContext.drawImage(runs.get(frame % 5), -position.getX()-runs.get(frame%5).getWidth(), position.getY());
                graphicsContext.drawImage(runsHand.get(frame % 5), -position.getX()-runsHand.get(frame%5).getWidth(), position.getY());

                frame++;
                graphicsContext.restore();
            }else if (state==2) {
                graphicsContext.drawImage(attacks.get(frame%7),position.getX(),position.getY());
                graphicsContext.drawImage(swordAttack.get(frame%7),position.getX(),position.getY());
                frame++;
            }
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
                state=3;
                leftPressed=true;
                isFacingLeft=true;
            }
            case RIGHT -> {
                state=1;
                rightPressed=true;
                isFacingLeft=false;
            }
            case E -> {
                state=2;
                ePressed=true;
            }
        }
    }

    public void onMove(){
        Position nextPosition = new Position(position.getX(), position.getY());

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
            case E -> {
                state=0;
                ePressed=false;

            }
        }
    }


}
