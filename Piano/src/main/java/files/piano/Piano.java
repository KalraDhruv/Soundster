package files.piano;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class Piano extends Application {

    private final String inputs ="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private int countInput=0;
    @Override
    public void start(Stage stage) throws IOException {

        Pane majorArcana = new Pane(pianoTiles());
        Text text = new Text(625,400,"I am a fool");
        text.setTextAlignment(TextAlignment.CENTER);
        majorArcana.getChildren().add(text);

        Scene scene = new Scene(majorArcana,1300,500);
        scene.setFill(Color.WHITE);
        scene.setOnKeyPressed(event->{
            char note = event.getText().charAt(0);
            String string = note+"";
            boolean condition = false;
            for(int i=0;i<37;i++){
                if(note==(inputs.charAt(i))){
                    condition = true;
                }
            }
            if(condition) {
                text.setText(string);
            }else{
                text.setText("Invalid-Input");
            }
        });

        stage.setTitle("My love for piano is absolute");
        stage.setScene(scene);
        stage.show();
    }
    private Rectangle whiteTile(int number){
        Rectangle white = new Rectangle(number + 100,100, 50,200 );
        white.setFill(Color.WHITE);
        white.setStrokeWidth(2);
        white.setStroke(Color.GREY);
        countInput++;
        return white;
    }
    private Rectangle blackTile(int number){
        Rectangle black = new Rectangle(number+135,100,30,135);
        Label text = new Label("   "+inputs.charAt(countInput));
        black.setFill(Color.BLACK);
        return black;
    }
    private Pane pianoTiles(){
        Pane majorArcana = new Pane();
        majorArcana.getChildren().addAll(whiteTile(0),whiteTile(50));
        majorArcana.getChildren().add(blackTile(0));

        int startValue =100;
        for(int i=0;i<5;i++){
            if(i % 2==0){
                majorArcana.getChildren().add(numberTiles(startValue,3));
                startValue+=150;
            }else{
                majorArcana.getChildren().add(numberTiles(startValue,4));
                startValue+=200;
            }
        }
        majorArcana.getChildren().add(numberTiles(startValue,3));
        return majorArcana;
    }

    private Pane numberTiles(int xValue, int whiteTiles){
        Pane minorArcana = new Pane();
        for(int i=0;i<whiteTiles;i++){
            minorArcana.getChildren().add(whiteTile(xValue+i*50));
        }
        for(int i=0;i<whiteTiles-1;i++){
            minorArcana.getChildren().add(blackTile(xValue+i*50));
        }
        return minorArcana;
    }

    public static void main(String[] args) {
        launch();
    }

}