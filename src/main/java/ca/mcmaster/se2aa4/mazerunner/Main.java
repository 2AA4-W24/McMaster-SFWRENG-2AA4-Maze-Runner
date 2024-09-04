package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;

public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        MazeFactory factory = new MazeFactory();
        factory.build();
        try {
            //Creating Apache Commons CLI options object to parse command line arguments
            Options options = new Options();
            options.addOption("i", "input",true, "retrieve input maze file");
            options.addOption("p", true, "verify path correctness");
            options.addOption("baseline", true, "check time benchmark");
            options.addOption("method", true, "specify faster path solving algorithm");

            //Creating default command line parser
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String input_file = "";
            String bench = "";
            String algo = "";
            if (cmd.hasOption("input")) {
                input_file = cmd.getOptionValue("input");
            }
            if (cmd.hasOption("baseline")) {
                bench = cmd.getOptionValue("baseline");
            }
            if (cmd.hasOption("method")) {
                algo = cmd.getOptionValue("method");
            }

            if (cmd.hasOption("p")) {
                factory.checkPath(input_file, cmd.getOptionValue("p"));
            }
            else if (cmd.hasOption("baseline")) {
                if (cmd.hasOption("method")) {
                    if (algo.equals("righthand")) {
                        factory.benchmark(input_file, "righthand", bench);
                    }
                    else if (algo.equals("bfs")) {
                        factory.benchmark(input_file, "bfs", bench);
                    }
                }
            }
            else {
                if (cmd.hasOption("method")) {
                    if (algo.equals("righthand")) {
                        factory.getPath(input_file);
                    }
                    else if (algo.equals("bfs")) {
                        factory.getQuickPath(input_file);
                    }
                }
                else {
                    factory.getPath(input_file);
                }
            }
        }
        catch (UnrecognizedOptionException a) {
            String message = "unrecognized flag. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form).";
            message += " -method righthand MAZE_FILE can also be used to specify input file for righthand algorithm, while -method bfs MAZE_FILE is used to enable faster breadth-first-search algorithm";
            System.out.println(message);
        }
        catch (FileNotFoundException b) {
            String message = "maze file/directory does not exist. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form).";
            message += " -method righthand MAZE_FILE can also be used to specify input file for righthand algorithm, while -method bfs MAZE_FILE is used to enable faster breadth-first-search algorithm";
            System.out.println(message);
        }
        catch (MissingArgumentException c) {
            System.out.println(c);
        }
        catch(Exception e) {
            String message = "unexpected error occurred. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form).";
            message += " -method righthand MAZE_FILE can also be used to specify input file for righthand algorithm, while -method bfs MAZE_FILE is used to enable faster breadth-first-search algorithm";
            System.out.println(message);
            logger.error(e);
        }
    }
}
