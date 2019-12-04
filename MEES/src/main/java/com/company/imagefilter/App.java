package com.company.imagefilter;
import org.apache.commons.cli.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import java.io.File;

import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class App {
   static Log l = new Log();
   static List<Filter> filterList = new ArrayList<>();
    public static void main(String[] args) {

        filterList.add(new Blur());
        filterList.add(new BlackAndWhite());


        try {
            parser(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    public static void treatment(String file, String output, String filters, Mat image) {

        File directory = new File(file);
        if (directory.isDirectory())
        {
            File[] listFile = directory.listFiles();
            if (listFile != null)
            {
                for (int i = 0; i < listFile.length; i++)
                {
                    System.out.println(String.valueOf(listFile[i]));
                    if (String.valueOf(listFile[i]).contains(".png") == true ||
                            String.valueOf(listFile[i]).contains(".jpg") == true)
                    {
                        image = opencv_imgcodecs.imread(String.valueOf(listFile[i]));
                       // BlackAndWhite bw = new BlackAndWhite();
                        String filterArg = "blur|grayscale";
                        String[] split = filterArg.split("\\|");

                        for ( String s : split){
                            switch(s) {
                                case "blur" :
                                    filterList.add(new Blur());
                                    break;
                                case "grayscale" :
                                    filterList.add(new BlackAndWhite());
                                    break;
                                case "dilate" :
                                    filterList.add(new Dilate());
                                    break;
                            }
                        }

                        try {

                            for(Filter f : filterList) {
                                image = f.process(image);
                            }
                           // Mat result = bw.filterGrayscale(image, l);

                            File outputDir = new File("Modifiated");
                            File outputFile = new File(outputDir, "output.jpg");

                            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
                        } catch (JavaCVHelperException e) {
                            e.printStackTrace();
                            System.out.println("le filtre n'a pas pu etre appliqué");
                        }
                        System.out.println("L'application du filtre à bien marché");
                    }
                }
            }
        }
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
        if (cmd.hasOption("o")) {
            fileModificate = cmd.getOptionValue("o");
            // file for put the picture change
        }
        if (cmd.hasOption("f")) {
            filters = cmd.getOptionValue("f");
            // filters without parse
        }
        System.out.println("file = " + file + " fileModificate = " + fileModificate + " filters = " + filters);

        treatment(file, fileModificate, filters, );
    }
}

