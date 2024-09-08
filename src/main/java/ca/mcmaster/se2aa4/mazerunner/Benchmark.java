package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.io.*;
import java.math.BigDecimal;
import java.math.MathContext;

public class Benchmark {
    public void keepTime(String filename, String baseline, String method, EntryPoint start, BuildMaze maze_finder, ParseMaze scan, Position cords) throws IOException {
        long starttime = System.currentTimeMillis();
        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();
        long endtime = System.currentTimeMillis();
        System.out.println("Maze loading ran for " + (endtime - starttime) + "ms");
        String testpath = "";
        if (method.equals("righthand")) {
            PathConfig pathfinder = new PathConfig();
            starttime = System.currentTimeMillis();
            testpath = pathfinder.findPath(filename, start, maze_finder, scan, cords);
            endtime = System.currentTimeMillis();
            System.out.println("Maze exploration with method " + method + " ran for " + (endtime - starttime) + "ms");
        }
        else {
            QuickPath fastpath = new QuickPath();
            starttime = System.currentTimeMillis();
            testpath = fastpath.findPath(filename, start, maze_finder, scan, cords);
            endtime = System.currentTimeMillis();
            System.out.println("Maze exploration with method " + method + " ran for " + (endtime - starttime) + "ms");
        }
        String basepath = "";
        if (baseline.equals("righthand")) {
            PathConfig pathfinder = new PathConfig();
            starttime = System.currentTimeMillis();
            basepath = pathfinder.findPath(filename, start, maze_finder, scan, cords);
            endtime = System.currentTimeMillis();
            System.out.println("Maze exploration with baseline " + baseline + " ran for " + (endtime - starttime) + "ms");
        }
        else {
            QuickPath fastpath = new QuickPath();
            starttime = System.currentTimeMillis();
            basepath = fastpath.findPath(filename, start, maze_finder, scan, cords);
            endtime = System.currentTimeMillis();
            System.out.println("Maze exploration with baseline " + baseline + " ran for " + (endtime - starttime) + "ms");
        }

        Double baselength = 0.0;
        Double testlength = 0.0;
        Character current;
        for (int i = 0; i < basepath.length(); i++) {
            current = basepath.charAt(i);

            if (current != ' ') {
                if (Character.isDigit(current)) {
                    baselength += Character.getNumericValue(current);
                    i++;
                }
                else {
                    baselength++;
                }
            }
        }
        for (int i = 0; i < testpath.length(); i++) {
            current = testpath.charAt(i);

            if (current != ' ') {
                if (Character.isDigit(current)) {
                    testlength += Character.getNumericValue(current);
                    i++;
                }
                else {
                    testlength++;
                }
            }
        }
        Double speedup = baselength / testlength;
        BigDecimal bd = new BigDecimal(speedup);
        bd = bd.round(new MathContext(2));
        speedup = bd.doubleValue();
        System.out.println("Speedup is " + speedup);
    }
}
