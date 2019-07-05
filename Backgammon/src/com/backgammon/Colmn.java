package com.backgammon;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

import java.util.LinkedList;

public class Colmn extends Rectangle {

    public Group piecegroup = new Group();

    public LinkedList<Piece> piecelist=new LinkedList<Piece>();

    public void piece_adder(Piece piece){

        piecelist.add(piece);
        piecegroup.getChildren().add(piece);


    }


    public LinkedList<Piece> getPiecelist() {
        return piecelist;
    }

    public Group getPiecegroup() {
        return piecegroup;
    }


    int x=0;
    int y=0;
    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }


    public int piece_counter() {


        return piecelist.size();

    }

    public void remove_piece() {

        piecegroup.getChildren().remove(piece_counter() - 1);
        piecelist.removeLast();

    }

   /* public void piece_adder(Piecetype piecetype) {






    }*/

    public Colmn(int x_i,int y_i){

        this.x=x_i;
        this.y=y_i;

        setHeight(.7936*(Backgammon.board_height/2));
        setWidth(.1237*(Backgammon.board_width)/2);

        if((x<6)&&(y==0)){

            relocate(.1359*(Backgammon.board_width/2)+.1239*x*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2));

        }

        if((x>=6)&&(y==0)){

            relocate(.5612*(Backgammon.board_width)+.1239*(x-6)*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2));

        }

        if((x<6)&&(y==1)){


            relocate(.1359*(Backgammon.board_width/2)+.1239*x*(Backgammon.board_width/2),.5379*Backgammon.board_height);

        }

        if((x>=6)&&(y==1)){

            relocate(.5612*(Backgammon.board_width)+.1239*(x-6)*(Backgammon.board_width/2),.5379*Backgammon.board_height);

        }




    }


}

