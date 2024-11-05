package com.example.stardewvalley.screens;

import com.example.stardewvalley.model.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ScreenA extends BaseScreen {
    private GraphicsContext graphicsContext;

    private Player player;
    private List<Cow> cows;
    private List<Sheep> sheeps;
    private Duck duck;
    private Tree tree;
    private List<Pig> pigs;

    public ScreenA(Canvas canvas) {
        super(canvas);// Asegúrate de llamar al constructor de BaseScreen
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.player = new Player(canvas, 32, 32);

        // Inicialización de animales
        cows = new ArrayList<>();
        addCow();
        addCow();
        addCow();

        sheeps = new ArrayList<>();
        sheeps.add(new Sheep(canvas, 60, 60));
        sheeps.add(new Sheep(canvas, 60, 60));

        duck = new Duck(canvas, 20, 20);
        pigs = new ArrayList<>();
        pigs.add(new Pig(canvas, 60, 60));
        pigs.add(new Pig(canvas, 60, 60));

        tree = new Tree(canvas, 32, 32);
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        tree.paint();
        paintPlayer();
        paintAnimals();
    }

    public void paintPlayer() {
        for (Cow cow : cows) {
            player.colisionCowVerify(cow);
            cow.verifyColision(player);
        }
        for (Pig pig : pigs) {
            player.colisionPigVerify(pig);
            pig.verifyColision(player);
        }
        player.colisionTreeVerify(tree);
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

    @Override
    public void onKeyPressed(KeyEvent e) {
        player.onKeyPressed(e);
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        player.onKeyReleased(e);
    }

    public void addCow() {
        double randomX = Math.random() * (canvas.getWidth() - 60);
        double randomY = Math.random() * (canvas.getHeight() - 60);

        Cow newCow = new Cow(canvas, 60, 60);
        newCow.setPosition(randomX, randomY);

        cows.add(newCow);
    }
}
