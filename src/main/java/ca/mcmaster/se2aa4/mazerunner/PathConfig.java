package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathConfig implements PathFinder {

    private static final Logger logger = LogManager.getLogger();
    @Override
    public void findPath(String filename, EntryPoint start, BuildMaze maze_finder, ParseMaze scan, Position cords) throws IOException {
        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();

        String path = "";
        cords.initialize(0, start.getWestIndex(), 4);

        while (cords.x != maze[0].length - 1) {
            scan.findPos(cords.x, cords.y, cords.heading, maze_finder);
            Integer right_wall = scan.getRightWallStatus();
            Integer front_wall = scan.getFrontWallStatus();
            Integer right_wall_f = scan.getAheadWallStatus();
            if (front_wall == 0) {
                if ((right_wall_f == 0) && (right_wall == 1)) {
                    if (cords.x == 0) {
                        path += "F R F";
                    }
                    else if (path.charAt(path.length() - 1) != 'F') {
                        path += " F R F";
                    }
                    else {
                        path += "F R F";
                    }
                    cords.move_f();
                    cords.turn_r();
                    cords.move_f();
                }
                else {
                    if (cords.x == 0) {
                        path += "F";
                    }
                    else if (path.charAt(path.length() - 1) != 'F') {
                        path += " F";
                    }
                    else {
                        path += "F";
                    }
                    cords.move_f();
                }
            }
            else {
                if (cords.x == 0) {
                    path += "L";
                }
                else if (path.charAt(path.length() - 1) != 'L') {
                    path += " L";
                }
                else {
                    path += "L";
                }
                cords.turn_l();
            }
        }

        path += " ";

        path = PathOutput.factorPath(path);
        System.out.println(path);
    }
}
