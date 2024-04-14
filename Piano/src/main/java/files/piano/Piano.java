package files.piano;
import files.piano.PianoResources.ArrayDeque;
import files.piano.PianoResources.GuitarString;
import files.piano.PianoResources.SoundGenerator;
import files.piano.PianoResources.StdAudio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.Date;

public class Piano extends Application {

    private GuitarString[] notes = new GuitarString[37];
    private final String inputs ="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private int countInput =0;
    public ArrayDeque<Character> charArray = new ArrayDeque<>();


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
        SoundGenerator sound = new SoundGenerator();

        // Generating a pane for piano.
        Pane majorArcana = new Pane(pianoTiles());

        // Adding the text for showing inputs.
        Text text = new Text(625,400,"I am a fool");
        majorArcana.getChildren().add(text);

        // Generating a scene
        Scene scene = new Scene(majorArcana,1300,500);
        stage.setTitle("My love for piano is absolute");

        // Abstraction is required for the below code.
        // Operations when a key is pressed from the keyboard.

        scene.setOnKeyPressed(event->{
            System.out.println("Key has been pressed.");
            String character = event.getText();
            System.out.println(character);
            charArray.addFirst(character.charAt(0));
            System.out.println(charArray);
        });

        sound.start();

        // Show the stage
        stage.setScene(scene);
        stage.show();
    }
    public class SoundGenerator extends Thread{
        public void run(){

            while (true) {
                if(!charArray.isEmpty()){
                    char input = charArray.removeFirst();
                    System.out.println(input);
                    for (int i = 0; i < 37; i++) {
                        if (input == inputs.charAt(i)) {
                            notes[i].pluck();
                            break;
                        }
                    }
                }

                double sample = 0;

                for (int j = 0; j < 37; j++) {
                    sample += notes[j].sample();
                }

                StdAudio.play(sample);

                for (int j = 0; j < 37; j++) {
                    notes[j].tic();
                }
            }
        }

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
        // Launch the application.
        launch();
    }
}