import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.*;

public class FastCollinearPoints {
    private final HashMap<Double, List<Point>> map = new HashMap<>();
    private final List<LineSegment> segments = new ArrayList<>();

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
    private void addSegmentIfNew(List<Point> slopePoints, double slope){
        List<Point> endPoints = map.get(slope);
        Collections.sort(slopePoints);
        Point startPoint = slopePoints.get(0);
        Point endPoint = slopePoints.get(slopePoints.size() - 1);

        if (endPoints == null){
            endPoints = new ArrayList<>();
            endPoints.add(endPoint);
            map.put(slope, endPoints);
            segments.add(new LineSegment(startPoint, endPoint));
        }else {
            for (Point currentEndPoint : endPoints){
                if(currentEndPoint.compareTo(endPoint) == 0){ return;}
            }
            endPoints.add(endPoint);
            segments.add(new LineSegment(startPoint, endPoint));
        }

    }
    public FastCollinearPoints(Point[] points){
        if(points == null) throw new java.lang.IllegalArgumentException();
        Point[] myPoints = Arrays.copyOf(points, points.length);
        checkDuplicates(myPoints);

        Point[] pointCopy = Arrays.copyOf(myPoints, myPoints.length);
        for (int a = 0; a < myPoints.length; a++) {
            Arrays.sort(pointCopy, myPoints[a].slopeOrder());
            List<Point> slopePoints = new ArrayList<>();
            double slope = 0;
            double previousSlope = Double.NEGATIVE_INFINITY;

            for (int i = 1; i < pointCopy.length; i++) {
                slope = myPoints[a].slopeTo(pointCopy[i]);
                if (slope == previousSlope) {
                    slopePoints.add(pointCopy[i]);
                } else {
                    if (slopePoints.size() >= 3) {
                        slopePoints.add(myPoints[a]);
                        addSegmentIfNew(slopePoints, slope);
                    }
                    slopePoints.clear();
                    slopePoints.add(pointCopy[i]);
                }
                previousSlope = slope;
            }
            if (slopePoints.size() >= 3) {
                slopePoints.add(myPoints[a]);
                addSegmentIfNew(slopePoints, slope);
            }
        }
    }
    public           int numberOfSegments(){return segments.size();}        // the number of line segments
    public LineSegment[] segments() {return segments.toArray(new LineSegment[segments.size()]);}               // the line segments

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
        Point[] points = {new Point(1, 0), new Point(2,0), new Point(3,0 ), new Point(4,4), new Point(5,0)};
        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}