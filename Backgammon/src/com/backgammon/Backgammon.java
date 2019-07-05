package com.backgammon;

import java.util.Optional;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;


public class Backgammon extends Application {

    public static final int base = 30;//100 is base
    public static final int size = 2 * base;//200 is base
    public static final int board_width = 24 * base;//2400 is base
    public static final int board_height = 18 * base;//1800 is base
    boolean incorrect_place_flag=true;
    public  int turn_flag=1;
    int first_dice;
    int second_dice;
    boolean first_dice_flag=true;
    boolean second_dice_flag=true;
    Player player1=new Player(0,Piecetype.RED,0);
    Player player2=new Player(0,Piecetype.GRAY,0);
    boolean runing_flag=false;



    public Dice dice1=new Dice();
    public Dice dice2=new Dice();
    public Colmn board[][] = new Colmn[12][2];
    public Group piecegroup = new Group();

    //String playerOneName;
    //String playerTwoName;

    private Label playerOneLabel;
    private Label playerTwoLabel;
    private Label playersTurnIndicator;


    public Parent top(){

        AnchorPane root=new AnchorPane();

        Button playerOne = new Button("Change Player 1 Name");
        playerOne.setFont(new Font("Chiller",base/3));

        playerOne.setTranslateX(board_width/8);
        playerOne.setTranslateY(base);
        playerOne.setAlignment(Pos.BOTTOM_LEFT);

        Button playerTwo = new Button("Change Player 2 Name");
        playerTwo.setFont(new Font("Chiller",base/3));


        playerTwo.setTranslateX(6*board_width/8);
        playerTwo.setTranslateY(base);
        playerTwo.setAlignment(Pos.BOTTOM_RIGHT);


        this.playerOneLabel = new Label("Player 1");
        playerOneLabel.setFont(new Font("Chiller",base));
        playerOneLabel.setTextFill(Color.MAROON);
        playerOneLabel.setMinHeight(base*1.5);
        playerOneLabel.setAlignment(Pos.TOP_LEFT);


        this.playerTwoLabel = new Label("Player 2");
        playerTwoLabel.setFont(new Font("Chiller",base));
        playerTwoLabel.setTextFill(Color.CHOCOLATE);
        playerTwoLabel.setMinHeight(base*1.5);
        playerTwoLabel.setAlignment(Pos.TOP_RIGHT);

        this.playersTurnIndicator = new Label();
        playersTurnIndicator.setFont(new Font("Chiller",base));



        playerOneLabel.setTranslateX(board_width/8);
        //      playerTwo.setTranslateY(base);

        playerTwoLabel.setTranslateX(6*board_width/8);
//        playerTwo.setTranslateY(base);

        playersTurnIndicator.setMinWidth(board_width);
        playersTurnIndicator.setAlignment(Pos.CENTER);

//        playerOne.setOnAction(event -> {
//            playerOneName = GetInput.display("Player 1", "Please Enter Your Name And Press OK.");
//            playerOneLabel.setText(playerOneName);
//        });
//
//        playerTwo.setOnAction(event -> {
//            playerTwoName = GetInput.display("Player 2", "Please Enter Your Name And Press OK.");
//            playerTwoLabel.setText(playerTwoName);
//        });
        playerOne.setOnAction(event -> {
            InputTextDialog(playerOneLabel.getText(), "Please Enter Your Name And Press OK.", playerOneLabel);
            //playerOneLabel.setText(playerOneName);
        });

        playerTwo.setOnAction(event -> {
            InputTextDialog(playerTwoLabel.getText(), "Please Enter Your Name And Press OK.", playerTwoLabel);
            //playerTwoLabel.setText(playerTwoName);
        });

        root.getChildren().addAll(playerOne,playerTwo,playerOneLabel,playerTwoLabel,playersTurnIndicator);
        root.setMinHeight(2 * base);
        return root;

    }

    private void InputTextDialog(String title, String message, Label label){
        TextInputDialog dialog = new TextInputDialog(title);
        dialog.setTitle(title);
        dialog.setHeaderText(message);
        dialog.setContentText("Name: ");

        Optional<String> result = dialog.showAndWait();

        result.ifPresent(name -> {
            label.setText(name);
        });

    }

   public void seting_dice_number(){

       first_dice=dice1.getDice_number();
       second_dice=dice2.getDice_number();

   }

