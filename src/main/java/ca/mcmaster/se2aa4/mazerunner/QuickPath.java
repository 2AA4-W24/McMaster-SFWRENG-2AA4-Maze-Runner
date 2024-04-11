package ca.mcmaster.se2aa4.mazerunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QuickPath implements PathFinder {
    private static final Logger logger = LogManager.getLogger();

    String path;

    @Override
    public void findPath(String filename, EntryPoint start, BuildMaze maze_finder, ParseMaze scan, Position cords) throws IOException {
        start.findEntry(filename);
        maze_finder.saveMaze(filename);

        Integer[][] maze = maze_finder.getMaze();

        path = "";
        cords.initialize(0, start.getWestIndex(), 4);
        Boolean[][] visited = new Boolean[maze.length][maze[0].length];
        for (Boolean[] row : visited) {
            Arrays.fill(row, false);
        }
        String[][] routes = new String[maze.length][maze[0].length];
        Queue<Position> nodequeue = new LinkedList<>();
        nodequeue.add(cords);
        visited[cords.y][cords.x] = true;
        routes[cords.y][cords.x] = path;
        Integer[] new_x = {0, -1, 0, 1};
        Integer[] new_y = {-1, 0, 1, 0};

        while (!nodequeue.isEmpty()) {
            Position current = nodequeue.peek();
            if (current.x == maze[0].length - 1) {
                for (int i = 0; i < maze.length; i++) {
                    if (routes[i][maze[0].length - 1] != null) {
                        path = routes[i][maze[0].length - 1];
                    }
                }
                path += " ";
                path = PathOutput.factorPath(path);
                path = path.trim();
                System.out.println(path);
                return;
            }
            nodequeue.remove();
            for (int i = 1; i < 5; i++) {
                Integer row = current.x + new_x[i-1];
                Integer column = current.y + new_y[i-1];
                Integer new_heading = i;
                if (validMove(maze, visited, row, column)) {
                    visited[column][row] = true;
                    String previous = routes[current.y][current.x];
                    if (i == current.heading) {
                        if (previous != null && !previous.isEmpty()) {
                            if (previous.charAt(previous.length() - 1) == 'F') {
                                routes[column][row] = previous + "F";
                            } else {
                                routes[column][row] = previous + " F";
                            }
                        }
                        else {
                            routes[column][row] = previous + "F";
                        }
                    }
                    else if (i == current.heading + 1 || (i == 1 && current.heading == 4)) {
                        if (previous != null && !previous.isEmpty()) {
                            if (previous.charAt(previous.length() - 1) == 'L') {
                                routes[column][row] = previous + "L F";
                            } else {
                                routes[column][row] = previous + " L F";
                            }
                        }
                        else {
                            routes[column][row] = previous + "L F";
                        }
                    }
                    else if (i == current.heading - 1 || (i == 4 && current.heading == 1)) {
                        if (previous != null && !previous.isEmpty()) {
                            if (previous.charAt(previous.length() - 1) == 'R') {
                                routes[column][row] = previous + "R F";
                            } else {
                                routes[column][row] = previous + " R F";
                            }
                        }
                        else {
                            routes[column][row] = previous + "R F";
                        }
                    }
                    Position addednode = new Position();
                    addednode.initialize(row, column, new_heading);
                    nodequeue.add(addednode);
                }
            }
        }
    }

    private Boolean validMove(Integer[][] maze, Boolean[][] visited, Integer x, Integer y) {
        return (x >= 0 && y >= 0 && x < maze[0].length && y < maze.length && maze[y][x] == 0 && !visited[y][x]);
    }
}
