package com.backgammon;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Backgammon extends Application {

    Dice dice1=new Dice();
    Dice dice2=new Dice();

    public Parent top(){

        Pane root=new Pane();
        dice1.setTranslateX(400);
        dice1.setTranslateY(400);

        dice2.setTranslateX(400);
        dice2.setTranslateY(600);

        root.getChildren().addAll(dice1,dice2);

        return root;



    }


    public Parent center(){

        StackPane stackPane=new StackPane();
        Image background=new Image("file:backgammon.jpg");
        ImageView iv=new ImageView();
        iv.setImage(background);
        iv.setFitHeight(1500);
        iv.setFitWidth(2200);
        stackPane.getChildren().add(iv);
        stackPane.setMinSize(2000,1500);
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
        borderPane.setTop(top());


        Scene scene=new Scene(borderPane,4000,2200);


        stage.setTitle("Backgammon");
        stage.setScene(scene);
        stage.show();














    }



    public static void main(String ...args){


        launch();

    }

}
