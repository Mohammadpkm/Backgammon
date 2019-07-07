package com.backgammon;

import javafx.animation.RotateTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Dice extends StackPane {

    public static final int max=6;
    public static final int min=1;

    int dice_number;

    Rectangle dice;
    Text text;

    public int getDice_number() {

        return dice_number;

    }
    public void setDice_number(int number) {

        dice_number = number;

    }

    public final SimpleIntegerProperty valueProperty=new SimpleIntegerProperty();

   public Dice(){

       dice = new Rectangle(Backgammon.size/2,Backgammon.size/2);
       dice.setFill(Color.YELLOW);
       dice.setStroke(Color.ORANGE);
       dice.setStrokeWidth(5);
       text=new Text();
       text.setFill(Color.BLACK);
       text.textProperty().bind(valueProperty.asString());
       text.setFont(new Font(Backgammon.base));
       this.setAlignment(Pos.CENTER);
       getChildren().addAll(dice,text);

//       this.setOnMouseClicked(new EventHandler<MouseEvent>() {
//           @Override
//           public void handle(MouseEvent mouseEvent) {
//               roll();
//
//           }
//       });

   }


   public void roll(){


       this.fadeIn();


       RotateTransition rotat=new RotateTransition(Duration.seconds(2),this);
       rotat.setFromAngle(0);
       rotat.setToAngle(360);

       rotat.setOnFinished(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {



               valueProperty.set(dice_number);
              // System.out.println(getDice_number());

           }
       });

       
       dice_number=(int)(Math.random()*(max-min+1))+min;
       rotat.play();

   }

   public void fadeOut(){
       dice.setOpacity(0.6);
       text.setOpacity(0.6);
   }


    public void fadeIn(){
        dice.setOpacity(1);
        text.setOpacity(1);

    }

}
