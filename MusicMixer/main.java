import edu.princeton.cs.algs4.StdAudio;

public class main {
    public static void main(String[] args){
       /* Classic guitar has 6 strings and 19 frets. */
       int i = 0;
       double frequency = 440 * Math.pow(2,(i-24.0)/12.0);
       GuitarString[] notes = new GuitarString[37];
       for(i=0;i<37;i++){
          notes[i] = new GuitarString(frequency);
       }
       while(true){
           notes[1].pluck();
           double sample =0.0;
           for(int j =0;j<37;j++){
               sample += notes[j].sample();
           }
           StdAudio.play(sample);
           for(int j=0;j<37;j++){
               notes[j].tic();
           }
       }
    }
}
