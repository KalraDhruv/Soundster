import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }
    @Test
   public void addFirstTestSimple(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<8;i++){
           arrayList.addFirst(i*10);
       }
       assertThat(arrayList.toList()).containsExactly(70,60,50,40,30,20,10,0).inOrder();
   }
   @Test
    public void toListTestIncompleteList(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       List<Integer> sample = arrayList.toList();
       assertThat(sample).containsExactly(50,40,30,20,10,0).inOrder();
   }
   @Test
   public void toListTestAdvanced(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<7;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(100);
       List<Integer> sample = arrayList.toList();
       assertThat(sample).containsExactly(60,50,40,30,20,10,0,100).inOrder();
   }
   @Test
   public void toHellWithTestsInitializeFinalTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i=0;i<10;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.toList();
       assertThat(arrayList.toList()).containsExactly(90,80,70,60,50,40,30,20,10,0).inOrder();

   }
   @Test
   public void toListTestHalfFirstHalfLast(){
        ArrayDeque<Integer> arrayList = new ArrayDeque();
        for(int i=0;i<5;i++){
            arrayList.addFirst(i*10);
        }
        arrayList.addLast(50);
        arrayList.addLast(60);
        arrayList.addLast(70);
        assertThat(arrayList.toList()).containsExactly(40,30,20,10,0,50,60,70);
   }

   @Test
   public void isEmptyTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       assertThat(arrayList.isEmpty()).isEqualTo(true);
   }
   @Test
    public void sizeTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       assertThat(arrayList.size()).isEqualTo(6);
   }
   @Test
    public void removeFirstTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(100);
       List<Integer> sample = arrayList.toList();
       sample.removeFirst();
       assertThat(sample).containsExactly(40,30,20,10,0,100);
   }
   @Test
    public void removeLastTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(100);
       List<Integer> sample = arrayList.toList();
       sample.removeLast();
       assertThat(sample).containsExactly(50,40,30,20,10,0);
   }
   @Test
   public void getTestAdvanced(){
        ArrayDeque<Integer> arrayList = new ArrayDeque<>();
        for(int i=0;i<5;i++){
            arrayList.addFirst(i*10);
        }
        arrayList.addLast(50);
        arrayList.addLast(60);
        arrayList.addLast(70);
        assertThat(arrayList.get(5)).isEqualTo(50);
        assertThat(arrayList.get(4)).isEqualTo(0);
        assertThat(arrayList.get(7)).isEqualTo(70);
   }
   @Test
   public void getTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(100);
       Object number = arrayList.get(5);
       arrayList.get(5);
       assertThat(arrayList.get(5)).isEqualTo(0);
   }
   @Test
    public void getTestOutOfBounds(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<6;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(100);
       assertThat(arrayList.get(-100)).isEqualTo(null);
   }
   @Test
   public void addFirstLastTestAdvancedResize(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<10;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.addLast(1000);
       assertThat(arrayList.size()).isEqualTo(11);
       assertThat(arrayList.toList()).containsExactly(90,80,70,60,50,40,30,20,10,0,1000).inOrder();
   }
   @Test
   public void getTestLast(){
        ArrayDeque<Integer> arrayList = new ArrayDeque();
        for(int i=0;i<2;i++){
            arrayList.addFirst(i*10);
        }
        arrayList.addLast(20);
        arrayList.addLast(30);
        arrayList.addLast(40);
        assertThat(arrayList.get(2)).isEqualTo(20);
        assertThat(arrayList.get(1)).isEqualTo(0);
       assertThat(arrayList.get(0)).isEqualTo(10);
       assertThat(arrayList.get(4)).isEqualTo(40);
   }
   @Test
   public void getTestOnlyLast(){
       ArrayDeque<Integer> arrayList = new ArrayDeque<>();
       arrayList.addLast(0);
       arrayList.addLast(10);
       arrayList.addLast(20);
       arrayList.addLast(30);
       arrayList.addLast(40);
       assertThat(arrayList.get(4)).isEqualTo(40);
       assertThat(arrayList.get(0)).isEqualTo(0);
   }
   @Test
    public void intializeTestBasic(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<9;i++){
           arrayList.addFirst(i*10);
       }
       arrayList.toList();
       assertThat(arrayList.size()).isEqualTo(9);
       assertThat(arrayList.toList()).containsExactly(80,70,60,50,40,30,20,10,0).inOrder();
       assertThat(arrayList.get(0)).isEqualTo(80);
   }
   @Test
    public void withoutInitializeFullArrayTest(){
       ArrayDeque<Integer> arrayList = new ArrayDeque();
       for(int i =0;i<8;i++){
           arrayList.addFirst(i*10);
       }
       assertThat(arrayList.size()).isEqualTo(8);
       //assertThat(arrayList.toList()).containsExactly(70,60,50,40,30,20,10,0);
   }

}
