package com.backgammon;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.TriangleMesh;

import java.util.LinkedList;

public class Colmn extends Rectangle {


    public Group piecegroup = new Group();

    public LinkedList<Piece> piecelist=new LinkedList<Piece>();

    public LinkedList<Piece> getPiecelist() {
        return piecelist;
    }

    public Group getPiecegroup() {
        return piecegroup;
    }


    int x=0;
    int y=0;
    boolean permission_flag=false;

    public boolean isPermission_flag() {
        return permission_flag;
    }

    public void setPermission_flag(boolean permition_flag) {
        this.permission_flag = permition_flag;
    }


    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public int piece_counter() {


        return piecelist.size();

    }

    public void piece_adder(Piece piece){

        if(piece_counter()>0) {
            piecelist.get(piece_counter() - 1).setMovable(false);
        }

        piecelist.add(piece);
        piecegroup.getChildren().add(piece);

        piecelist.get(piece_counter()-1).setMovable(true);


    }

    public void remove_piece() {

        piecegroup.getChildren().remove(piece_counter() - 1);
        piecelist.removeLast();

        if(piece_counter()>0) {
            piecelist.get(piece_counter() - 1).setMovable(true);
        }


    }




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

