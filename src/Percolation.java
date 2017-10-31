import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF w;
    private boolean[] array;
    private int n;
    private int open = 0;

    public Percolation(int n){
        if(n < 1){
            throw new java.lang.IllegalArgumentException();
        }
        this.n = n;
        w = new WeightedQuickUnionUF(n*n + 2);
        array = new boolean[n*n];
        for(int a = 0; a< n; a++){
            w.union(n*n, a);
            w.union(n*n+1, n*n-1-a);
        }
    }              // create n-by-n grid, with all sites blocked

    public void open(int row, int col){
        testN(row, col);
        int elem = n* (row-1) + (col-1);
        if(array[elem])
            return;
        array[elem] = true;
        open++;

        if (elem % n != 0 && array[elem-1]){
            //System.out.println('1');
            w.union(elem, elem-1);
        }
        if ((elem+1) % n != 0 && array[elem+1]){
            //System.out.println('2');
            w.union(elem, elem+1);
        }
        if (elem-n >= 0 && array[elem-n]){
            //System.out.println('3');
            w.union(elem, elem-n);
        }
        if (elem+n < n*n && array[elem+n]){
            //System.out.println('4');
            w.union(elem+n, elem);
        }
    }    // open site (row, col) if it is not open already

    public boolean isOpen(int row, int col){
        testN(row, col);
        return array[n* (row-1) + (col-1)];
    }  // is site (row, col) open?

    public boolean isFull(int row, int col){
        testN(row, col);
        int index = n * (row-1) + (col-1);
        if(!array[index]) return false;
        return w.connected(index, n*n);
    }  // is site (row, col) full?

    public int numberOfOpenSites(){
        return this.open;
    }       // number of open sites
    public boolean percolates()
    {
        if(n == 1){
            return array[0];
        }
        return w.connected(n*n, n*n+1);
    }              // does the system percolate?

    private void testN(int row, int col){
        if(row < 1 || row > n || col < 1 || col > n){
            throw new java.lang.IllegalArgumentException();
        }
    }
    public static void main(String[] args){
//        Percolation p = new Percolation(3);
//        //System.out.println(p.percolates());
//        p.open(1,1);
//        p.open(1,2);
//        p.open(2,2);
//        p.open(3,3);
//        p.open(2,3);
//
//        //System.out.println(p.numberOfOpenSites());
//        //p.open(1,1);
//        //p.open(1,2);
//        //PercolationVisualizer.draw(p, 3);
//        //System.out.println(p.isFull(2,1));
//        System.out.println(p.numberOfOpenSites());
//        System.out.println(p.percolates());
//        //PercolationVisualizer.draw(p, 3);
    }   // test client (optional)
}