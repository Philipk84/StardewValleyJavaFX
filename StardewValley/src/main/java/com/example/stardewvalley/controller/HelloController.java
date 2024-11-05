package com.example.stardewvalley.controller;

import com.example.stardewvalley.screens.BaseScreen;
import com.example.stardewvalley.screens.ScreenA;
import com.example.stardewvalley.screens.ScreenB;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    private ArrayList<BaseScreen> screens;
    public static int SCREEN_ID=0;
    private boolean isRunning;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.graphicsContext = canvas.getGraphicsContext2D();
        screens = new ArrayList<>(2);
        screens.add(new ScreenA(canvas));
        screens.add(new ScreenB(canvas));

        isRunning = true;

        this.canvas.setOnKeyPressed(event -> {
            screens.get(SCREEN_ID).onKeyPressed(event);
        });

        this.canvas.setOnKeyReleased(event -> {
            screens.get(SCREEN_ID).onKeyReleased(event);
        });
        new Thread( ()->{
                    while(isRunning) {
                        Platform.runLater(()->{
                            screens.get(SCREEN_ID).paint();
                        });
                        try {
                            Thread.sleep(50);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
            }
        ).start();
    }

}