package com.example.stardewvalley.screens;

import com.example.stardewvalley.model.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class ScreenB extends BaseScreen{
    private Player player;
    private GraphicsContext graphicsContext;

    public ScreenB(Canvas canvas) {
        super(canvas);
        this.canvas = canvas;
        this.graphicsContext = this.canvas.getGraphicsContext2D();
        this.player=new Player(this.canvas, 32, 32);
    }

    @Override
    public void paint() {
        graphicsContext.setFill(Color.GREEN);
        graphicsContext.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        player.paint();
    }

    @Override
    public void onKeyPressed(KeyEvent e) {
        player.onKeyPressed(e);
    }

    @Override
    public void onKeyReleased(KeyEvent e) {
        player.onKeyReleased(e);
    }
}
