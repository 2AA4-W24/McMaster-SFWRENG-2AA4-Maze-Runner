package ca.mcmaster.se2aa4.mazerunner;

public class ParseMaze {

    private Integer right_wall;

    private Integer right_wall_f; //status of right wall in position one unit forward

    private Integer front_wall;

    public void findPos(Integer x, Integer y, Integer heading, BuildMaze maze_finder) {
        Integer[][] maze = maze_finder.getMaze();

        switch (heading) {
            case 1:
                if (maze[y-1][x] == 0) {
                    front_wall = 0;
                }
                else {
                    front_wall = 1;
                }

                if (maze[y][x+1] == 0) {
                    right_wall = 0;
                }
                else {
                    right_wall = 1;
                }

                if (maze[y-1][x+1] == 0) {
                    right_wall_f = 0;
                }
                else {
                    right_wall_f = 1;
                }
                break;
            case 2:
                if (maze[y][x-1] == 0) {
                    front_wall = 0;
                }
                else {
                    front_wall = 1;
                }

                if (maze[y-1][x] == 0) {
                    right_wall = 0;
                }
                else {
                    right_wall = 1;
                }

                if (maze[y-1][x-1] == 0) {
                    right_wall_f = 0;
                }
                else {
                    right_wall_f = 1;
                }
                break;
            case 3:
                if (maze[y+1][x] == 0) {
                    front_wall = 0;
                }
                else {
                    front_wall = 1;
                }

                if (maze[y][x-1] == 0) {
                    right_wall = 0;
                }
                else {
                    right_wall = 1;
                }

                if (maze[y+1][x-1] == 0) {
                    right_wall_f = 0;
                }
                else {
                    right_wall_f = 1;
                }
                break;
            case 4:
                if (maze[y][x+1] == 0) {
                    front_wall = 0;
                }
                else {
                    front_wall = 1;
                }

                if (maze[y+1][x] == 0) {
                    right_wall = 0;
                }
                else {
                    right_wall = 1;
                }

                if (maze[y+1][x+1] == 0) {
                    right_wall_f = 0;
                }
                else {
                    right_wall_f = 1;
                }
                break;
        }
    }

    public Integer getRightWallStatus() {
        Integer copy = right_wall;
        return copy;
    }

    public Integer getFrontWallStatus() {
        Integer copy = front_wall;
        return copy;
    }

    public Integer getAheadWallStatus() {
        Integer copy = right_wall_f;
        return copy;
    }
}
