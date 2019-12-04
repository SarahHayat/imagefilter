package com.company.imagefilter;


import org.apache.commons.cli.*;
import java.lang.String;

public class App
{
    public static void parser(String[] args) throws ParseException {
        //options
       Options options = new Options();

        options.addOption("f", "filters", true, "filters");
        options.addOption("i", true, "File with picture source");
        options.addOption("o", true, "File with picture modificate");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String fileModificate = "output";
        String file = "toModificate";
        String filters = null;
        if (cmd.hasOption("i")) {
            file = cmd.getOptionValue("i");
            // retrieve the images from the folder
        }
        if (cmd.hasOption("o"))
        {
           fileModificate = cmd.getOptionValue("o");
            // file for put the picture change
        }
        if (cmd.hasOption("f"))
        {
            filters = cmd.getOptionValue("f");
            // filters without parse
        }
        System.out.println("file = " + file + " fileModificate = " + fileModificate + " filters = " + filters);
      // treatment(file, fileModificate, filters);
    }

    public static void main(String[] args) {
        System.out.println("hello");
        try {
            parser(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
