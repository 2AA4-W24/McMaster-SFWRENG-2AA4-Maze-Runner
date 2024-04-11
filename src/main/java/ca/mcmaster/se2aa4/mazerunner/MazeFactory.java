package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

interface MazeSolver {
    void getPath(String filename) throws IOException;
    void getQuickPath(String filename) throws IOException;
    void checkPath(String filename, String path_in) throws IOException;
}

public class MazeFactory implements MazeSolver {
    PathConfig pathfinder;
    QuickPath fastpath;
    PathCheck checker;
    ParseMaze scan;
    EntryPoint entry;
    BuildMaze mazebuilder;
    Position cords;
    Benchmark bench;

    public void build() {
        pathfinder = new PathConfig();
        fastpath = new QuickPath();
        checker = new PathCheck();
        scan = new ParseMaze();
        entry = new EntryPoint();
        mazebuilder = new BuildMaze();
        cords = new Position();
        bench = new Benchmark();
    }

    @Override
    public void getPath(String filename) throws IOException {
        System.out.println(pathfinder.findPath(filename, entry, mazebuilder, scan, cords));
    }

    @Override
    public void getQuickPath(String filename) throws IOException {
        System.out.println(fastpath.findPath(filename, entry, mazebuilder, scan, cords));
    }

    @Override
    public void checkPath(String filename, String path_in) throws IOException {
        checker.checkPath(filename, path_in, cords, mazebuilder, entry);
    }

    public void benchmark(String filename, String method, String baseline) throws IOException {
        bench.keepTime(filename, baseline, method, entry, mazebuilder, scan, cords);
    }
}
