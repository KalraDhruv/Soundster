import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import java.util.sound.midi.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import edu.*;


public class MusicPlayer(){
    private Sequence sequence = null;

    public MusicPlayer(InputStream file){
        try{
            sequence = MidiSystem.getSequence(file);
        }catch(IOException | InvalidMidiDataException e){
            e.printStackTrace();
        }
    }
    public MusicPlayer(File file){
        try{
            sequence = MidiSystem.getSequence(file);
        }catch(IOException | InvalidMidiDataException e){
            e.printStackTrace();
        }
    }
    public void play(){
        // Default case.
        if(sequence == null){
            return;
        }


    }
    public void simple(){
        double hz = 440 * Math.pow(2,pitch / 12.0);
        int n = (int) (StdAudio.SAMPLE_RATE * duration);
        double[] a = new double[n+1];
        for(int i=0; i<=n; i++){
            a[i] = Math.sin(2 * Math.PI * i * hz / StdAudio.SAMPLE_RATE);
        }
       StdAudio.play(a);
    }
}