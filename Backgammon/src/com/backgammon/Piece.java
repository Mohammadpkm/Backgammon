package com.backgammon;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Piece extends StackPane {

    Ellipse ground = new Ellipse(Backgammon.size*.3125,Backgammon.size*.26);
    Ellipse piece = new Ellipse(Backgammon.size*.3125,Backgammon.size*.26);


    Piecetype type;
    public Piecetype getType(){

        return type;

    }




    boolean movable=false;

    public boolean isMovable() {
        return movable;
    }

    public void setMovable(boolean movable) {
        this.movable = movable;
    }




    double oldx,oldy;
    double mousex,mousey;


    public double getMousex() {
        return mousex;
    }

    public double getMousey() {
        return mousey;
    }

    public double getOldx(){return oldx;}

    public double getOldy(){return oldy;}

    public void setOldx(double oldx) {
        this.oldx = oldx;
    }

    public void setOldy(double oldy) {
        this.oldy = oldy;
    }


    public Piece(Piecetype type) {
        this.type = type;

        move();

        ground.setFill(Color.BLACK);

        ground.setStroke(Color.BLACK);
        ground.setStrokeWidth(Backgammon.size*.03);


        piece.setFill(type==Piecetype.RED?Color.MAROON:Color.CHOCOLATE);

        piece.setStroke(Color.BLACK);
        piece.setStrokeWidth(Backgammon.size*.03);


        getChildren().addAll(ground,piece);



        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(movable) {
                    mousex = mouseEvent.getSceneX();
                    mousey = mouseEvent.getSceneY();

                }
            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                if(movable) {

                    relocate(mouseEvent.getSceneX() - mousex + oldx, mouseEvent.getSceneY() - mousey + oldy);//+Backgammon.size);

                }

            }
        });

    }



    public void move(){

        relocate(oldx,oldy);


    }



}
