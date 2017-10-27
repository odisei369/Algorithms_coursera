package week1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] arrayOfOpened;
    private double mean = 0;
    private double stddev = 0;

    public PercolationStats(int n, int trials){
        arrayOfOpened = new double[trials];
        if (n <= 0 || trials <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        for(int a = 0; a< trials; a++){
            Percolation p = new Percolation(n);
            while(!p.percolates()){
                int col = StdRandom.uniform(1, n+1);
                int row = StdRandom.uniform(1, n+1);
                p.open(col, row);
            }
            //System.out.println(p.numberOfOpenSites() * 1.0/(n*n));
            arrayOfOpened[a] = p.numberOfOpenSites() * 1.0/(n*n);
        }
        mean = StdStats.mean(arrayOfOpened);
        stddev = StdStats.stddev(arrayOfOpened);

    }    // perform trials independent experiments on an n-by-n grid
    public double mean(){
        return mean;
    }      // sample mean of percolation threshold
    public double stddev(){
        return stddev;
    }                        // sample standard deviation of percolation threshold
    public double confidenceLo(){
        return mean - 1.96 * stddev / Math.sqrt(arrayOfOpened.length);
    }                  // low  endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean + 1.96 * stddev / Math.sqrt(arrayOfOpened.length);
    }                  // high endpoint of 95% confidence interval

    public static void main(String[] args){
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean:" + ps.mean());
        System.out.println("stddev:" + ps.stddev());
        System.out.println("min:" + ps.confidenceLo());
        System.out.println("max:" + ps.confidenceHi());
    }        // test client (described below)
}