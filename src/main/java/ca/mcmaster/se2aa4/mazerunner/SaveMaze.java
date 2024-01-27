package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SaveMaze {

    public Integer[][] maze_array;

    public String[] rows;

    public void buildMaze(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        String line;

        rows = new String[1000];
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

        for (int l = 0; l < maze_array.length; l++) {
            for (int w = 0; w < maze_array[0].length; w++) {
                if (maze_array[l][w] == null) {
                    maze_array[l][w] = 0;
                }
            }
        }
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
