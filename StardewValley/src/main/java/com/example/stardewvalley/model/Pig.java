package com.example.stardewvalley.model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

public class Pig extends Entity {
    private Canvas canvas;
    private GraphicsContext graphicsContext;
    private ArrayList<Image> idles;

    private int height, width;

    private Position position;
    private int frame;

    private Position targetPosition;
    private double speed;
    private Random rand;
    private boolean facingRight;

    private boolean waiting;
    private double waitStartTime;
    private final double waitDuration = 2000;


    public Pig(Canvas canvas, int height, int width) {
        super(canvas, height, width);
        this.canvas = canvas;
        this.frame = 0;
        this.graphicsContext = canvas.getGraphicsContext2D();

        this.idles = new ArrayList<>();
        this.position = new Position(150, 150);
        this.targetPosition = new Position(150, 150);
        this.speed = 3;
        this.rand = new Random();
        this.facingRight = false;
        this.waiting = false;

        this.height = height;
        this.width = width;

        for (int i = 0; i <= 3; i++) {
            Image image = new Image(getClass().getResourceAsStream("/animations.animals/pig/idle/pig-0" + i + ".png"), height, width, true, false);
            this.idles.add(image);
        }

        setNewTargetPosition();

    }

    public void paint() {
        moveTowardsTarget();
        if (facingRight) {
            graphicsContext.save(); // Guardar el estado actual del contexto
            graphicsContext.scale(-1, 1); // Reflejar horizontalmente
            graphicsContext.drawImage(idles.get(frame % 3), -position.getX() - width, position.getY()); // Ajustar la posición para reflejar
            graphicsContext.restore(); // Restaurar el estado original
        } else {
            graphicsContext.drawImage(idles.get(frame % 3), position.getX(), position.getY());
        }
        frame++;
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.strokeRect(position.getX()+10, position.getY()+10, 40, 40);
    }
    public Rectangle getRectangle() {
        return new Rectangle(position.getX()+10, position.getY()+10, 40, 40);
    }

    private void setNewTargetPosition() {
        double randomX = rand.nextDouble() * (canvas.getWidth() - width);
        double randomY = rand.nextDouble() * (canvas.getHeight() - height);
        targetPosition.setX(randomX);
        targetPosition.setY(randomY);

        // Determinar la dirección basada en la posición actual
        if (targetPosition.getX() < position.getX()) {
            facingRight = false; // Mira a la izquierda
        } else {
            facingRight = true; // Mira a la derecha
        }

        waiting = false;
    }

    public void moveTowardsTarget() {
        if (waiting) {
            // Verifica si ha pasado el tiempo de espera
            if (System.currentTimeMillis() - waitStartTime >= waitDuration) {
                waiting = false; // Termina la espera
                setNewTargetPosition(); // Establece una nueva posición objetivo
            }
            return; // Sale del método, no se mueve
        }

        double deltaX = targetPosition.getX() - position.getX();
        double deltaY = targetPosition.getY() - position.getY();
        double distance = Math.sqrt(deltaX * deltaX + deltaY * deltaY);

        // Si la distancia es pequeña, establecer una nueva posición objetivo
        if (distance < speed) {
            position.setX(targetPosition.getX());
            position.setY(targetPosition.getY());
            waiting = true; // Comienza a esperar
            waitStartTime = System.currentTimeMillis(); // Guarda el tiempo de inicio de la espera
        } else {
            // Normalizar la dirección y mover la vaca
            position.setX(position.getX() + (deltaX / distance) * speed);
            position.setY(position.getY() + (deltaY / distance) * speed);
        }
    }

}
