package ua.trip.maps.be.v1.geometry.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ua.trip.maps.be.v1.geometry.model.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class TrianglePointGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrianglePointGenerator.class);

    public List<Point> generatePoints(Point a, Point b, Point c, int numberOfPoints){
        List<Point> result = new ArrayList<>();
        for(int i = 0; i < numberOfPoints; i++){
            result.add(generatePoint(a, b, c));
        }
        return result;
    }

    private Point generatePoint(Point a, Point b, Point c){
        List<Point> points = Arrays.asList(a, b, c);
        Collections.shuffle(points);

        // uniformly sampled point along that edge,
        double randomNum = Math.random();
        double xTemp = (1 - randomNum) * points.get(0).getX() + randomNum * points.get(1).getX();
        double yTemp = (1 - randomNum) * points.get(0).getY() + randomNum * points.get(1).getY();

        // uniformly sampled point along that edge,
        double randomNumSecondSquared = Math.sqrt(Math.random());
        double xRes = (1 - randomNumSecondSquared) * points.get(2).getX() + randomNumSecondSquared * xTemp;
        double yRes = (1 - randomNumSecondSquared) * points.get(2).getY() + randomNumSecondSquared * yTemp;

        return new Point(xRes, yRes);
    }
}
