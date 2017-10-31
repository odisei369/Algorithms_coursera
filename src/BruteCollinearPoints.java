import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    private Point[] myPoints;
    private final LineSegment[] arr;
    private final ArrayList<LineSegment> array = new ArrayList<>();

    private void checkDuplicates(Point[] points){
        for(int a = 0; a< points.length; a++) {
            if (points[a] == null) throw new java.lang.IllegalArgumentException();
        }
        Arrays.sort(points);
        for(int a = 0; a< points.length; a++) {
            if (a > 0) {
                if (points[a].compareTo(points[a - 1]) == 0) throw new java.lang.IllegalArgumentException();
            }
        }
    }

    public BruteCollinearPoints(Point[] points){
        if(points == null) throw new java.lang.IllegalArgumentException();
        myPoints = Arrays.copyOf(points, points.length);
        checkDuplicates(myPoints);
        for(int a = 0; a< myPoints.length; a++){
            for(int q = a + 1; q < myPoints.length - 2; q++){
                for ( int r = q + 1; r < myPoints.length - 1; r++){
                    for(int s = r + 1; s < myPoints.length; s ++){
                        if(myPoints[a].slopeTo(myPoints[q]) == myPoints[a].slopeTo(myPoints[r]) && myPoints[a].slopeTo(myPoints[q]) == myPoints[a].slopeTo(myPoints[s])){
                            array.add(new LineSegment(myPoints[a], myPoints[s]));
                        }
                    }
                }
            }
        }
        arr = array.toArray(new LineSegment[array.size()]);

    }    // finds all line segments containing 4 myPoints
    public           int numberOfSegments(){
        return arr.length;
    }        // the number of line segments
    public LineSegment[] segments(){
        return Arrays.copyOf(arr, arr.length);
    }                // the line segments

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//            System.out.println(points[i]);
//        }
//
//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
        Point[] points = {new Point(1, 0), new Point(2,0), new Point(3,0 ), new Point(4,0), null};
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}