package com.backgammon;

import java.util.Optional;

import javafx.application.Application;
import javafx.beans.value.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.*;
import javafx.scene.text.Font;


public class Backgammon extends Application {

    public static int base = 30;//100 is base
    public static int size = 2 * base;//200 is base
    public static int board_width = 24 * base;//2400 is base
    public static int board_height = 18 * base;//1800 is base
    boolean incorrect_place_flag=true;
    public  int turn_flag=1;
    int first_dice;
    int second_dice;
    boolean first_dice_flag=true;
    boolean second_dice_flag=true;
    Player player1=new Player(0,Piecetype.RED,0);
    Player player2=new Player(0,Piecetype.GRAY,0);
    boolean runing_flag=false;
    Polygon triangle;

    Pane stackPane;


    //public Dice dice1=new Dice();
    //public Dice dice2=new Dice();
    public  Dice dice1;
    public  Dice dice2;
    public  Dice dice3;
    public  Dice dice4;


    public Colmn board[][] = new Colmn[12][2];
    public Group piecegroup = new Group();
    public Group highlight_group=new Group();

    //String playerOneName;
    //String playerTwoName;

    private Label playerOneLabel;
    private Label playerTwoLabel;
    private Label playersTurnIndicator;

    private String playerOneName = "Player 1";
    private String playerTwoName = "Player 2";

    private String availableColors[] = {"chocolate","maroon","gray","blue violate","silver","olive","pink","snow"};
    private Color availableColorCode [] = {Color.CHOCOLATE, Color.MAROON, Color.GRAY, Color.BLUEVIOLET,
            Color.SILVER, Color.OLIVE, Color.PINK, Color.SNOW};

    static Color playerOneColor;
    static Color playerTwoColor;

    private Colmn targetColmn;

    private void startWindow(){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Settings");
        window.setMinWidth(15 * base);
        window.setMinHeight(8 * base);

        TextField playerOneDefault = new TextField(playerOneName);
        playerOneDefault.setPromptText("Enter Player 1 Name");
        playerOneDefault.setMaxWidth(6 * base);
        playerOneDefault.setAlignment(Pos.CENTER);
        //playerOneDefault.

        TextField playerTwoDefault = new TextField(playerTwoName);
        playerTwoDefault.setMaxWidth(6 * base);
        playerTwoDefault.setPromptText("Enter Player 2 Name");
        playerTwoDefault.setAlignment(Pos.CENTER);

        TextField inputBase = new TextField(String.valueOf(base));
        inputBase.setMaxWidth(6 * base);
        inputBase.setPromptText("Enter Base size up to 100");
        inputBase.setAlignment(Pos.CENTER);

        // force the field to be numeric only
        inputBase.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    inputBase.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        // create a choiceBox
        ChoiceBox colorChoiceBoxOne = new ChoiceBox(FXCollections.observableArrayList(availableColors));
        colorChoiceBoxOne.getSelectionModel().select(0);

        ChoiceBox colorChoiceBoxTwo = new ChoiceBox(FXCollections.observableArrayList(availableColors));
        colorChoiceBoxTwo.getSelectionModel().select(1);



        Button startBtn = new Button("Start");

        startBtn.setOnAction(event -> {
            playerOneName = playerOneDefault.getText();
            playerTwoName = playerTwoDefault.getText();
            base = Integer.valueOf(inputBase.getText());
            size = 2 * base;
            board_width = 24 * base;
            board_height = 18 * base;

            playerOneColor = availableColorCode[colorChoiceBoxOne.getSelectionModel().getSelectedIndex()];
            playerTwoColor = availableColorCode[colorChoiceBoxTwo.getSelectionModel().getSelectedIndex()];

            window.close();
        });




        GridPane layout = new GridPane();
        //layout.getChildren().addAll(playerOneDefault,playerTwoDefault,inputBase,startBtn);
        layout.add(new Label("Player 1 name: "),0,0,1,1);
        layout.add(new Label("Player 2 name: "),0,1,1,1);
        layout.add(new Label("Base size: "),0,2,1,1);


        layout.add(playerOneDefault,1,0,1,1);
        layout.add(playerTwoDefault,1,1,1,1);
        layout.add(inputBase,1,2,1,1);
        layout.add(startBtn,2,4,1,1);

        layout.add(colorChoiceBoxOne,2,0,1,1);
        layout.add(colorChoiceBoxTwo,2,1,1,1);

        layout.setAlignment(Pos.CENTER);

        layout.setHgap(base/2);
        layout.setVgap(base/2);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();
    }