   public void dicing(){


       dice1.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {


               dice1.roll();
               dice2.roll();
               seting_dice_number();
               piece_highlight();

           }
       });

       dice2.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {


               dice1.roll();
               dice2.roll();
               seting_dice_number();
               piece_highlight();

           }
       });


   }

   public void turn_changer(){

       turn_flag=turn_flag*(-1);
       if (turn_flag == 1)
           playersTurnIndicator.setText(playerOneLabel.getText());
       else
           playersTurnIndicator.setText(playerTwoLabel.getText());



   }
    public void highlight_detect2(int i,int j,int temp){
        int x=0;
        int y=0;
        runing_flag=false;

        if (j == 1 && (i - temp >= 0)) {
            x = i - temp;
            y = j;
            runing_flag = true;
        }

        if (j == 1 && (i - temp < 0)) {
            x= Math.abs(i - temp) - 1;
            y= 0;
            runing_flag = true;
        }

        if (j == 0 && (i + temp <= 11)) {
            x= i + temp;
            y = 0;
            runing_flag = true;
        }

        if (runing_flag) {
            if ((board[x][y].piece_counter() == 0)||(board[x][y].piecelist.getLast().getType() == player2.piecetype)) {

                board[i][j].piecelist.getLast().sethighlight();
            }
        }
        runing_flag=false;
    }


   public void highlight_detect1(int i,int j,int temp){
        int x=0;
        int y=0;
        runing_flag=false;

       if (j == 0 && (i - temp >= 0)) {
           x = i - temp;
           y = j;
           runing_flag = true;
       }

       if (j == 0 && (i - temp < 0)) {
           x= Math.abs(i - temp) - 1;
           y= 1;
           runing_flag = true;
       }

       if (j == 1 && (i + temp <= 11)) {
           x= i + temp;
           y = 1;
           runing_flag = true;
       }

       if (runing_flag) {
           if ((board[x][y].piece_counter() == 0)||(board[x][y].piecelist.getLast().getType() == player1.piecetype)) {

               board[i][j].piecelist.getLast().sethighlight();
           }
       }
       runing_flag=false;
   }

   public void piece_highlight(){

       int temp=0;

       for(int j=0;j<2;j++){

           for(int i=0;i<12;i++){

               if(board[i][j].piece_counter()>0) {

                   if (board[i][j].piecelist.getLast().getType() == player1.piecetype && (player1.piecetype.turn == turn_flag)) {

                       if (first_dice_flag) {
                           temp = first_dice;
                           highlight_detect1(i, j, temp);
                           highlight_detect1(i, j, temp);
                       }

                       if (second_dice_flag) {
                           temp = second_dice;
                           highlight_detect1(i, j, temp);
                           highlight_detect1(i, j, temp);
                       }

                         /*  if(first_dice_flag&&second_dice_flag){

                               temp=first_dice+second_dice;
                               highlight_detect1(i,j,temp);
                               highlight_detect1(i,j,temp);


                           }*/

                   }


                   if (board[i][j].piecelist.getLast().getType() == player2.piecetype && (player2.piecetype.turn == turn_flag)) {


                       if (first_dice_flag) {
                           temp = first_dice;
                           highlight_detect2(i, j, temp);
                           highlight_detect2(i, j, temp);

                       }

                       if (second_dice_flag) {

                           temp = second_dice;
                           highlight_detect2(i, j, temp);
                           highlight_detect2(i, j, temp);


                       }

                         /*  if(first_dice_flag&&second_dice_flag){

                               temp=first_dice+second_dice;
                               highlight_detect1(i,j,temp);
                               highlight_detect1(i,j,temp);


                           }*/

                   }
               }
           }
       }
   }



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


       piece.move();
       colmn.piece_adder(piece);
       turn_changer();


       piece.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {

               if(piece.isMovable()&&(turn_flag==piecetype.turn)) {
                   piece.mousex = mouseEvent.getSceneX();
                   piece.mousey = mouseEvent.getSceneY();
                   piece.removehighlight();

               }
           }
       });

       piece.setOnMouseDragged(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {

               if(piece.isMovable()&&(turn_flag==piecetype.turn)) {

                   piece.relocate(mouseEvent.getSceneX() - piece.mousex + piece.oldx, mouseEvent.getSceneY() - piece.mousey + piece.oldy);//+Backgammon.size);

               }

           }
       });

       piece.setOnMouseReleased(new EventHandler<MouseEvent>() {
                               @Override
                              public void handle(MouseEvent mouseEvent) {

                                   if(piece.isMovable()&&(turn_flag==piecetype.turn)) {
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



        dicing();

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
        borderPane.setTop(top());
        borderPane.setCenter(center());


        Scene scene=new Scene(borderPane,board_width,board_height+size);


        stage.setTitle("Backgammon");
        stage.setScene(scene);
        stage.show();



    }



    public static void main(String ...args){


        launch();

    }

}
