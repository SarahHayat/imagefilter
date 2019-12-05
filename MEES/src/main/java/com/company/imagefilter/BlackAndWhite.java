package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;

import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import javax.annotation.processing.Filer;
import java.io.FileWriter;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class BlackAndWhite extends Filter {
    //public Mat filterGrayscale() throws JavaCVHelperException {

    //return  filterGrayscale();
    //}


    @Override
    Mat process(Mat image, int args) throws JavaCVHelperException {
        Mat result = new Mat(image.rows(), image.cols(), CvType.CV_8UC3);
        cvtColor(image, result, Imgproc.COLOR_RGB2GRAY);

        App.l.Log(" image " + image + "\n" + "filtre : " + BlackAndWhite.class.getSimpleName() + "\n");


        if (result == null) {
            throw new JavaCVHelperException("le noir et blanc ne marche pas" );

        }return result;
    }
}
