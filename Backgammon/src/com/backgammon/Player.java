package com.backgammon;

public class Player {

    int out_piece=0;
    Piecetype piecetype=null;

    int score=0;
    boolean turn;


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

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }



}
