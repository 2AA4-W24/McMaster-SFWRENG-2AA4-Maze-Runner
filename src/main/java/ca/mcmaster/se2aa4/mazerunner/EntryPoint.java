package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;

public class EntryPoint {

    private Integer input_index;

    private Integer index_e;

    public void findEntry(String filename) throws IOException {
        BuildMaze maze_finder = new BuildMaze();

        maze_finder.saveMaze(filename);
        MazeRecord record = maze_finder.recordCopy();
        Integer[][] maze = record.maze;

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

    public IndexRecord indexCopy() {
        IndexRecord copy = new IndexRecord(input_index, index_e);
        return copy;
    }
}
