package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class ParseMaze {

    public Integer start_position;

    public String filename;

    Position WallStatus;

    public ParseMaze(String input_name) {
        filename = input_name;
    }

    public void findStart() throws IOException {
        EntryPoint Start = new EntryPoint(filename);
        start_position = Start.getIndexCopy();
    }

    public void findSurroundings(Integer x, Integer y) {
        WallStatus = new Position(start_position);
    }

    public Boolean getFWallCopy() {
        Position Copy = new Position(start_position);
        return Copy.front_wall;
    }

    public Boolean getRWallCopy() {
        Position Copy = new Position(start_position);
        return Copy.right_wall;
    }





}
