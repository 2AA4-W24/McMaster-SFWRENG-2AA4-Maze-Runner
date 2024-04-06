package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

interface MazeSolver {
    void getPath(String filename) throws IOException;
    void getQuickPath(String filename);
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

    public void build() {
        pathfinder = new PathConfig();
        fastpath = new QuickPath();
        checker = new PathCheck();
        scan = new ParseMaze();
        entry = new EntryPoint();
        mazebuilder = new BuildMaze();
        cords = new Position();
    }

    @Override
    public void getPath(String filename) throws IOException {
        pathfinder.findPath(filename, entry, mazebuilder, scan, cords);
    }

    @Override
    public void getQuickPath(String filename) {

    }

    @Override
    public void checkPath(String filename, String path_in) throws IOException {
        checker.checkPath(filename, path_in, cords, mazebuilder, entry);
    }
}
