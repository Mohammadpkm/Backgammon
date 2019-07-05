package com.backgammon;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Backgammon extends Application {

    public static final int base = 60;//100 is base
    public static final int size = 2 * base;//200 is base
    public static final int board_width = 24 * base;//2400 is base
    public static final int board_height = 18 * base;//1800 is base
    boolean incorrect_place_flag=true;



    public Dice dice1=new Dice();
    public Dice dice2=new Dice();
    public Colmn board[][] = new Colmn[12][2];
    public Group piecegroup = new Group();


   /* public Parent top(){

        Pane root=new Pane();
        dice1.setTranslateX(100);
        dice1.setTranslateY(100);

        dice2.setTranslateX(100);
        dice2.setTranslateY(200);

        root.getChildren().addAll(dice1,dice2);
        //root.setMinSize(1000,450);


        return root;



    }*/
   public Colmn colmn_finder(double x,double y){


       incorrect_place_flag=true;
       int x_location=0;
       int y_location=0;

       for(int j=0;j<2;j++){

           for(int i=0;i<12;i++){


               if((i<6)&&(j==0)){

                   if((x>=(.1359*(Backgammon.board_width/2)+.1239*i*(Backgammon.board_width/2)))&&(y>=(.1349*(Backgammon.board_height/2)))) {

                       if ((x <(.1359*(Backgammon.board_width/2)+.1239*(i+1)*(Backgammon.board_width/2))) && (y < (2 * Backgammon.board_height / 5))) {

                           incorrect_place_flag=false;
                           x_location = i;
                           y_location = j;

                       }
                   }
               }

               if((i<6)&&(j==1)){

                   if((x>=(.1359*(Backgammon.board_width/2)+.1239*i*(Backgammon.board_width/2)))&&(y>=(3 * Backgammon.board_height / 5))){

                       if((x <(.1359*(Backgammon.board_width/2)+.1239*(i+1)*(Backgammon.board_width/2))) &&( y<=(.5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)))){

                           incorrect_place_flag=false;
                           x_location = i;
                           y_location = j;

                       }

                   }

               }



               if((i>=6)&&(j==0)){

                   if((x>=(.5612*(Backgammon.board_width)+.1239*(i-6)*(Backgammon.board_width/2)))&&(y>=(.1349*(Backgammon.board_height/2)))) {

                       if ((x <(.5612*(Backgammon.board_width)+.1239*(i-5)*(Backgammon.board_width/2))) && (y < (2 * Backgammon.board_height / 5))) {

                           incorrect_place_flag=false;
                           x_location = i;
                           y_location = j;

                       }
                   }
               }


               if((i>=6)&&(j==1)){

                   if((x>=(.5612*(Backgammon.board_width)+.1239*(i-6)*(Backgammon.board_width/2)))&&(y>=(3 * Backgammon.board_height / 5))) {

                       if ((x <(.5612*(Backgammon.board_width)+.1239*(i-5)*(Backgammon.board_width/2))) && (y < (.5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)))) {

                           incorrect_place_flag=false;
                           x_location = i;
                           y_location = j;

                       }
                   }
               }


           }


       }

       return board[x_location][y_location];

   }







   public void piece_adder(final Colmn colmn, final Piecetype piecetype){

      final  Piece piece=new Piece(piecetype);


       if((colmn.x<6)&&(colmn.y==0)){

           piece.setOldx(.1359*(Backgammon.board_width/2)+.1239*colmn.x*(Backgammon.board_width/2)+.021*(Backgammon.board_width/4));
           piece.setOldy(.03*(Backgammon.board_height/2)+(colmn.piece_counter()+1)*Backgammon.size/2);

       }

       if((colmn.x>=6)&&(colmn.y==0)){

           piece.setOldx(.5612*(Backgammon.board_width)+.1239*(colmn.x-6)*(Backgammon.board_width/2));
           piece.setOldy(.03*(Backgammon.board_height/2)+(colmn.piece_counter()+1)*Backgammon.size/2);

       }

       if((colmn.x<6)&&(colmn.y==1)){

           piece.setOldx(.1359*(Backgammon.board_width/2)+.1239*colmn.x*(Backgammon.board_width/2)+.021*(Backgammon.board_width/4));
           piece.setOldy(.5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)-(colmn.piece_counter()+1)*Backgammon.size/2);

       }

       if((colmn.x>=6)&&(colmn.y==1)){

           piece.setOldx(.5612*(Backgammon.board_width)+.1239*(colmn.x-6)*(Backgammon.board_width/2));
           piece.setOldy( .5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)-(colmn.piece_counter()+1)*Backgammon.size/2);

       }

       if(colmn.piece_counter()>0) {
           colmn.getPiecelist().get(colmn.piece_counter() - 1).setMovable(false);
       }
       piece.move();
       colmn.piece_adder(piece);
       colmn.getPiecelist().get(colmn.piece_counter()-1).setMovable(true);



       piece.setOnMouseReleased(new EventHandler<MouseEvent>() {
                               @Override
                              public void handle(MouseEvent mouseEvent) {

                                   if(piece.isMovable()) {
                                       colmn.remove_piece();
                                       piece_adder(colmn_finder(mouseEvent.getSceneX(), mouseEvent.getSceneY()), piecetype);

                                       if (incorrect_place_flag) {

                                           colmn_finder(mouseEvent.getSceneX(), mouseEvent.getSceneY()).remove_piece();
                                           piece_adder(colmn, piecetype);


                                       }


                                       piece.setOldx(mouseEvent.getSceneX() - piece.getMousex() + piece.getOldx());
                                       piece.setOldy(mouseEvent.getSceneY() - piece.getMousey() + piece.getOldy());
                                       piece.move();

                                   }
                              }

                          }

       );



   }




    public Parent center(){

        //uploading image
        Pane stackPane=new Pane();
        Image background=new Image("file:d8bb7315f541050ceb54ac22f8f88231.jpg");
        ImageView iv=new ImageView();
        iv.setImage(background);
        iv.setFitHeight(board_height);
        iv.setFitWidth(board_width);


        //initialize board pieces
        for(int i=0;i<12;i++){


            for(int j=0;j<2;j++){

                Colmn colmn=new Colmn(i,j);
                board[i][j]=colmn;
                piecegroup.getChildren().add(board[i][j].getPiecegroup());


            }


        }
        for(int count=0;count<5;count++){


            piece_adder(board[0][0],Piecetype.RED);
            piece_adder(board[0][1],Piecetype.GRAY);


            piece_adder(board[6][1],Piecetype.RED);
            piece_adder(board[6][0],Piecetype.GRAY);

            if(count<3){

                piece_adder(board[4][1],Piecetype.RED);
                piece_adder(board[4][0],Piecetype.GRAY);

                if(count<2){

                    piece_adder(board[11][0],Piecetype.RED);
                    piece_adder(board[11][1],Piecetype.GRAY);

                }


            }

        }




        dice1.setTranslateX(board_width/8);
        dice1.setTranslateY(board_height/2);

        dice2.setTranslateX(3*board_width/8 - base);
        dice2.setTranslateY(board_height/2);




        stackPane.getChildren().addAll(iv,piecegroup,dice1,dice2);
        //stackPane.setMinSize(1000,450);


        return stackPane;
    }
    @Override
    public void init() throws Exception {
        super.init();
    }



    @Override
    public void start(Stage stage) throws Exception {

        BorderPane borderPane=new BorderPane();
        borderPane.setCenter(center());
        //borderPane.setTop(top());


        Scene scene=new Scene(borderPane,board_width,board_height);


        stage.setTitle("Backgammon");
        stage.setScene(scene);
        stage.show();

        dice1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dice1.roll();
                dice2.roll();
            }
        });

        dice2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                dice1.roll();
                dice2.roll();
            }
        });



    }



    public static void main(String ...args){


        launch();

    }

}
