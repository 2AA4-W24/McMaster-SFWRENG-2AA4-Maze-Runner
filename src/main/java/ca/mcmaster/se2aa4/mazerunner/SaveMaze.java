package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveMaze {

    public Integer[][] maze_array;

    private static final Logger logger = LogManager.getLogger();

    public SaveMaze(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        String[] rows = new String[1000];
        int i = 0;
        while ((line = reader.readLine()) != null) {
            rows[i] = line;
            i++;
        }

        maze_array = new Integer[i][rows[0].length()];

        for (int j = 0; j < i; j++) {
            for (int k = 0; k < rows[j].length(); k++) {
                if (rows[j].charAt(k) == '#') {
                    maze_array[j][k] = 1;
                }
                else if (rows[j].charAt(k) == ' ') {
                    maze_array[j][k] = 0;
                }
            }
        }
    }

    public Integer[][] mazeCopy() {
        Integer[][] copy = new Integer[maze_array.length][maze_array[0].length];

        for (int j = 0; j < copy.length; j++) {
            for (int k = 0; k < copy[0].length; k++) {
                copy[j][k] = maze_array[j][k];
            }
        }

        return copy;
    }

    public void logMaze() {
        for (int i = 0; i < maze_array.length; i++) {
            for (int j = 0; j < maze_array[0].length; j++) {
                System.out.print(maze_array[i][j]);
            }
            System.out.println();
        }
    }
}
