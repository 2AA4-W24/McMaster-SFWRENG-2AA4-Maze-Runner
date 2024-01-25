package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntryPoint {

    Integer input_index;

    public void findEntry(String filename) throws IOException{
        SaveMaze maze_finder = new SaveMaze();
        maze_finder.buildMaze(filename);
        input_index = null;
        Integer[][] maze = maze_finder.maze_array;
        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == 0) {
                input_index = i;
            }
        }
    }
}
