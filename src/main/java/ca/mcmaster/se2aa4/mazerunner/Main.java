package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {

    private static final Logger logger = LogManager.getLogger();


    public static void main(String[] args) {
        PathFinder navigator = new PathFinder();
        try {
            //Creating Apache Commons cli options object to parse command line arguments
            Options options = new Options();
            options.addOption("i", "input",true, "retrieve input maze file");

            //Creating default command line parser
            CommandLineParser parser = new DefaultParser();
            CommandLine cmd = parser.parse(options, args);

            String input_file = "";
            if (cmd.hasOption("input")) {
                input_file = cmd.getOptionValue("input");
            }

            navigator.findPath(input_file);

            System.out.println(navigator.path);

        } catch(Exception e) {
            logger.error("/!\\ An error has occured /!\\");
            logger.error(e);
        }
    }
}
