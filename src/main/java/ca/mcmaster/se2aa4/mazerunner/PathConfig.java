package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathConfig implements PathFinder {

    private static final Logger logger = LogManager.getLogger();

    private String path;
    @Override
    public void findPath(String filename, EntryPoint start, BuildMaze maze_finder, ParseMaze scan, Position cords) throws IOException {
        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();

        path = "";
        cords.initialize(0, start.getWestIndex(), 4);

        int i = 0;
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

        this.factorPath();
        System.out.println(path);
    }

    private void factorPath() {
        String new_path = "";
        Integer count;
        Character move_type;

        if (!(path.equals(""))) {
            for (int i = 0; i < path.length() - 1; i++) {
                count = 0;
                move_type = path.charAt(i);
                if (move_type != ' ') {
                    count += 1;
                    for (int j = i + 1; path.charAt(j) == move_type; j++) {
                        count += 1;
                    }
                }
                if (count > 1) {
                    new_path += count + Character.toString(move_type) + " ";
                }
                else {
                    new_path += move_type + " ";
                }
                i += count;
            }
        }

        path = new_path;
    }
}
