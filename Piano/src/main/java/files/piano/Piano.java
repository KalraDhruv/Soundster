package files.piano;
import files.piano.PianoResources.ArrayDeque;
import files.piano.PianoResources.GuitarString;
import files.piano.PianoResources.StdAudio;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Piano extends Application {

    private GuitarString[] notes = new GuitarString[37];
    private final String inputs ="q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private final String whiteInputs="qwertyuiop[zxcvbnm,./ ";
    private final String blackInputs="245789-=dfgjk;,";
    private ArrayDeque<Rectangle> whiteTiles;
    private ArrayDeque<Rectangle> blackTiles;
    private int countInput =0;
    public ArrayDeque<Character> charArray = new ArrayDeque<>();


    private void logic(){
        whiteTiles = new ArrayDeque<>();
        blackTiles = new ArrayDeque<>();
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
            char input = character.charAt(0);
            charArray.addFirst(input);
            if(characterChecker(input)){
                text.setText(input+"");
                TileHighlighter highlight = new TileHighlighter(input);
                highlight.start();
            }else{
                text.setText("Invalid-Input");
            }
        });

        sound.start();

        // Show the stage
        stage.setScene(scene);
        stage.show();
    }
    private boolean characterChecker(char c){
        for(int i=0;i<37;i++){
            if(c == inputs.charAt(i)){
                return true;
            }
        }
        return false;
    }
    public class TileHighlighter extends Thread{
        private char input;
        public TileHighlighter(Character input){
            this.input = input;
        }
        public void run(){
            for(int i=0;i<whiteInputs.length();i++){
                if(input == whiteInputs.charAt(i)){
                    Rectangle white = whiteTiles.get(i);
                    white.setFill(Color.BLANCHEDALMOND);
                    try {
                        Thread.sleep(250);
                    }catch(Exception e){

                    }
                    white.setFill(Color.WHITE);
                }
            }
            for(int i=0;i<blackInputs.length();i++){
                if(input==blackInputs.charAt(i)){
                    Rectangle black = blackTiles.get(i);
                    black.setFill(Color.GREY);
                    try {
                        Thread.sleep(250);
                    }catch(Exception e){

                    }
                    black.setFill(Color.BLACK);
                }
            }
        }
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
        whiteTiles.addLast(white);
        return white;
    }
    private Rectangle blackTile(int number){
        Rectangle black = new Rectangle(number+135,100,30,135);
        black.setFill(Color.BLACK);
        blackTiles.addLast(black);
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