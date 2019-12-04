package com.company.imagefilter;
import org.apache.commons.cli.*;
import java.sql.SQLOutput;
import org.apache.commons.cli.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

import java.lang.String;

public class App {
    public static void main(String[] args) {

        try {
            parser(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String filename = "toModificate/index.jpg";
        Mat image = opencv_imgcodecs.imread(filename);
        BlackAndWhite bw = new BlackAndWhite();
        try {
            bw.filterGrayscale(image);
        } catch (JavaCVHelperException e) {
            e.printStackTrace();
            System.out.println("le filtre n'a pas pu etre appliqué");
        }
        System.out.println("L'application du filtre à bien marché");
    }


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
}

