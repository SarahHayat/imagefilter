package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;

import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import java.io.FileWriter;
import java.io.IOException;

import static org.bytedeco.opencv.global.opencv_imgproc.cvtColor;

public class BlackAndWhite {
    public Mat filterGrayscale (Mat image, Log l) throws JavaCVHelperException {
        Mat result = new Mat(image.rows(), image.cols(), CvType.CV_8UC3);
        cvtColor(image, result, Imgproc.COLOR_RGB2GRAY);

         l.Log(" image " + image + "\n" + "filtre : " + BlackAndWhite.class.getSimpleName() + "\n");


        if (result == null) {
            throw new JavaCVHelperException("le noir et blanc ne marche pas" );

        }return result;

    }
}
