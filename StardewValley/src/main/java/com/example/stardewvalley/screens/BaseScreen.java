package com.example.stardewvalley.screens;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public abstract class BaseScreen {
    protected Canvas canvas;
    private boolean changeScreen;
    private int currentScreenIndex;

    public BaseScreen(Canvas canvas) {
        this.canvas = canvas;
    }
    public abstract void paint();
    public abstract void onKeyPressed(KeyEvent e);
    public abstract void onKeyReleased(KeyEvent e);
}