    public Parent /*top*/bottem(){

        AnchorPane root=new AnchorPane();

        Button playerOne = new Button("Change Player 1 Name");
        playerOne.setFont(new Font(base/3));

        playerOne.setTranslateX(board_width/8);
        playerOne.setTranslateY(base);
        playerOne.setAlignment(Pos.BOTTOM_LEFT);

        Button playerTwo = new Button("Change Player 2 Name");
        playerTwo.setFont(new Font(base/3));


        playerTwo.setTranslateX(6*board_width/8);
        playerTwo.setTranslateY(base);
        playerTwo.setAlignment(Pos.BOTTOM_RIGHT);


        this.playerOneLabel = new Label(playerOneName);
        playerOneLabel.setFont(new Font(2*base/3));
        playerOneLabel.setTextFill(playerOneColor);
        playerOneLabel.setMinHeight(base*1.5);
        playerOneLabel.setAlignment(Pos.TOP_LEFT);


        this.playerTwoLabel = new Label(playerTwoName);
        playerTwoLabel.setFont(new Font(2*base/3));
        playerTwoLabel.setTextFill(playerTwoColor);
        playerTwoLabel.setMinHeight(base*1.5);
        playerTwoLabel.setAlignment(Pos.TOP_RIGHT);

        this.playersTurnIndicator = new Label();
        playersTurnIndicator.setFont(new Font(base));



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
               if(dice1.dice_number == dice2.dice_number){
                   dice3.valueProperty.set(dice1.dice_number);
                   dice4.valueProperty.set(dice1.dice_number);
                   stackPane.getChildren().removeAll(dice3,dice4);
                   stackPane.getChildren().addAll(dice3,dice4);

               }
               else{
                   stackPane.getChildren().removeAll(dice3,dice4);
               }
               seting_dice_number();
               piece_highlight();

           }
       });

       dice2.setOnMouseClicked(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {


               dice1.roll();
               dice2.roll();
               if(dice1.dice_number == dice2.dice_number){
                   dice3.valueProperty.set(dice1.dice_number);
                   dice4.valueProperty.set(dice1.dice_number);
                   stackPane.getChildren().removeAll(dice3,dice4);
                   stackPane.getChildren().addAll(dice3,dice4);

               }
               else{
                   stackPane.getChildren().removeAll(dice3,dice4);
               }
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


    public void highlight_detect2(int i,int j,int temp,boolean colmn_or_piece){
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

        if(!colmn_or_piece) {
            if (runing_flag) {
                if ((board[x][y].piece_counter() == 0) || (board[x][y].piecelist.getLast().getType() == player2.piecetype)) {

                    board[i][j].piecelist.getLast().sethighlight();
                }
            }
            runing_flag = false;
        }

        if(colmn_or_piece){

            if (runing_flag) {
                if ((board[x][y].piece_counter() == 0)||(board[x][y].piecelist.getLast().getType() == player2.piecetype)) {

                    triangle_maker(x,y);
                }
            }

        }
    }


   public void highlight_detect1(int i,int j,int temp,boolean colmn_or_piece){
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

       if(!colmn_or_piece){
               if (runing_flag) {
                   if ((board[x][y].piece_counter() == 0)||(board[x][y].piecelist.getLast().getType() == player1.piecetype)) {

                       board[i][j].piecelist.getLast().sethighlight();
                   }
               }
               runing_flag=false;
       }

       if(colmn_or_piece){

           if (runing_flag) {
               if ((board[x][y].piece_counter() == 0)||(board[x][y].piecelist.getLast().getType() == player1.piecetype)) {

                   triangle_maker(x,y);
               }
           }



       }

   }

   public void piece_highlight(){

       int temp=0;

       for(int j=0;j<2;j++){

           for(int i=0;i<12;i++){

               if(board[i][j].piece_counter()>0) {

                   if (board[i][j].piecelist.getLast().getType() == player1.piecetype && (player1.piecetype.turn == turn_flag)) {

                       if (first_dice_flag) {
                           temp = first_dice;
                           highlight_detect1(i, j, temp,false);
                       }

                       if (second_dice_flag) {
                           temp = second_dice;
                           highlight_detect1(i, j, temp,false);

                       }

                         /*  if(first_dice_flag&&second_dice_flag){

                               temp=first_dice+second_dice;
                               highlight_detect1(i,j,temp,false);


                           }*/

                   }


                   if (board[i][j].piecelist.getLast().getType() == player2.piecetype && (player2.piecetype.turn == turn_flag)) {


                       if (first_dice_flag) {

                           temp = first_dice;
                           highlight_detect2(i, j, temp,false);

                       }

                       if (second_dice_flag) {

                           temp = second_dice;
                           highlight_detect2(i, j, temp,false);

                       }

                         /*  if(first_dice_flag&&second_dice_flag){

                               first_dice+second_dice;
                               highlight_detect2(i,j,temp,false);



                           }*/

                   }
               }
           }
       }
   }

   public void piece_highlight_remover(){


       for(int j=0;j<2;j++) {

           for (int i = 0; i < 12; i++) {

               if (board[i][j].piece_counter() > 0) {

                   board[i][j].piecelist.getLast().removehighlight();


               }

           }
       }

   }


   public void colmn_highlight(double mouse_x,double mouse_y){


       if (colmn_finder(mouse_x,mouse_y).piecelist.getLast().getType() == player1.piecetype && (player1.piecetype.turn == turn_flag)) {

           if (first_dice_flag) {

               highlight_detect1(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(), first_dice,true);

           }

           if (second_dice_flag) {

               highlight_detect1(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(), second_dice,true);

           }

        /*if(first_dice_flag&&second_dice_flag){

           highlight_detect1(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(), first_dice+second_dice,true);


          }*/


       }


       if (colmn_finder(mouse_x,mouse_y).piecelist.getLast().getType() == player2.piecetype && (player2.piecetype.turn == turn_flag)) {


           if (first_dice_flag) {

               highlight_detect2(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(), first_dice,true);

           }

           if (second_dice_flag) {

               highlight_detect2(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(), second_dice,true);

           }

         /*if(first_dice_flag&&second_dice_flag){

            highlight_detect2(colmn_finder(mouse_x,mouse_y).getx(), colmn_finder(mouse_x,mouse_y).gety(),first_dice+second_dice,true);

         }*/

       }

   }

   public void colmn_highlight_remove(){

        int temp=highlight_group.getChildren().size();

        for(int i=0;i<temp;i++ ){

           highlight_group.getChildren().remove(highlight_group.getChildren().size()-1);

       }
   }


   public void triangle_maker(int x,int y){


       triangle = new Polygon();



       if((x<6)&&(y==0)){

           triangle.getPoints().addAll(new Double[]{
                   .1359*(Backgammon.board_width/2)+.1239*x*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2),
                   .1359*(Backgammon.board_width/2)+.1239*(x+1)*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2),
                   .1359*(Backgammon.board_width/2)+.1239*(x+.5)*(Backgammon.board_width/2), .1349*(Backgammon.board_height/2)+(.7936*(Backgammon.board_height/2))/2});

       }

       if((x>=6)&&(y==0)){

           triangle.getPoints().addAll(new Double[]{
                   .5612*(Backgammon.board_width)+.1239*(x-6)*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2),
                   .5612*(Backgammon.board_width)+.1239*(x-5)*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2),
                   .5612*(Backgammon.board_width)+.1239*(x-5.5)*(Backgammon.board_width/2),.1349*(Backgammon.board_height/2)+(.7936*(Backgammon.board_height/2))/2});

       }

       if((x<6)&&(y==1)){

           triangle.getPoints().addAll(new Double[]{
                   .1359*(Backgammon.board_width/2)+.1239*x*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2)),
                   .1359*(Backgammon.board_width/2)+.1239*(x+1)*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2)),
                   .1359*(Backgammon.board_width/2)+.1239*(x+.5)*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2))-(.7936*(Backgammon.board_height/2))/2});

       }

       if((x>=6)&&(y==1)){

           triangle.getPoints().addAll(new Double[]{
                   .5612*(Backgammon.board_width)+.1239*(x-6)*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2)),
                   .5612*(Backgammon.board_width)+.1239*(x-5)*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2)),
                   .5612*(Backgammon.board_width)+.1239*(x-5.5)*(Backgammon.board_width/2),.5379*Backgammon.board_height+(.7936*(Backgammon.board_height/2))-(.7936*(Backgammon.board_height/2))/2});

       }

       triangle.setFill(Color.YELLOW);
       triangle.setOpacity(.5);

       highlight_group.getChildren().add(triangle);

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
           piece.setOldy(.03*(Backgammon.board_height/2)+(colmn.piece_counter()+1)*Backgammon.size/3);

       }

       if((colmn.x>=6)&&(colmn.y==0)){

           piece.setOldx(.5612*(Backgammon.board_width)+.1239*(colmn.x-6)*(Backgammon.board_width/2));
           piece.setOldy(.03*(Backgammon.board_height/2)+(colmn.piece_counter()+1)*Backgammon.size/3);

       }

       if((colmn.x<6)&&(colmn.y==1)){

           piece.setOldx(.1359*(Backgammon.board_width/2)+.1239*colmn.x*(Backgammon.board_width/2)+.021*(Backgammon.board_width/4));
           piece.setOldy(.5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)-(colmn.piece_counter()+1)*Backgammon.size/3);

       }

       if((colmn.x>=6)&&(colmn.y==1)){

           piece.setOldx(.5612*(Backgammon.board_width)+.1239*(colmn.x-6)*(Backgammon.board_width/2));
           piece.setOldy( .5379*Backgammon.board_height+.7736*(Backgammon.board_height/2)-(colmn.piece_counter()+1)*Backgammon.size/3);

       }


       piece.move();
       colmn.piece_adder(piece);
