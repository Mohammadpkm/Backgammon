package com.backgammon;


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


    double oldx,oldy;
    double mousex,mousey;

    public double getOldx(){return oldx;}

    public double getOldy(){return oldy;}

    public Piece(Piecetype type,double x,double y) {
        this.type = type;
        move(x,y);

        ground.setFill(Color.BLACK);

        ground.setStroke(Color.BLACK);
        ground.setStrokeWidth(Backgammon.size*.03);


        piece.setFill(type==Piecetype.RED?Color.MAROON:Color.CHOCOLATE);

        piece.setStroke(Color.BLACK);
        piece.setStrokeWidth(Backgammon.size*.03);


        getChildren().addAll(ground,piece);














    }



    public void move(double x,double y){

        oldx=x;
        oldy=y;
        relocate(oldx,oldy);


    }



}
