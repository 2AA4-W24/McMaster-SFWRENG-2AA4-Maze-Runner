package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class EntryPoint {

    private Integer input_index;

    private Integer index_e;

    public void findEntry(String filename) throws IOException {
        BuildMaze maze_finder = new BuildMaze();

        maze_finder.saveMaze(filename);
        Integer[][] maze = maze_finder.getMaze();

        input_index = null;
        index_e = null;


        for (int i = 0; i < maze.length; i++) {
            if (maze[i][0] == 0) {
                input_index = i;
            }
        }

        for (int i = 0; i < maze.length; i++) {
            if (maze[i][maze[0].length - 1] == 0) {
                index_e = i;
            }
        }
    }

    public Integer getWestIndex() {
        Integer copy = input_index;
        return copy;
    }

    public Integer getEastIndex() {
        Integer copy = index_e;
        return copy;
    }
}
