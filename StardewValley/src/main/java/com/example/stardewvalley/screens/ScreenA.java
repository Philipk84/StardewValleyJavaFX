package com.example.stardewvalley.screens;

import com.example.stardewvalley.model.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class ScreenA {

    private Canvas canvas;

    private GraphicsContext graphicsContext;

    private Player player;

    public ScreenA(Canvas canvas) {
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.player=new Player(this.canvas);
    }

    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        player.paint();
    }

    public void onKeyPressed(KeyEvent e) {
        this.player.onKeyPressed(e);
    }

    public void onKeyReleased(KeyEvent e) {
        this.player.onKeyReleased(e);
    }

}
