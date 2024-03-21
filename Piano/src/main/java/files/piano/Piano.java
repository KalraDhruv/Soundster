package files.piano;
import files.piano.PianoResources.GuitarString;
import files.piano.PianoResources.StdAudio;
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
import java.util.Date;

public class Piano extends Application {
    private GuitarString[] notes = new GuitarString[37];
    private final String inputs ="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private int countInput=0;

    public Piano(){
    }
    private void logic(){
        // Write code for all the prerequisites in order to make the sound work.
        for(int i=0;i<37;i++){
            double frequency =440* Math.pow(2,(i-24.0)/12.0);
            notes[i] = new GuitarString(frequency);
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        logic();


        Pane majorArcana = new Pane(pianoTiles());
        Text text = new Text(625,400,"I am a fool");
        text.setTextAlignment(TextAlignment.CENTER);
        majorArcana.getChildren().add(text);

        Scene scene = new Scene(majorArcana,1300,500);
        stage.setTitle("My love for piano is absolute");

        scene.setFill(Color.WHITE);
        scene.setOnKeyPressed(event->{
            char note = event.getText().charAt(0);
            String string = note+"";

            boolean condition = false;

            for (int i = 0; i < 37; i++) {
                if (note == (inputs.charAt(i))) {
                    notes[i].pluck();
                    condition = true;
                    break;
                }
            }

            if (condition) {
                text.setText(string);
                sound();
            } else {
                text.setText("Invalid-Input");
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    private void sound(){
        Date date = new Date();
        long timeInSeconds= date.getTime()/1000;
        Date timer = new Date();
        long timeKeeper = timer.getTime()/1000;
        while(timeKeeper - timeInSeconds < 2) {
            timer = new Date();
            timeKeeper = timer.getTime()/1000;
            System.out.println("TimeKeeper: "+ timeKeeper);
            System.out.println("TimeInSeconds: "+timeInSeconds);
            System.out.println(timeKeeper - timeInSeconds);
            double sample = 0;
            for (int j = 0; j < 37; j++) {
                sample += notes[j].sample();
            }
            StdAudio.play(sample);
            for (int j = 0; j < 37; j++) {
                notes[j].tic();
            }
            System.out.println("Is it running?");
        }
        System.out.println("Sound process finished here.");
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