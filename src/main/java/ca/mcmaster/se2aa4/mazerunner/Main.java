package ca.mcmaster.se2aa4.mazerunner;

import org.apache.commons.cli.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;

public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        PathConfig navigator = new PathConfig();
        try {
            //Creating Apache Commons cli options objects to parse command line arguments
            Options options = new Options();
            options.addOption("i", "input",true, "retrieve input maze file");
            options.addOption("p", true, "verify path correctness");

            //Creating default command line parser
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String input_file = "";
            if (cmd.hasOption("input")) {
                input_file = cmd.getOptionValue("input");
            }

            if (cmd.hasOption("p")) {
                navigator.check(input_file, cmd.getOptionValue("p"));
            }
            else {
                navigator.findPath(input_file);
            }

        }
        catch (UnrecognizedOptionException a) {
            System.out.println("unrecognized flag. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form");
        }
        catch (FileNotFoundException b) {
            System.out.println("maze file/directory does not exist. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form");
        }
        catch (MissingArgumentException c) {
            System.out.println(c);
        }
        catch(Exception e) {
            System.out.println("unexpected error occurred. usage: -i MAZE_FILE (specify maze input file) -p PATH (optional flag - check path correctness in factored and non-factored form");
            logger.error(e);
        }
    }
}
