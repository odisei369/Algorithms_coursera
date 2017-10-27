package week2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] array;
    private int counter = 0;
    public RandomizedQueue() {
        array = (Item[]) new Object[1];
    }                // construct an empty randomized queue
    public boolean isEmpty() {
        return counter == 0;
    }               // is the randomized queue empty?
    public int size(){
        return counter;
    }                   // return the number of items on the randomized queue

    private void resizePlus(){
        Item[] newArray = (Item[])new Object[array.length * 2];
        for (int a = 0; a < array.length; a++){
            newArray[a] = array[a];
        }
        array = newArray;
    }
    private void resizeMinus(){
        Item[] newArray = (Item[]) new Object[array.length / 2];
        for (int a = 0; a < counter; a++){
            newArray[a] = array[a];
        }
        array = newArray;
    }

    public void enqueue(Item item){
        if (item == null)
            throw new java.lang.IllegalArgumentException();
        counter++;
        if(counter > array.length){
            resizePlus();
        }
        array[counter - 1] = item;
    }           // add the item
    public Item dequeue(){
        if(counter == 0){throw new java.util.NoSuchElementException();}
        int index = StdRandom.uniform(counter);
        Item toRet = array[index];
        array[index] = array[--counter];
        if(counter == 0){array[index] = null;};
        if(counter < array.length / 4){resizeMinus();}
        return  toRet;
    }                    // remove and return a random item
    public Item sample(){
        if(counter == 0){throw new java.util.NoSuchElementException();}
        return array[StdRandom.uniform(counter)];
    }
    private class RandomIterator implements Iterator<Item>{
        private int[] shuffledArray;
        private int Itcounter = 0;

        public void remove(){
            throw new java.lang.UnsupportedOperationException();
        }
        @Override
        public boolean hasNext() {
            return Itcounter < shuffledArray.length;
        }

        public Item next(){
            if(!hasNext()){
                throw new java.util.NoSuchElementException();
            }
            return array[shuffledArray[Itcounter++]];
        }

        public RandomIterator() {
           shuffledArray = new int[counter];
           for(int a = 0; a < counter; a++){
               shuffledArray[a] = a;
           }
           StdRandom.shuffle(shuffledArray);
        }
    }// return a random item (but do not remove it)
    public Iterator<Item> iterator(){return new RandomIterator();}       // return an independent iterator over items in random order
    public static void main(String[] args){
        RandomizedQueue rq = new RandomizedQueue();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);
        rq.enqueue(7);
        rq.enqueue(8);
        rq.enqueue(9);
        rq.enqueue(0);
        rq.dequeue();
        rq.dequeue();
        for (Object item : rq) {
            System.out.println(item);
        }
    }   // unit testing (optional)
}