//       if(first_dice_flag || second_dice_flag){
//
//       }else{
//           turn_changer();
//           first_dice_flag = true;
//           second_dice_flag = true;
//       }


       piece.setOnMousePressed(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent mouseEvent) {

               if(piece.isMovable()&&(turn_flag==piecetype.turn)) {
                   piece.mousex = mouseEvent.getSceneX();
                   piece.mousey = mouseEvent.getSceneY();
                   piece_highlight_remover();
                   colmn_highlight(mouseEvent.getSceneX(),mouseEvent.getSceneY());


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

                                   targetColmn = colmn_finder(mouseEvent.getSceneX(), mouseEvent.getSceneY());

                                   if(piece.isMovable()&&(turn_flag==piecetype.turn)) {
                                       colmn.remove_piece();

                                       piece_adder(targetColmn, piecetype);
                                       colmn_highlight_remove();

                                       if (incorrect_place_flag) {

                                           targetColmn.remove_piece();
                                           piece_adder(colmn, piecetype);


                                       }else if(targetColmn.y == colmn.y){
                                            if (first_dice_flag && (first_dice == (targetColmn.x - colmn.x)|| first_dice == (colmn.x - targetColmn.x)))
                                                first_dice_flag = false;
                                            else if (second_dice_flag && (second_dice == (targetColmn.x - colmn.x)|| second_dice == (colmn.x - targetColmn.x)))
                                                second_dice_flag = false;
                                       }else {
                                           if (first_dice_flag && first_dice == (targetColmn.x + colmn.x + 1))
                                               first_dice_flag = false;
                                           else if (second_dice_flag && second_dice == (targetColmn.x + colmn.x + 1))
                                               second_dice_flag = false;
                                       }

                                       if(first_dice_flag || second_dice_flag){
                                           piece_highlight();
                                       }
                                       else{
                                           turn_changer();
                                           first_dice_flag = true;
                                           second_dice_flag = true;
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
        stackPane=new Pane();
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


        dice1 = new Dice();
        dice2 = new Dice();
        dice3 = new Dice();
        dice4 = new Dice();

        dice1.setTranslateX(board_width/8);
        dice1.setTranslateY(board_height/2);

        dice2.setTranslateX(3*board_width/8 - base);
        dice2.setTranslateY(board_height/2);

        dice3.setTranslateX(7*board_width/8 - base);
        dice3.setTranslateY(board_height/2);

        dice4.setTranslateX(5*board_width/8);
        dice4.setTranslateY(board_height/2);

        dicing();

        stackPane.getChildren().addAll(iv,piecegroup,dice1,dice2,highlight_group);
        //stackPane.setMinSize(1000,450);


        return stackPane;
    }
    @Override
    public void init() throws Exception {
        super.init();
    }



    @Override
    public void start(Stage stage) throws Exception {

        startWindow();

        BorderPane borderPane=new BorderPane();
        //borderPane.setTop(top());
        borderPane.setBottom((bottem()));

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
