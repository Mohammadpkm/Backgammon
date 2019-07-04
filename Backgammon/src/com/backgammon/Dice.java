package com.backgammon;

import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Dice extends StackPane {

    public static final int max=6;
    public static final int min=1;
    public final SimpleIntegerProperty valueProperty=new SimpleIntegerProperty();

   public Dice(){

       Rectangle rectangle=new Rectangle(Backgammon.size/2,Backgammon.size/2);
       rectangle.setFill(Color.YELLOW);
       rectangle.setStroke(Color.ORANGE);
       rectangle.setStrokeWidth(5);
       Text text=new Text();
       text.setFill(Color.BLACK);
       text.textProperty().bind(valueProperty.asString());
       text.setFont(new Font("Chiller",50));
       this.setAlignment(Pos.CENTER);
       getChildren().addAll(rectangle,text);

//       this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//           @Override
//           public void handle(MouseEvent mouseEvent) {
//               roll();
//
//           }
//       });

   }


   public void roll(){


       RotateTransition rotat=new RotateTransition(Duration.seconds(2),this);
       rotat.setFromAngle(0);
       rotat.setToAngle(360);

       rotat.setOnFinished(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {


               valueProperty.set((int)(Math.random()*(max-min+1))+min);

           }
       });

       rotat.play();

   }




}
