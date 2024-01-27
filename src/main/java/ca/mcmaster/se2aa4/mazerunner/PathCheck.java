package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.util.Objects;

public class PathCheck extends PathFinder {

    private static final Logger logger = LogManager.getLogger();

    public void checkPath(String filename, String path_in) throws IOException {
        SaveMaze record = new SaveMaze();
        EntryPoint start = new EntryPoint();

        start.findEntry(filename);
        record.buildMaze(filename);

        x_position = 0;
        y_position = start.input_index;
        heading = 4;
        Integer factored = 0;
        Integer[][] maze = record.maze_array;

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
        Boolean test = this.traversePath(filename, path_in, attempt);

        if (test) {
            System.out.println("correct path");
        }
        else {
            x_position = maze[0].length - 1;
            y_position = start.index_e;
            heading = 2;

            attempt = 2;
            Boolean second_test = this.traversePath(filename, path_in, attempt);

            if (second_test) {
                System.out.println("correct path");
            }
            else {
                System.out.println("incorrect path");
            }
        }
    }

    public Boolean traversePath(String filename, String path_in, Integer attempt) throws IOException {
        SaveMaze record = new SaveMaze();

        record.buildMaze(filename);

        Integer[][] maze = record.maze_array;
        Integer end;

        if (attempt == 1) {
            end = maze[0].length - 1;
        }
        else {
            end = 0;
        }

        int j = 0;
        while ((!(x_position.equals(end))) && (maze[y_position][x_position] != 1) && (j < path_in.length())) {
            switch (path_in.charAt(j)) {
                case 'F':
                    this.move_f();
                    break;
                case 'R':
                    this.turn_r();
                    break;
                case 'L':
                    this.turn_l();
                    break;
            }
            j++;
        }

        if ((x_position.equals(end)) && (maze[y_position][x_position] == 0) && (j == path_in.length())) {
            return true;
        }
        else {
            return false;
        }
    }

    public String expandPath(String path_in) {
        String new_path = "";
        Integer count = 0;
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
