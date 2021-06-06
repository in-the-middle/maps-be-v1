package ua.trip.maps.be.v1.geometry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.trip.maps.be.v1.geometry.model.Point;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConvexHullService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConvexHullService.class);

    public List<Point> buildConvexHull(List<Point> points) {
        int pointsSize = points.size();
        points.sort((pointA, pointB) -> {
            if (pointA.getX().equals(pointB.getX())) {
                if (pointA.getY().equals(pointB.getY())) {
                    return 0;
                }
                return pointA.getY() < pointB.getY() ? -1 : 1;
            }
            return pointA.getX() < pointB.getX() ? -1 : 1;
        });
        Point firstPoint = points.get(0), lastPoint = points.get(pointsSize - 1);
        List<Point> up = new ArrayList<>();
        List<Point> down = new ArrayList<>();
        up.add(points.get(0));
        down.add(points.get(0));
        for (int i = 1; i < pointsSize; i++) {
            Point currentPoint = points.get(i);
            if (orientation(firstPoint, lastPoint, currentPoint) == 1 || i == pointsSize - 1){
                while(up.size() >= 2 && orientation(up.get(up.size() - 2), up.get(up.size() - 1), currentPoint) != 2){
                    up.remove(up.size() - 1);
                }
                up.add(currentPoint);
            }
            if (orientation(firstPoint, lastPoint, currentPoint) == 2 || i == pointsSize - 1){
                while(down.size() >= 2 && orientation(down.get(down.size() - 2), down.get(down.size() - 1), currentPoint) != 1){
                    down.remove(down.size() - 1);
                }
                down.add(currentPoint);
            }
        }
        List<Point> convexHull = new ArrayList<>(down);
        for(int i = up.size() - 2; i > 0; i--){
            convexHull.add(up.get(i));
        }
        return convexHull;
    }

    // 0 --> a, b and c are colinear
    // 1 --> Clockwise
    // 2 --> Counterclockwise
    private int orientation(Point a, Point b, Point c) {
        double val = (b.getY() - a.getY()) * (c.getX() - b.getX()) -
                (b.getX() - a.getX()) * (c.getY() - b.getY());

        if (val == 0) return 0;  // collinear
        return (val > 0) ? 1 : 2; // clock or counterclock wise
    }
}
