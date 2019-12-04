package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class Blur {
    public Mat filterBlur(Mat image, Log l) throws JavaCVHelperException  {
        int size = 3;
        Mat result = image.clone();
        GaussianBlur(image, result, new Size(size, size), 0);

        l.Log(" image " + image + "\n" + "filtre : " + Blur.class.getSimpleName() + "\n");

        if (result == null) {
            throw new JavaCVHelperException("le flou ne marche pas");

        }return result;
    }
}
