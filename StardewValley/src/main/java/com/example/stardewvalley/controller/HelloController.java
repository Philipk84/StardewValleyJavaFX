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
         Thread hola=new Thread( ()->{
                    while(isRunning) {
                        Platform.runLater(()->{
                            paint(SCREEN_ID);
                        });
                        try {
                            Thread.sleep(50);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
            }
        );
         hola.setDaemon(true);
         hola.start();



    }
    public void paint(int SCREEN_ID){
        setScreenId(SCREEN_ID);
        screens.get(SCREEN_ID).paint();
    }
    public void setScreenId(int SCREEN_ID){
        this.SCREEN_ID = SCREEN_ID;
    }

}