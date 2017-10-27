package week3;

import java.util.Arrays;

public class FastCollinearPoints {
    public FastCollinearPoints(Point[] points){
        if(points == null) throw new java.lang.IllegalArgumentException();
        Arrays.sort(points);
        for(int a = 0; a< points.length; a++){
            if(points[a] == null) throw new java.lang.IllegalArgumentException();
            if(a > 0){
                if(points[a].compareTo(points[a-1]) == 0) throw new java.lang.IllegalArgumentException();
            }
        }


    }     // finds all line segments containing 4 or more points
    public           int numberOfSegments()        // the number of line segments
    public LineSegment[] segments()                // the line segments
}