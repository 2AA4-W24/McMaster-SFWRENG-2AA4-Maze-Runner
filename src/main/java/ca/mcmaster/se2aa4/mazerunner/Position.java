package ca.mcmaster.se2aa4.mazerunner;

public class Position {
    Integer x;
    Integer y;
    Integer heading;

    public void initialize(Integer x_in, Integer y_in, Integer heading_in) {
        x = x_in;
        y = y_in;
        heading = heading_in;
    }

    public void move_f() {
        switch(heading) {
            case 1:
                y -= 1;
                break;
            case 2:
                x -= 1;
                break;
            case 3:
                y += 1;
                break;
            case 4:
                x += 1;
                break;
        }
    }

    public void turn_r() {
        switch(heading) {
            case 1:
                heading = 4;
                break;
            case 2:
                heading = 1;
                break;
            case 3:
                heading = 2;
                break;
            case 4:
                heading = 3;
                break;
        }
    }

    public void turn_l() {
        switch(heading) {
            case 1:
                heading = 2;
                break;
            case 2:
                heading = 3;
                break;
            case 3:
                heading = 4;
                break;
            case 4:
                heading = 1;
                break;
        }
    }
}
