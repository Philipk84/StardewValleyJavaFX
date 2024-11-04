package com.example.stardewvalley.screens;

import com.example.stardewvalley.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ScreenA extends BaseScreen{

    private Canvas canvas;

    private GraphicsContext graphicsContext;



    private Player player;
    private List<Cow> cows;
    private List<Sheep> sheeps;
    private Duck duck;
    private Tree tree;
    private List<Pig> pigs;

    public ScreenA(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.player=new Player(this.canvas, 32, 32);

        this.cows=new ArrayList<>();
        addCow();
        addCow();
        addCow();

        this.sheeps= new ArrayList<>();
        this.sheeps.add(new Sheep(this.canvas, 60, 60));
        this.sheeps.add(new Sheep(this.canvas, 60, 60));

        this.duck=new Duck(this.canvas, 20, 20);

        this.pigs = new ArrayList<>();
        this.pigs.add(new Pig(this.canvas, 60, 60));
        this.pigs.add(new Pig(this.canvas, 60, 60));

        this.tree=new Tree(this.canvas,32 ,32);
    }

    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tree.paint();
        paintPlayer();
        paintAnimals();
    }

    public void paintPlayer() {
        player.paint();
    }

    public void paintAnimals() {
        for (Cow cow : cows) {
            cow.paint();
        }
        for (Sheep sheep : sheeps) {
            sheep.paint();
        }
        for (Pig pig : pigs) {
            pig.paint();
        }
    }

    public void onKeyPressed(KeyEvent e) {
        this.player.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e) {
        this.player.onKeyReleased(e);
    }

    public void addCow() {

        double randomX = Math.random() * (canvas.getWidth() - 60); // Ajusta 32 por el ancho de la vaca
        double randomY = Math.random() * (canvas.getHeight() - 60); // Ajusta 32 por la altura de la vaca

        Cow newCow = new Cow(this.canvas, 60, 60);
        newCow.setPosition(randomX, randomY);

        cows.add(newCow);
    }


}
