package com.company.imagefilter;


import org.apache.commons.cli.*;

public class App
{
    /*public static void parser(String[] args) throws ParseException {
        //options
        Options options = new Options();

        options.addOption("--filters", true, "Filter to applicate");
        options.addOption("-i", true, "File with picture source");
        options.addOption("-o", true, "File with picture modificate");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String fileModificate = "output";
        String file = "toModificate";
        String filters = null;
        if (cmd.hasOption("-i")) {
            file = cmd.getOptionValue("-i");
            // retrieve the images from the folder
        }
        if (cmd.hasOption("-o"))
        {
           fileModificate = cmd.getOptionValue("-o");
            // file for put the picture change
        }
        if (cmd.hasOption("--filters"))
        {
            filters = cmd.getOptionValue("--filters");
            // filters without parse
        }
        treatment(file, fileModificate, filters);
    }*/

    public static void main(String[] args) throws ParseException {
        System.out.println("hello");
       // parser(args);


    }
}
