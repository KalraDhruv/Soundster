package files.piano.PianoResources;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class ArrayDeque<T> implements Deque<T>,Iterable<T> {

   private int nextFirst;
    private int nextLast;
    private int size;
    private T[] array;
    public ArrayDeque(){
        size=0;
        nextFirst=4;
        nextLast=5;
        array=(T[]) new Object[8];
    }
    @Override
    public void addFirst(Object x) {
        if(size == array.length){
            initializeArrayList();
        }
        array[nextFirst] = (T)x;
        --nextFirst;
        size++;
        if(nextFirst<0){
            nextFirst = array.length -1;
        }
    }
    @Override
    public void addLast(Object x){
        if(size==array.length){
            initializeArrayList();
        }
        array[nextLast] =(T) x;
        nextLast++;
        size++;
        if(nextLast==array.length){
            nextLast=0;
        }
    }
    private void initializeArrayList(){
        T[] newArray = (T[]) new Object[array.length*2];
        int addFirstPart = newArray.length/2;
        int limit =array.length;

        for(int i=0;i<limit;i++){
            newArray[addFirstPart] = get(limit-1-i);
            addFirstPart--;
        }
        nextFirst =0;
        nextLast=newArray.length/2+1;
        array=newArray;
    }

    @Override
    public List toList() {
        if(size==0){
            return null;
        }else{
            ArrayList<T> sampleArray = new ArrayList<>();
            int actualFirst;
            if(nextFirst==array.length-1){
                actualFirst=0;
            }else{
                actualFirst=nextFirst+1;
            }
            for (int i = 0; i < array.length; i++) {
                if (actualFirst == array.length) {
                    actualFirst = 0;
                }
                if (array[actualFirst] == null) {
                    actualFirst++;
                    continue;
                }
                sampleArray.add(array[actualFirst]);

                actualFirst++;
            }
            return sampleArray;
        }
    }

    @Override
    public boolean isEmpty() {
        if(size==0){
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if(size>0){
            int actualFirst;
            if(nextFirst==array.length-1){
                actualFirst=0;
            }else{
                actualFirst=nextFirst+1;
            }
            T object = array[actualFirst];
            array[actualFirst] =null;
            nextFirst=actualFirst;
            size--;
            return object;
        }
        return null;
    }

    @Override
    public T removeLast() {
        if(size>0){
            int actualLast;
            if(nextLast == 0){
                actualLast =array.length -1;
            }else{
                actualLast = nextLast-1;
            }
            T object = array[actualLast];
            array[actualLast] = null;
            nextLast = actualLast;
            size--;
            return object;
        }
        return null;
    }
    public T get(int index) {
        if(index<0 || index>size){
            return null;
        }
        if(size>0){
            int actualFirst;
            int actualIndex;
            if(nextFirst==array.length-1){
                actualFirst=0;
            }else{
                actualFirst=nextFirst+1;
            }

            actualIndex = Math.abs(-actualFirst-index);
            if(actualIndex<array.length){
                return array[actualIndex];
            }else{
                return array[Math.floorMod(actualIndex,array.length)];
            }
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
    private class ArrayDequeIterator<T> implements Iterator<T> {
        private int wizPos;
        private ArrayDequeIterator(){
            wizPos=0;
        }

        @Override
        public boolean hasNext() {
            return wizPos<size;
        }

        @Override
        public T next() {
            T object = (T)get(wizPos);
            wizPos++;
            return object;
        }
    }
    public boolean equals(Object obj){
        return (this == obj);
    }

    @Override
    public String toString(){

        StringBuilder x = new StringBuilder();
        x.append("--> ");

        for(T i: this){
            x.append(i.toString());
            x.append(" ");
        }

        x.append(" <--");
        return x.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }
}
