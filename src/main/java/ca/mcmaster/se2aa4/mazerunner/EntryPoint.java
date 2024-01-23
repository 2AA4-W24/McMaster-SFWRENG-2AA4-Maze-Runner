package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EntryPoint {

    EntryIndex Index;

    public EntryPoint(String input_file) throws IOException {
        //Parse input file for entry position, pass the row index of this position as the entry_index attribute for the EntryIndex Class
        Integer input_index = 0;
        Index = new EntryIndex(input_index);
    }

    public Integer getIndexCopy() {
        Integer copy_index = Index.entry_index;
        EntryIndex Copy = new EntryIndex(copy_index);
        return Copy.entry_index;
    }
}
