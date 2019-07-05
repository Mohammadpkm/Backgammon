package com.backgammon;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.event.ActionEvent;

import javax.xml.soap.Text;

//import java.awt.*;

public class GetInput {

    static String playerName;

    public static String display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);


        Label label = new Label();
        label.setText(message);

        TextField inputName = new TextField();
        inputName.setPromptText("Name :");

        Button okay = new Button("OK");

        okay.setOnAction(event -> {
            playerName = inputName.getText();
            window.close();
        });




        VBox layout = new VBox();
        layout.getChildren().addAll(label,inputName,okay);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);

        window.showAndWait();

        return playerName;
    }

}
