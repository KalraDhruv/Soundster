package org.example.demo1;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIPiano extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Pane majorArcana = new Pane(arcanaTiles());

        Scene scene = new Scene(majorArcana,1000,500);
        scene.setFill(Color.WHITE);
        stage.setTitle("My love for piano is invincible");
        stage.setScene(scene);
        stage.show();
    }
    private Rectangle whiteTile(int number){
        Rectangle white = new Rectangle(number + 100,100, 50,200 );
        white.setFill(Color.WHITE);
        white.setHeight(200);
        white.setWidth(50);
        white.setStrokeWidth(2);
        white.setStroke(Color.GREY);
        return white;
    }
    private Rectangle blackTile(int number){
        Rectangle black = new Rectangle(number+130,100,36,135);
        black.setFill(Color.BLACK);
        black.setHeight(110);
        black.setWidth(36);
        return black;
    }
    private Pane arcanaTiles(){
        Pane majorArcana = new Pane();
        majorArcana.getChildren().addAll(whiteTile(0),whiteTile(50));
        majorArcana.getChildren().add(blackTile(0));
        for(int i=0;i<5;i++){
            if(i % 2==0){
                majorArcana.getChildren().add(numberTiles(100+i*100,3));
            }else{
                majorArcana.getChildren().add(numberTiles(100+i*100,4));
            }
        }
        return majorArcana;
    }

    private Pane numberTiles(int xValue, int whiteTiles){
        Pane minorArcana = new Pane();
        for(int i=0;i<=whiteTiles;i++){
            minorArcana.getChildren().add(whiteTile(xValue+i*50));
        }
        for(int i=0;i<=whiteTiles-1;i++){
            minorArcana.getChildren().add(blackTile(xValue+i*50));
        }
       return minorArcana;
    }

    public static void main(String[] args) {
        launch();
    }

}