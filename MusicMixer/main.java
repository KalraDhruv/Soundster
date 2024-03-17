import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.util.Scanner;

public class main {
    public static void main(String[] args){
       /* Classic guitar has 6 strings and 19 frets. */
        // Inputs for 37 frequencies.
        String inputKeys = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

       int i = 0;
       GuitarString[] notes = new GuitarString[37];
       for(i=0;i<37;i++){
           // Works fine
           // All good.
          double frequency = 440 * Math.pow(2,(i-24.0)/12.0);
          notes[i] = new GuitarString(frequency);
       }

       Scanner scanner = new Scanner(System.in);

       while(true){
//           char input =  scanner.next().charAt(0);
           if(StdDraw.hasNextKeyTyped()) {
               char input = StdDraw.nextKeyTyped();

               for (int j = 0; j < 37; j++) {
                   if (input == inputKeys.charAt(j)) {
                       notes[j].pluck();
                       break;
                   }
               }
           }
           double sample =0;
           for(int j=0;j<37;j++){
               sample+=notes[j].sample();
           }

           StdAudio.play(sample);

           for(int j=0;j<37;j++){
               notes[j].tic();
           }
       }

    }
}
