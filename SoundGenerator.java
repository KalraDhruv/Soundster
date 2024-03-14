package gh2;
import java.lang.Math;
import deque.*;

public class GuitarString {

    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor
    private int capacity;
    /* Buffer for storing sound data. */
     private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque61B<Double>();
        capacity = (int)Math.round(SR/frequency);
        for(int i=0;i<capacity;i++){
            buffer.addFirst(0.0);
        }
    }
    //Primary operations for the working of Karplus-Strong algorithm is the ring buffer
    //and averaging operation

    // Sound is produced by vibrations of guitar's string. The length of the string
    // determines the fundamental frequency of vibration. We model a guitar string by choosing
    // a random double within -0.5 and 0.5 at N spaced repetitions.
    // We place the N displacements at random intervals.

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for(int i=0;i<capacity;i++){
            double randomNumber = Math.random() - 0.5;
            buffer.removeLast();
            buffer.addFirst(randomNumber);
        }
    }

    //The vibrations are also present. The Karplus-Strong algorithm simulates
    //this vibration by maintaining a ring buffer of the N samples:
    //It removes the first element averages the first and second element
    //Adds this new element at the end of array to make vibration effect.
    //The Higher frequencies are removed while lower ferquencies are passed.
    //This has the effect of gradually attenuating the higher harmonics while
    //keeping the lower ones, which corresponds closely with how a guitar string sounds.

    //Advance the simulation by one time step
    public void tic() {
        double firstRandom = buffer.removeFirst();
        double secondRandom= buffer.get(0);
        double newDouble = 0.996 * 0.5 *(firstRandom+secondRandom);
        buffer.addLast(newDouble);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}
