package com.company.imagefilter;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public class Filter {
    void Filter (){
        String filename = "toModificate/index.jpg";
        Mat image = opencv_imgcodecs.imread(filename);
        BlackAndWhite bw = new BlackAndWhite();
        try {
            bw.filterGrayscale(image, App.l);
        } catch (JavaCVHelperException e) {
            e.printStackTrace();
            System.out.println("le filtre n'a pas pu etre appliqué");
        }
        System.out.println("L'application du filtre à bien marché");
    }

}



