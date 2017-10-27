package week3;

import week3.LineSegment;
import week3.Point;

import java.util.Arrays;

public class BruteCollinearPoints {
    private int number;
    private LineSegment[] array;
    public BruteCollinearPoints(Point[] points){
        if(points == null) throw new java.lang.IllegalArgumentException();
        Arrays.sort(points);
        for(int a = 0; a< points.length; a++){
            if(points[a] == null) throw new java.lang.IllegalArgumentException();
            if(a > 0){
                if(points[a].compareTo(points[a-1]) == 0) throw new java.lang.IllegalArgumentException();
            }
        }
        if(points[0].slopeTo(points[1]) == points[0].slopeTo(points[2]) && points[0].slopeTo(points[1]) == points[0].slopeTo(points[3])){
            number = 1;
            array = new LineSegment[1];
            array[0] = new LineSegment(points[0], points[3]);
        } else {
            number = 0;
        }
    }    // finds all line segments containing 4 points
    public           int numberOfSegments(){
        return number;
    }        // the number of line segments
    public LineSegment[] segments(){
        return array;
    }                // the line segments
}