package week2;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private class Node{
        public Node(Item i, Node n, Node p){
            this.i = i;
            this.next = n;
            this.previous = p;
        }
        Item i;
        Node next;
        Node previous;
    }
    private int number = 0;
    private Node first = null;
    private Node last = null;
    public Deque(){}                 // construct an empty deque
    public boolean isEmpty(){return first == null;}                 // is the deque empty?
    public int size(){
        return number;
    }                        // return the number of items on the deque
    public void addFirst(Item item){
        if(item == null){
            throw new java.lang.IllegalArgumentException();
        }
        if (first == null) {
            first = new Node(item, null, null);
            last = first;
        }else {
            Node oldFirst = first;
            first = new Node(item, oldFirst, null);
            oldFirst.previous = first;
        }
        number++;
    }          // add the item to the front
    public void addLast(Item item){
        if(item == null){
            throw new java.lang.IllegalArgumentException();
        }
        if(last == null){
            first = new Node(item, null, null);
            this.last = first;
        } else {
            Node oldLast = last;
            last = new Node(item, null, oldLast);
            oldLast.next = last;
        }
        number++;
    }           // add the item to the end
    public Item removeFirst(){
        if(first == null){
            throw new java.util.NoSuchElementException();
        }
        Item item = first.i;
        first = first.next;
        if (first != null)
            first.previous = null;
        else
            last = null;
        number--;
        return item;
    }                // remove and return the item from the front
    public Item removeLast(){
        if(first == null){
            throw new java.util.NoSuchElementException();
        }
        Item item = last.i;
        last = last.previous;
        if(last != null)
            last.next = null;
        else
            first = null;
        number--;
        return item;
    }                 // remove and return the item from the end
    public Iterator<Item> iterator(){return new ListIterator();}
    private class ListIterator implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext() { return current != null;}
        public void remove() {throw new java.lang.UnsupportedOperationException();}
        public Item next(){
            if(current == null)
                throw new java.util.NoSuchElementException();
            Item item = current.i;
            current = current.next;
            return  item;
        }
    }// return an iterator over items in order from front to end
    public static void main(String[] args){
        Deque<Integer> d = new Deque<Integer>();
        //d.addFirst(2);
        //d.removeFirst();
        //for (int a : d)
        //    System.out.println(a);
        d.addFirst(2);
        d.addLast(3);
        for (int a : d)
            System.out.println(a);
        d.removeLast();
        d.removeFirst();
        for (int a : d)
            System.out.println(a);
        System.out.println(d.isEmpty());
    }   // unit testing (optional)
}