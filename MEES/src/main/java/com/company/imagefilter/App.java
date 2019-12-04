package com.company.imagefilter;


import org.apache.commons.cli.*;
import org.bytedeco.opencv.opencv_core.Mat;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;
import static org.bytedeco.opencv.helper.opencv_imgcodecs.cvLoadImage;

public class App
{
    public static void parser(String[] args) throws ParseException {
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
     //   treatment(file, fileModificate, filters);
    }

    public static void main(String[] args) throws ParseException {
        System.out.println("hello");
       // parser(args);
        String filename = "toModificate/index.jpg";

        Mat image = imread(filename);
        BlackAndWhite bw = new BlackAndWhite();
        try {
            bw.filterGrayscale(image);
        } catch (JavaCVHelperException e) {
            e.printStackTrace();
        }
        System.out.println("modified image : " + image + "\n");
        System.out.println( "Applicates : " + bw.getClass().getSimpleName()+ " filter \n");

    }
}
