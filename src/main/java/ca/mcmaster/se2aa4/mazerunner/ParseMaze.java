package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
public class ParseMaze {

    public Integer right_wall;

    public Integer right_wall_f; //status of right wall in position one unit forward

    public Integer front_wall;

    public void findPos(Integer x, Integer y, Integer heading, SaveMaze maze_finder) {
        Integer[][] maze = maze_finder.maze_array;

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
}
