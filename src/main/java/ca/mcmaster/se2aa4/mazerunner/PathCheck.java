package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class PathCheck {

    private static final Logger logger = LogManager.getLogger();

    public void checkPath(String filename, String path_in, Position cords, BuildMaze maze_finder, EntryPoint start) throws IOException {
        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        cords.initialize(0, start.getWestIndex(), 4);
        Integer factored = 0;
        Integer[][] maze = maze_finder.getMaze();

        for (int i = 0; i < path_in.length(); i++) {
            if (Character.isDigit(path_in.charAt(i))) {
                factored = 1;
            }
        }

        if (factored == 1) {
            path_in = this.expandPath(path_in);
        }
        else {
            path_in.replaceAll("\\s", "");
        }

        Integer attempt = 1;
        Boolean test = this.traversePath(filename, path_in, attempt, cords);

        if (test) {
            System.out.println("correct path");
        }
        else {
            cords.initialize(maze[0].length - 1, start.getEastIndex(), 2);

            attempt = 2;
            Boolean second_test = this.traversePath(filename, path_in, attempt, cords);

            if (second_test) {
                System.out.println("correct path");
            }
            else {
                System.out.println("incorrect path");
            }
        }
    }

    private Boolean traversePath(String filename, String path_in, Integer attempt, Position cords) throws IOException {
        BuildMaze maze_finder = new BuildMaze();

        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();
        Integer end;

        if (attempt == 1) {
            end = maze[0].length - 1;
        }
        else {
            end = 0;
        }

        int j = 0;
        while ((!(cords.x.equals(end))) && (maze[cords.y][cords.x] != 1) && (j < path_in.length())) {
            switch (path_in.charAt(j)) {
                case 'F':
                    cords.move_f();
                    break;
                case 'R':
                    cords.turn_r();
                    break;
                case 'L':
                    cords.turn_l();
                    break;
            }
            j++;
        }

        if ((cords.x.equals(end)) && (maze[cords.y][cords.x] == 0) && (j == path_in.length())) {
            return true;
        }
        else {
            return false;
        }
    }

    private String expandPath(String path_in) {
        String new_path = "";
        Integer count;
        Character move_type;
        Character current;

        if (!(path_in.equals(""))) {
            for (int i = 0; i < path_in.length(); i++) {
                current = path_in.charAt(i);

                if (current != ' ') {
                    if (Character.isDigit(current)) {
                        count = Character.getNumericValue(current);
                        if (count == 1) {
                            count = 10 + Character.getNumericValue(path_in.charAt(i+1));
                            i += 2;
                            move_type = path_in.charAt(i);
                            for (int k = 0; k < count; k++) {
                                new_path += Character.toString(move_type);
                            }
                        }
                        else {
                            i++;
                            move_type = path_in.charAt(i);
                            for (int j = 0; j < count; j++) {
                                new_path += Character.toString(move_type);
                            }
                        }
                    }
                    else {
                        move_type = path_in.charAt(i);
                        new_path += Character.toString(move_type);
                    }
                }
            }
        }

        return new_path;
    }
}
