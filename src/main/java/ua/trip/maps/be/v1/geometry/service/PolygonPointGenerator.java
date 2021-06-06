package ua.trip.maps.be.v1.geometry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.trip.maps.be.v1.geometry.model.Point;

import java.util.ArrayList;
import java.util.List;

@Service
public class PolygonPointGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(PolygonPointGenerator.class);

    @Autowired
    TrianglePointGenerator trianglePointGenerator;

    public List<Point> generatePoints(List<Point> points, int numberOfPointsToGenerate) {
        List<Point> result = new ArrayList<>();
        double polygonArea = 0.0;
        for (int i = 2; i < points.size(); i++) {
            polygonArea += calculateTriangleArea(points.get(0), points.get(i - 1), points.get(i));
        }
        for (int i = 2; i < points.size(); i++) {
            double triangleArea = calculateTriangleArea(points.get(0), points.get(i - 1), points.get(i));
            int numberOfPointsPerCurrentTriangle = (int) (numberOfPointsToGenerate * triangleArea / polygonArea);
            result.addAll(trianglePointGenerator.generatePoints(points.get(0), points.get(i - 1), points.get(i), numberOfPointsPerCurrentTriangle));
        }
        return result;
    }

    private double calculateTriangleArea(Point a, Point b, Point c) {
        return Math.abs((b.getX() - a.getX()) * (c.getY() - a.getY()) - (b.getY() - a.getY()) * (c.getX() - a.getX())) / 2.0;
    }
}
