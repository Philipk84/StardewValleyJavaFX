package com.example.stardewvalley.screens;

import com.example.stardewvalley.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ScreenA {

    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private Player player;
    private Cow cow;
    private Sheep sheep;
    private Duck duck;
    private Tree tree;
    private Pig pig;

    public ScreenA(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.player=new Player(this.canvas, 32, 32);
        this.cow=new Cow(this.canvas, 32,32);
        this.sheep=new Sheep(this.canvas, 32, 32);
        this.duck=new Duck(this.canvas, 20, 20);
        this.pig=new Pig(this.canvas, 32, 32);
        this.tree=new Tree(this.canvas,32 ,32);
    }

    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        player.paint();
        cow.paint();
        sheep.paint();
        duck.paint();
        pig.paint();
        tree.paint();

    }

    public void onKeyPressed(KeyEvent e) {
        this.player.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e) {
        this.player.onKeyReleased(e);
    }


}
