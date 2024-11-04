package com.example.stardewvalley.controller;

import com.example.stardewvalley.model.Cow;
import com.example.stardewvalley.screens.ScreenA;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Random;

public class GameController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    private ScreenA screenA;

    private ArrayList <Cow> cows;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas.setHeight(400);
        canvas.setWidth(600);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenA= new ScreenA(this.canvas);

        this.canvas.setOnKeyPressed(event -> {
           screenA.onKeyPressed(event);
        });

        this.canvas.setOnKeyReleased(event -> {
            screenA.onKeyReleased(event);
        });

       Thread gameThread = new Thread( ()->{
           while(true){
               Platform.runLater(()->{
                   screenA.paint();

               });
               try {
                   Thread.sleep(100);
               }catch (InterruptedException e){
                   e.printStackTrace();
               }
           }
       });

        gameThread.setDaemon(true);
        gameThread.start();

    }



}