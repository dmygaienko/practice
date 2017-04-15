package com.mygaienko.common.algorithms.condingame.very_hard.mars_lander;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Created by enda1n on 15.04.2017.
 */
public class PlayerTest {

    @Test
    public void testCalculateCurrentDirection() throws Exception {
        Player.Vector v = Player.calculateCurrentVector(10, 5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection1() throws Exception {
        Player.Vector v = Player.calculateCurrentVector(10, -5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection2() throws Exception {
        Player.Vector v = Player.calculateCurrentVector(-10, -5);
        System.out.println(v);
    }

    @Test
    public void testCalculateCurrentDirection3() throws Exception {
        Player.Vector v = Player.calculateCurrentVector(-10, 5);
        System.out.println(v);
    }

    @Test
    public void testCalculateDesiredDirection() throws Exception {
        Player.Vector v = Player.calculateDesiredDirection(new Player.Point(0, 0), new Player.Point(1, 1));
        System.out.println(v);
    }

    @Test
    public void testCalculateDesiredDirection1() throws Exception {
        Player.Vector v = Player.calculateDesiredDirection(new Player.Point(3, 4), new Player.Point(1, 1));
        System.out.println(v);
    }

    @Test
    public void testCalculateDesiredDirection2() throws Exception {
        Player.Vector v = Player.calculateDesiredDirection(new Player.Point(-3, -4), new Player.Point(1, 1));
        System.out.println(v);
    }

    @Test
    public void testGetAngle() throws Exception {
        Player.Vector vector = new Player.Vector(-4, -5);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testGetAngle1() throws Exception {
        Player.Vector vector = new Player.Vector(-4, 5);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testGetAngle2() throws Exception {
        Player.Vector vector = new Player.Vector(4, 5);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testGetAngle3() throws Exception {
        Player.Vector vector = new Player.Vector(4, -5);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testGetAngle4() throws Exception {
        Player.Vector vector = new Player.Vector(0, 0);
        System.out.println(vector);
    }

    @Test
    public void testGetAngle5() throws Exception {
        Player.Vector vector = new Player.Vector(0, 1);
        System.out.println(vector);
    }


    @Test
    public void testGetAngle55() throws Exception {
        Player.Vector vector = new Player.Vector(0, -1);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testGetAngle6() throws Exception {
        Player.Vector vector = new Player.Vector(1, 1);
        System.out.println(vector);
    }

    @Test
    public void testGetAngle7() throws Exception {
        Player.Vector vector = new Player.Vector(1, 0);
        System.out.println(vector);
    }

    @Test
    public void testGetAngle8() throws Exception {
        Player.Vector vector = new Player.Vector(-1, 0);
        System.out.println(vector.getAngle());
    }

    @Test
    public void testFindFlatGround() throws Exception {
        Player.FlatGround flatGround = Player.findFlatGround(Arrays.asList(
                new Player.Point(0, 100),
                new Player.Point(1000, 500),
                new Player.Point(1500, 1500),
                new Player.Point(3000, 1000),
                new Player.Point(4000, 150),
                new Player.Point(5500, 150),
                new Player.Point(6999, 800)
        ));
        System.out.println(flatGround);
    }

    @Test
    public void main() throws Exception {
        String inputs = getGameInputString1();
        System.setIn(new ByteArrayInputStream(inputs.getBytes(StandardCharsets.UTF_8)));
        Player.main(new String[]{});
    }

    private String getGameInputString1() {
        return "7\n" +
                "0 100\n" +
                "1000 500\n" +
                "1500 1500\n" +
                "3000 1000\n" +
                "4000 150\n" +
                "5500 150\n" +
                "6999 800\n" +

                "2500 2700\n" +
                "0 0\n" +
                "8281\n" +
                "0 0\n" ;
    }

    private String getGameInputString6() {
        return "20\n" +
                "0 1000\n" +
                "300 1500\n" +
                "350 1400\n" +
                "500 2100\n" +
                "1500 2100\n" +
                "2000 200\n" +
                "2500 500\n" +
                "2900 300\n" +
                "3000 200\n" +
                "3200 1000\n" +
                "3500 500\n" +
                "3800 800\n" +
                "4000 200\n" +
                "4200 800\n" +
                "4800 600\n" +
                "5000 1200\n" +
                "5500 900\n" +
                "6000 500\n" +
                "6500 300\n" +
                "6999 500\n" +

                "3269 1565\n" +
                "-84 -29\n" +
                "8281\n" +
                "18 4\n" ;
    }

}