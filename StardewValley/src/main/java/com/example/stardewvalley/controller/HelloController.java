package com.example.stardewvalley.controller;

import com.example.stardewvalley.screens.ScreenA;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    private ScreenA screenA;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenA= new ScreenA(this.canvas);

        this.canvas.setOnKeyPressed(event -> {
           screenA.onKeyPressed(event);
        });

        this.canvas.setOnKeyReleased(event -> {
            screenA.onKeyReleased(event);
        });
        new Thread( ()->{
                    while(true){
                        Platform.runLater(()->{
                            screenA.paintPlayer();
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