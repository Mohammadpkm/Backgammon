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


    public static final int size=200;
    public static final int board_width=2400;
    public static final int board_height=1800;



    public Dice dice1=new Dice();
    public Dice dice2=new Dice();
    public Colmn board[][]=new Colmn[12][2];


    public Group piecegroup = new Group();


    public Parent top(){

        Pane root=new Pane();
        dice1.setTranslateX(100);
        dice1.setTranslateY(100);

        dice2.setTranslateX(100);
        dice2.setTranslateY(200);

        root.getChildren().addAll(dice1,dice2);
        //root.setMinSize(1000,450);


        return root;



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

            }


        }
        for(int count=0;count<5;count++){

            board[0][0].piece_adder(Piecetype.RED);
            board[0][1].piece_adder(Piecetype.GRAY);
            board[6][1].piece_adder(Piecetype.RED);
            board[6][0].piece_adder(Piecetype.GRAY);
            if(count<3){

                board[4][1].piece_adder(Piecetype.RED);
                board[4][0].piece_adder(Piecetype.GRAY);

                if(count<2){

                    board[11][0].piece_adder(Piecetype.RED);
                    board[11][1].piece_adder(Piecetype.GRAY);

                }


            }

        }
        piecegroup.getChildren().addAll(board[0][0].getPiecegroup(),board[0][1].getPiecegroup()
        ,board[6][1].getPiecegroup(),board[6][0].getPiecegroup(),
         board[4][1].getPiecegroup(),board[4][0].getPiecegroup(),
         board[11][0].getPiecegroup(),board[11][1].getPiecegroup()
        );

        dice1.setTranslateX(board_width/5);
        dice1.setTranslateY(board_height/2);

        dice2.setTranslateX(board_width/5+size);
        dice2.setTranslateY(board_height/2);




        stackPane.getChildren().addAll(iv,piecegroup,dice1,dice2);
        stackPane.setMinSize(1000,450);


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
