package ca.mcmaster.se2aa4.mazerunner;

public class WallRecord {

    final Integer front_wall;

    final Integer right_wall;

    final Integer right_wall_f;

    public WallRecord(Integer f, Integer r, Integer r_f) {
        front_wall = f;
        right_wall = r;
        right_wall_f = r_f;
    }
}
