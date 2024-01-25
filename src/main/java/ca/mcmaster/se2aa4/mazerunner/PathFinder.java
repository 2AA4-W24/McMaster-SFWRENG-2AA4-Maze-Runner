package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathFinder {

    private static final Logger logger = LogManager.getLogger();

    String path;

    Integer x_position;

    Integer y_position;

    public void findPath(String filename) throws IOException {
        EntryPoint start = new EntryPoint(filename);
        SaveMaze record = new SaveMaze(filename);


        EntryIndex copy = start.getIndexCopy();
        Integer[][] maze_copy = record.mazeCopy();

        path = "";

        for (int i = 1; i < maze_copy[0].length; i++) {
            if (maze_copy[copy.index][i] == 0) {
                path += "F";
            }
        }

        logger.info("Saved Maze: ");
        record.mazeOut();
    }

    public PathFinder pathCopy() {
        PathFinder copy = new PathFinder();
        copy.path = path;
        return copy;
    }


}
