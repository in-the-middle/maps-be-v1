package ua.trip.maps.be.v1.geometry.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ua.trip.maps.be.v1.MapsApplication;
import ua.trip.maps.be.v1.geometry.model.Point;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = MapsApplication.class)
@RunWith(SpringRunner.class)
public class ConvexHullServiceTest {

    @Autowired
    ConvexHullService convexHullService;

    @Test
    public void convexHullTest1() {
        List<Point> correctConvexHull = Arrays.asList(new Point(-9.0, 0.0), new Point(-7.0, 7.0), new Point(0.0, 9.0), new Point(7.0, 7.0), new Point(9.0, 0.0), new Point(7.0, -7.0), new Point(0.0, -9.0), new Point(-7.0, -7.0));
        List<Point> points = Arrays.asList(new Point(7.0, 7.0), new Point(7.0, -7.0), new Point(-7.0, -7.0), new Point(-7.0, 7.0), new Point(9.0, 0.0), new Point(-9.0, 0.0), new Point(0.0, 9.0), new Point(0.0, -9.0));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }

    @Test
    public void convexHullTest2() {
        List<Point> correctConvexHull = Arrays.asList(new Point(-9.0, 0.0), new Point(-7.0, 7.0), new Point(0.0, 9.0), new Point(7.0, 7.0), new Point(9.0, 0.0), new Point(7.0, -7.0), new Point(0.0, -9.0), new Point(-7.0, -7.0));
        List<Point> points = Arrays.asList(new Point(7.0, 7.0), new Point(7.0, -7.0), new Point(-7.0, -7.0), new Point(-7.0, 7.0), new Point(9.0, 0.0), new Point(-9.0, 0.0), new Point(0.0, 9.0), new Point(0.0, -9.0), new Point(0.0, 0.0), new Point(1.0, 2.0), new Point(-2.0, 1.0), new Point(-1.0, -1.0), new Point(3.0, 4.0), new Point(4.0, 3.0), new Point(-5.0, 4.0), new Point(6.0, 5.0));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }

    @Test
    public void convexHullTest3() {
        List<Point> correctConvexHull = Arrays.asList(new Point(-9.0, 0.0), new Point(-7.0, 7.0), new Point(0.0, 9.0), new Point(7.0, 7.0), new Point(9.0, 0.0), new Point(7.0, -7.0), new Point(0.0, -9.0), new Point(-7.0, -7.0));
        List<Point> points = Arrays.asList(new Point(7.0, 7.0), new Point(7.0, -7.0), new Point(-7.0, -7.0), new Point(-7.0, 7.0), new Point(9.0, 0.0), new Point(-9.0, 0.0), new Point(0.0, 9.0), new Point(0.0, -9.0), new Point(0.0, 0.0), new Point(1.0, 2.0), new Point(-2.0, 1.0), new Point(-1.0, -1.0), new Point(3.0, 4.0), new Point(4.0, 3.0), new Point(-5.0, 4.0), new Point(6.0, 5.0), new Point(-9.0, 0.0), new Point(0.0, 8.0), new Point(0.0, -7.0), new Point(0.0, 7.0), new Point(0.0, -6.0), new Point(0.0, 6.0), new Point(0.0, -5.0), new Point(0.0, 5.0), new Point(0.0, -4.0), new Point(0.0, 4.0), new Point(0.0, -3.0), new Point(0.0, 3.0), new Point(0.0, -2.0), new Point(0.0, 2.0), new Point(0.0, -1.0), new Point(0.0, 1.0), new Point(0.0, 0.0), new Point(-8.0, 0.0), new Point(8.0, 0.0), new Point(-7.0, 0.0), new Point(7.0, 0.0), new Point(-6.0, 0.0), new Point(6.0, 0.0), new Point(-5.0, 0.0), new Point(5.0, 0.0), new Point(-4.0, 0.0), new Point(4.0, 0.0), new Point(-3.0, 0.0), new Point(3.0, 0.0), new Point(-2.0, 0.0), new Point(2.0, 0.0), new Point(-1.0, 0.0));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }

