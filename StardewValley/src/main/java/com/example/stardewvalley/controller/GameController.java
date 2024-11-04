package com.example.stardewvalley.controller;

import com.example.stardewvalley.model.Cow;
import com.example.stardewvalley.screens.ScreenA;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Random;

public class GameController implements Initializable {
    @FXML
    private Canvas canvas;

    private GraphicsContext graphicsContext;
    private ScreenA screenA;

    private List<Cow> cows;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        canvas.setHeight(600);
        canvas.setWidth(1000);
        this.graphicsContext = canvas.getGraphicsContext2D();
        this.screenA= new ScreenA(this.canvas);

        cows = new ArrayList<>();
        int numberOfCows = 5;

        this.canvas.setOnKeyPressed(event -> {
           screenA.onKeyPressed(event);
        });

        this.canvas.setOnKeyReleased(event -> {
            screenA.onKeyReleased(event);
        });

        Timeline cowSpawner = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            Platform.runLater(() -> {
                Cow newCow = new Cow(canvas, 60, 60); // Crea una nueva vaca
                cows.add(newCow); // Añade la vaca a la lista
            });
        }));
        cowSpawner.setCycleCount(Timeline.INDEFINITE); // Repite indefinidamente
        cowSpawner.play();

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