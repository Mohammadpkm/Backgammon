package com.backgammon;

public class Player {

    int out_piece=0;

    String name=new String();

    Piecetype piecetype=null;


    int score=0;
    boolean final_flag=false;

    public boolean isFinal_flag() {
        return final_flag;
    }

    public void setFinal_flag(boolean final_flag) {
        this.final_flag = final_flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOut_piece() {
        return out_piece;
    }

    public void setOut_piece(int out_piece) {
        this.out_piece = out_piece;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Piecetype getPiecetype() {
        return piecetype;
    }

    public void setPiecetype(Piecetype piecetype) {
        this.piecetype = piecetype;
    }




    public Player(int out_piece, Piecetype piecetype, int score) {
        this.out_piece = out_piece;
        this.piecetype = piecetype;
        this.score = score;
    }

}