    @Test
    public void convexHullTest4() {
        List<Point> correctConvexHull = Arrays.asList(new Point(229.0, 544.0), new Point(732.0, 590.0), new Point(299.0, 95.0));
        List<Point> points = Arrays.asList(new Point(732.0, 590.0), new Point(415.0, 360.0), new Point(276.0, 276.0), new Point(229.0, 544.0), new Point(299.0, 95.0));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }

    @Test
    public void convexHullTest5() {
        List<Point> correctConvexHull = Arrays.asList(new Point(82.0, 860.0), new Point(441.0, 932.0), new Point(635.0, 382.0), new Point(165.0, 142.0));
        List<Point> points = Arrays.asList(new Point(441.0, 932.0), new Point(277.0, 526.0), new Point(82.0, 860.0), new Point(635.0, 382.0), new Point(165.0, 142.0));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }

    @Test
    public void convexHullTest6() {
        List<Point> correctConvexHull = Arrays.asList(new Point(-0.4907368011686362, 0.1865826865533206), new Point(-0.3521487911717489, 0.4352656197131292), new Point(0.05054295812784038, 0.4754929463150845), new Point(0.4932166845474547, 0.4928094162538735), new Point(0.4916198379282093, -0.345391701297268), new Point(0.4823896228171788, -0.4776170002088109), new Point(-0.161920957418085, -0.4055339716426413), new Point(-0.4404289572876217, -0.2894855991839297));
        List<Point> points = Arrays.asList(new Point(0.3215348546593775, 0.03629583077160248), new Point(0.02402358131857918, -0.2356728797179394), new Point(0.04590851212470659, -0.4156409924995536), new Point(0.3218384001607433, 0.1379850698988746), new Point(0.11506479756447, -0.1059521474930943), new Point(0.2622539999543261, -0.29702873322836), new Point(-0.161920957418085, -0.4055339716426413), new Point(0.1905378631228002, 0.3698601009043493), new Point(0.2387090918968516, -0.01629827079949742), new Point(0.07495888748668034, -0.1659825110491202), new Point(0.3319341836794598, -0.1821814101954749), new Point(0.07703635755650362, -0.2499430638271785), new Point(0.2069242999022122, -0.2232970760420869), new Point(0.04604079532068295, -0.1923573186549892), new Point(0.05054295812784038, 0.4754929463150845), new Point(-0.3900589168910486, 0.2797829520700341), new Point(0.3120693385713448, -0.0506329867529059), new Point(0.01138812723698857, 0.4002504701728471), new Point(0.009645149586391732, 0.1060251100976254), new Point(-0.03597933197019559, 0.2953639456959105), new Point(0.1818290866742182, 0.001454397571696298), new Point(0.444056063372694, 0.2502497166863175), new Point(-0.05301752458607545, -0.06553921621808712), new Point(0.4823896228171788, -0.4776170002088109), new Point(-0.3089226845734964, -0.06356112199235814), new Point(-0.271780741188471, 0.1810810595574612), new Point(0.4293626522918815, 0.2980897964891882), new Point(-0.004796652127799228, 0.382663812844701), new Point(0.430695573269106, -0.2995073500084759), new Point(0.1799668387323309, -0.2973467472915973), new Point(0.4932166845474547, 0.4928094162538735), new Point(-0.3521487911717489, 0.4352656197131292), new Point(-0.4907368011686362, 0.1865826865533206), new Point(-0.1047924716070224, -0.247073392148198), new Point(0.4374961861758457, -0.001606279519951237), new Point(0.003256207800708899, -0.2729194320486108), new Point(0.04310378203457577, 0.4452604050238248), new Point(0.4916198379282093, -0.345391701297268), new Point(0.001675087028811806, 0.1531837672490476), new Point(-0.4404289572876217, -0.2894855991839297));
        List<Point> convexHull = convexHullService.getConvexHull(points);
        Assertions.assertEquals(correctConvexHull.size(), convexHull.size());
        for (int i = 0; i < correctConvexHull.size(); i++) {
            Assertions.assertEquals(correctConvexHull.get(i), convexHull.get(i));
        }
    }
}
