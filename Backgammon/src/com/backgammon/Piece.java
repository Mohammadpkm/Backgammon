package com.backgammon;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class Piece extends StackPane {

    Ellipse ground = new Ellipse(Backgammon.size*.3125,Backgammon.size*.26);
    Ellipse piece = new Ellipse(Backgammon.size*.3125,Backgammon.size*.26);

    int colmn_x=0;
    int colmn_y=0;

    Piecetype type;


    public int getColmn_x() {
        return colmn_x;
    }


    public int getColmn_y() {
        return colmn_y;
    }


    public void setColmn_x(int colmn_x) {
        this.colmn_x = colmn_x;
    }


    public void setColmn_y(int colmn_y) {
        this.colmn_y = colmn_y;
    }


    public Piecetype getType(){

        return type;

    }


    double oldx,oldy;
    double mousex,mousey;

    public double getOldx(){return oldx;}

    public double getOldy(){return oldy;}

    public Piece(Piecetype type,double x,double y,int colmn_x,int colmn_y) {
        this.type = type;
        move(x,y);

        this.colmn_x=colmn_x;
        this.colmn_y=colmn_y;


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

                mousex = mouseEvent.getSceneX();
                mousey = mouseEvent.getSceneY();

            }
        });

        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {

                relocate(mouseEvent.getSceneX()-mousex+oldx,mouseEvent.getSceneY()-mousey+oldy);//+Backgammon.size);

            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {

               oldx = mouseEvent.getSceneX() - mousex + oldx;
               oldy = mouseEvent.getSceneY() - mousey + oldy;//+Backgammon.size/2;


               for(int i=0;i<12;i++){

                   for(int j=0;j<2;j++){


                       if((i<6)&&(j==0)){


                           if((oldx>=.1359*(Backgammon.board_width/2)+.1239*i*(Backgammon.board_width/2)+.021*(Backgammon.board_width/4))&&(oldx<=.1359*(Backgammon.board_width/2)+.1239*(i+1)*(Backgammon.board_width/2)+.021*(Backgammon.board_width/4))){

                               if((oldy>=.03*(Backgammon.board_height/2))&&(oldy<=2*Backgammon.board_height/5)){




                               }

                           }



                       }




















                   }


               }














           }

        }

        );


    }



    public void move(double x,double y){

        oldx=x;
        oldy=y;
        relocate(oldx,oldy);


    }



}
