package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public interface PathFinder {
    String findPath(String filename, EntryPoint start, BuildMaze maze_finder, ParseMaze scan, Position cords) throws IOException;
}
