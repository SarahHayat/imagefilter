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
import java.util.ArrayList;
import java.util.List;


public class App {

    static Log l = new Log();
   static List<Filter> filterList = new ArrayList<>();

    /**
     *
      * @param args Program argument
     */
   public static void main(String[] args) {
        try {
            parser(args);
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set filter of commandLine to each picture in define directory
     * @param file Directory of pictures/images
     * @param output Directory of output filtered images
     * @param filters List of filters to applicate
     */

    public static void treatment(String file, String output, String filters) {

        File directory = new File(file);
        File outputDir = new File(output);
        outputDir.mkdirs();

        int argc = 5;
        if (filters != null) {
            String filterArg = filters;
            String[] split = filterArg.split("\\|");

            for (String s : split) {

                String[] str = s.split(":" , 2);
                for (int j = 0; j < str.length; j++) {
                    switch (str[j]) {
                        case "blur":
                            if (j + 1 < str.length)
                                argc = Integer.parseInt(str[j + 1]);
                            filterList.add(new Blur(argc));
                            break;
                        case "grayscale":
                            filterList.add(new BlackAndWhite(argc));
                            break;
                        case "dilate":
                            if (j + 1 < str.length)
                                argc = Integer.parseInt(str[j + 1]);
                            filterList.add(new Dilate(argc));
                            break;
                    }
                }
            }

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
                       // BlackAndWhite bw = new BlackAndWhite();


                            try {
                                for (Filter f : filterList) {
                                    image = f. process(image, String.valueOf(listFile[i]));
                                }

                                String nameFile = listFile[i].getName();//.split(splitFile);

                                File outputFile = new File(outputDir, nameFile);

                                opencv_imgcodecs.imwrite(outputFile.getAbsolutePath(), image);
                            } catch (JavaCVHelperException e) {
                                e.printStackTrace();
                                System.out.println("The application of filter work");
                            }
                        }
                        System.out.println("The application of filter doesn't work");
                    }
                }
            }
        }
    }

    /**
     * Display each commands
     */
    public static void help()
    {
        System.out.println("usage : imagefilter");
        System.out.println("-f, --filters <list of filters>");
        System.out.println("-i <directory> File source");
        System.out.println("-o <directory> File for save picture");
        System.out.println("-h, --help");
        System.out.println("-file, --config-file <directory.ini> File config");
        System.out.println("-list, --list-filters List of filters");
        System.exit(0);
    }

    /**
     * Parse a .ini file and the CommandLine
     * @param args Program argument
     * @throws ParseException /
     * @throws IOException /
     */
    public static void parser(String[] args) throws ParseException, IOException {
        //options
        Options options = new Options();

        options.addOption("f", "filters", true, "filters");
        options.addOption("i", true, "File with picture source");
        options.addOption("o", true, "File with picture modificate");
        options.addOption("h", "help", false, "help config");
        options.addOption("file","config-file",true,"file ini config");
        options.addOption("list", "list-filters",false,"list of filters");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String fileModificate = "output";
        String file = "toModificate";
        String filters = null;

        if (cmd.hasOption("h"))
            help();
        if (cmd.hasOption("list"))
        {
            System.out.println("blackAndWhite");
            System.out.println("blur:<param> | blur");
            System.out.println("dilate:<param> | dilate");
            System.exit(0);
        }

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
        treatment(file, fileModificate, filters);
    }
}

