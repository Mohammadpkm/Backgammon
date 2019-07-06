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


    public void sethighlight(){

       this.piece.setStroke(Color.YELLOW);
       this.piece.setStrokeWidth(Backgammon.size*.07);

    }

    public void removehighlight(){

        this.piece.setStroke(Color.BLACK);
        this.piece.setStrokeWidth(Backgammon.size*.03);


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


        piece.setFill(type==Piecetype.RED?Backgammon.playerOneColor:Backgammon.playerTwoColor);
        piece.setStroke(Color.BLACK);
        piece.setStrokeWidth(Backgammon.size*.03);


        getChildren().addAll(ground,piece);





    }



    public void move(){

        relocate(oldx,oldy);


    }



}
