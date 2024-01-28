package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;

public class PathCheck {

    private static final Logger logger = LogManager.getLogger();

    private Integer x_position;

    private Integer y_position;

    private Integer heading;

    public void checkPath(String filename, String path_in) throws IOException {
        BuildMaze maze_finder = new BuildMaze();
        EntryPoint start = new EntryPoint();

        start.findEntry(filename);
        maze_finder.saveMaze(filename);
        MazeRecord record = maze_finder.recordCopy();
        IndexRecord start_pos = start.indexCopy();

        x_position = 0;
        y_position = start_pos.index;
        heading = 4;
        Integer factored = 0;
        Integer[][] maze = record.maze;

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
            y_position = start_pos.index_e;
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

    private Boolean traversePath(String filename, String path_in, Integer attempt) throws IOException {
        BuildMaze maze_finder = new BuildMaze();

        maze_finder.saveMaze(filename);
        MazeRecord record = maze_finder.recordCopy();

        Integer[][] maze = record.maze;
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
