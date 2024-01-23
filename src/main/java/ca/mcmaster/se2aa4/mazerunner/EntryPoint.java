package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EntryPoint {

    EntryIndex Index;

    public EntryPoint(String filename) throws IOException {
        SaveMaze maze_finder = new SaveMaze(filename);
        Integer[][] maze_copy = maze_finder.mazeCopy();
        Integer input_index = null;
        for (int i = 0; i < maze_copy.length; i++) {
            if (maze_copy[i][0] == 0) {
                input_index = i;
            }
        }

        Index = new EntryIndex(input_index);

    }

    public Integer getIndexCopy() {
        Integer copy_index = Index.entry_index;
        EntryIndex Copy = new EntryIndex(copy_index);
        return Copy.entry_index;
    }


}
