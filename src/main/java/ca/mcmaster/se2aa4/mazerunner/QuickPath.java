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
        Position starting_point = cords;
        nodequeue.add(starting_point);
        visited[cords.y][cords.x] = true;
        routes[cords.y][cords.x] = path;
        Integer[] new_x = {0, -1, 0, 1};
        Integer[] new_y = {-1, 0, 1, 0};

        while (!nodequeue.isEmpty()) {
            Position current = nodequeue.peek();
            logger.info("x: {}", current.x);
            logger.info("y: {}", current.y);
            logger.info("heading: {}", current.heading);
            if (current.x == maze[0].length - 1) {
                for (int i = 0; i < maze.length; i++) {
                    if (routes[i][maze[0].length - 1] != null) {
                        path = routes[i][maze[0].length - 1];
                    }
                }
                path += " ";
                factorPath();
                System.out.println(path);
                logger.info("step passed");
                return;
            }
            nodequeue.remove();
            for (int i = 1; i < 5; i++) {
                Integer row = current.x + new_x[i-1];
                Integer column = current.y + new_y[i-1];
                Integer new_heading = i;
                if (validMove(maze, visited, row, column)) {
                    visited[column][row] = true;
                    if (i == current.heading) {
                        routes[column][row] = routes[current.y][current.x] + " F";
                    }
                    else if (i == current.heading + 1 || (i == 1 && current.heading == 4)) {
                        routes[column][row] = routes[current.y][current.x] + " L F";
                    }
                    else if (i == current.heading - 1 || (i == 4 && current.heading == 1)) {
                        routes[column][row] = routes[current.y][current.x] + " R F";
                    }
                    Position addednode = new Position();
                    addednode.initialize(row, column, new_heading);
                    nodequeue.add(addednode);
                }
            }
            if (nodequeue.isEmpty()) {
                if (current.x != maze[0].length) {
                    logger.info("failed. ");
                }
            }
        }
    }

    private Boolean validMove(Integer[][] maze, Boolean[][] visited, Integer x, Integer y) {
        return (x >= 0 && y >= 0 && x < maze[0].length && y < maze.length && maze[y][x] == 0 && !visited[y][x]);
    }

    private void factorPath() {
        String new_path = "";
        Integer count;
        Character move_type;

        if (!(path.equals(""))) {
            for (int i = 0; i < path.length() - 1; i++) {
                count = 0;
                move_type = path.charAt(i);
                if (move_type != ' ') {
                    count += 1;
                    for (int j = i + 1; path.charAt(j) == move_type; j++) {
                        count += 1;
                    }
                }
                if (count > 1) {
                    new_path += count + Character.toString(move_type) + " ";
                }
                else {
                    new_path += move_type + " ";
                }
                i += count;
            }
        }

        path = new_path;
    }
}
