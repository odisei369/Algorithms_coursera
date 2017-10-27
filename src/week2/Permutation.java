package week2;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {
    public static void main(String[] args){
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty())
        {
            String s = StdIn.readString();
            rq.enqueue(s);
        }
        for (int a = 0; a < k; a++){
            System.out.println(rq.dequeue());
        }
    }
}
