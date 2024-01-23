package ca.mcmaster.se2aa4.mazerunner;

public class Position {

    public Boolean front_wall;

    public Boolean right_wall;

    public Position(Integer index) {
        //Find surroundings of start position provided by EntryPoint through ParseMaze
        front_wall = false;
        right_wall = false;
    }
}
