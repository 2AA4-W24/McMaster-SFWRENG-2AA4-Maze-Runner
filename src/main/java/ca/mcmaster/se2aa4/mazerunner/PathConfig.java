package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PathConfig implements PathFinder {

    private static final Logger logger = LogManager.getLogger();

    private String path;

    private Integer x_position;

    private Integer y_position;

    private Integer heading; // 1-4 denote N to E, counter-clockwise

    @Override
    public void findPath(String filename) throws IOException {
        EntryPoint start = new EntryPoint();
        BuildMaze maze_finder = new BuildMaze();
        ParseMaze scan = new ParseMaze();

        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();

        path = "";
        x_position = 0;
        y_position = start.getWestIndex();
        heading = 4;

        int i = 0;
        while (x_position != maze[0].length - 1) {
            scan.findPos(x_position, y_position, heading, maze_finder);
            Integer right_wall = scan.getRightWallStatus();
            Integer front_wall = scan.getFrontWallStatus();
            Integer right_wall_f = scan.getAheadWallStatus();
            if (front_wall == 0) {
                if ((right_wall_f == 0) && (right_wall == 1)) {
                    if (x_position == 0) {
                        path += "F R F";
                    }
                    else if (path.charAt(path.length() - 1) != 'F') {
                        path += " F R F";
                    }
                    else {
                        path += "F R F";
                    }
                    this.move_f();
                    this.turn_r();
                    this.move_f();
                }
                else {
                    if (x_position == 0) {
                        path += "F";
                    }
                    else if (path.charAt(path.length() - 1) != 'F') {
                        path += " F";
                    }
                    else {
                        path += "F";
                    }
                    this.move_f();
                }
            }
            else {
                if (x_position == 0) {
                    path += "L";
                }
                else if (path.charAt(path.length() - 1) != 'L') {
                    path += " L";
                }
                else {
                    path += "L";
                }
                this.turn_l();
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

    public void check(String filename, String path_in) throws IOException {
        PathCheck scan = new PathCheck();
        scan.checkPath(filename, path_in);
    }

    private void move_f() {
        switch(heading) {
            case 1:
                y_position -= 1;
                break;
            case 2:
                x_position -= 1;
                break;
            case 3:
                y_position += 1;
                break;
            case 4:
                x_position += 1;
                break;
        }
    }

    private void turn_r() {
        switch(heading) {
            case 1:
                heading = 4;
                break;
            case 2:
                heading = 1;
                break;
            case 3:
                heading = 2;
                break;
            case 4:
                heading = 3;
                break;
        }
    }

    private void turn_l() {
        switch(heading) {
            case 1:
                heading = 2;
                break;
            case 2:
                heading = 3;
                break;
            case 3:
                heading = 4;
                break;
            case 4:
                heading = 1;
                break;
        }
    }
}
