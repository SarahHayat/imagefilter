package com.company.imagefilter;
import org.apache.commons.cli.*;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;
import org.ini4j.Ini;
import sun.swing.FilePane;

import javax.swing.text.AbstractDocument;
import java.io.File;
import java.io.IOException;
import java.lang.String;

public class App {
   static Log l = new Log();
    public static void main(String[] args) {


        try {
            parser(args);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void treatment(String file, String output, String filters) {

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
                        Mat image = opencv_imgcodecs.imread(String.valueOf(listFile[i]));
                        BlackAndWhite bw = new BlackAndWhite();
                        try {
                            Mat result = bw.filterGrayscale(image, l);

                            File outputDir = new File("Modifiated");
                            File outputFile = new File(outputDir, "output.jpg");

                            opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), result);
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

    public static void parser(String[] args) throws ParseException, IOException {
        //options
        Options options = new Options();

        options.addOption("f", "filters", true, "filters");
        options.addOption("i", true, "File with picture source");
        options.addOption("o", true, "File with picture modificate");
        options.addOption("file",true,"file ini config");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String fileModificate = "output";
        String file = "toModificate";
        String filters = null;

        if (cmd.hasOption("file")) {
            String filePathIni = cmd.getOptionValue("file");
            if (filePathIni != null) {
                Ini fileIni = new Ini(new File(filePathIni));
                Ini.Section section = fileIni.get("general");
                file = section.get("inputDir");
                fileModificate = section.get("outputDir");
                String log = section.get("logFile");
                if (log == null)
                    log = "log";
                Ini.Section filter = fileIni.get("filters");
                String filterAdd =  filter.get("content");
                System.out.println(filterAdd);
                if (filterAdd != null)
                    filters = String.valueOf(filterAdd);
            }

        }
        else
            System.out.println("file not found");
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

        treatment(file, fileModificate, filters);
    }
}

