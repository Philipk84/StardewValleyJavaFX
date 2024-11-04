package com.example.stardewvalley.screens;

import javafx.scene.input.KeyEvent;

public abstract class BaseScreen {
    public abstract void paint();
    public abstract void onKeyPressed(KeyEvent e);
    public abstract void onKeyReleased(KeyEvent e);
}
