package ca.mcmaster.se2aa4.mazerunner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
class QuickPathTest {
    PathConfig pathfinder;
    QuickPath fastpath;
    PathCheck checker;
    ParseMaze scan;
    EntryPoint entry;
    BuildMaze mazebuilder;
    Position cords;
    @BeforeEach
    public void setUp() {
        pathfinder = new PathConfig();
        fastpath = new QuickPath();
        checker = new PathCheck();
        scan = new ParseMaze();
        entry = new EntryPoint();
        mazebuilder = new BuildMaze();
        cords = new Position();
    }

    @Test
    public void smallPathTest() throws IOException {
        fastpath.findPath("./examples/small.maz.txt", entry, mazebuilder, scan, cords);
        assertEquals("F L F R 2F L 6F R 4F R 2F L 2F R 2F L F", fastpath.path);
    }

    @Test
    public void mediumPathTest() throws IOException {
        fastpath.findPath("./examples/medium.maz.txt", entry, mazebuilder, scan, cords);
        assertEquals("F L 2F R 2F L 18F L 2F R 2F R 8F R 2F L 6F R 10F L 4F R 10F L 10F R 4F L F", fastpath.path);
    }

    @Test
    public void hugePathTest() throws IOException {
        fastpath.findPath("./examples/huge.maz.txt", entry, mazebuilder, scan, cords);
        String correct = "F L 7F R 2F L 4F R 4F L 2F R 6F R 2F L 6F R 4F L 2F R 2F L 10F L 2F R 2F R 2F L 4F R 10F L 2F R 4F L 6F R 2F L 2F R 4F L 2F R 6F L 2F R 2F L 2F R 2F L 2F R 4F L 4F R 2F L 4F R 4F R 2F L 4F L 4F R 2F L 6F R 2F L 2F R 6F L 4F R 2F L 4F R 2F L 2F R 2F L 2F R 4F L 6F R 4F L 4F R 6F L 4F R F L F";
        assertEquals(correct, fastpath.path);
    }
}