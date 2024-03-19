package files.piano.PianoResources;

import java.lang.Math;

public class GuitarString {

   //The Karplus-Strong algorithm simulates
   //the vibration by maintaining a ring buffer of the N samples:
   //It removes the first element averages the first and second element
   //Adds this new element at the end of array to make vibration effect.
   //The Higher frequencies are removed while lower ferquencies are passed.
   //This has the effect of gradually attenuating the higher harmonics while
   //keeping the lower ones, which corresponds closely with how a guitar string sounds.

   //Primary operations for the working of Karplus-Strong algorithm is the ring buffer
   //and averaging operation

   // Each String has a single ring buffer.
   //Ring Buffer is updated 44100 times per second.
   private static final int SR = 44100;      // Sampling Rate
   private static final double DECAY = .996; // energy decay factor
   private int capacity;

   /* Buffer for storing sound data. */
   private Deque<Double> buffer;

   /* Create a guitar string of the given frequency.  */
   public GuitarString(double frequency) {
      buffer = new ArrayDeque<Double>();
      capacity = (int)Math.round(SR/frequency);

      /* Guitar string at rest No Excitation.*/
      for(int i=0;i<capacity;i++){
         buffer.addFirst(0.0);
      }
   }

   /* Plucking the string. The string can contain energy at any frequency (excitation).
   *  This excitation is simulated by white noise: setting the displacements to a random
   *  number between -1/2 and +1/2.*/
   /* Taking random numbers and making music */

   public void pluck() {
      System.out.println("The string has been plucked.");
      for(int i=0;i<capacity;i++){
         double randomNumber = Math.random() - 0.5;
         buffer.removeLast();
         buffer.addFirst(randomNumber);
      }
   }

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