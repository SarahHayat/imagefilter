package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.core.CvType;
import org.opencv.imgproc.Imgproc;

import static org.bytedeco.opencv.global.opencv_imgproc.*;

public class JavaCVHelper {
    public Mat filterBlur(Mat image) throws JavaCVHelperException  {
        int size = 3;
        Mat result = image.clone();
        GaussianBlur(image, result, new Size(size, size), 0);

        if (result == null) {
            throw new JavaCVHelperException("le flou ne marche pas");

        }return result;
    }

    public Mat filterDilate(Mat image) throws JavaCVHelperException {
        int size = 8;
        Mat result = image.clone();
        Mat element = getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * size + 1, 2 * size + 1));
        dilate(image, result, element);

        if (result == null) {
            throw new JavaCVHelperException("la dilatation ne marche pas");

        }
        return result;
    }

        public Mat filterGrayscale (Mat image) throws JavaCVHelperException {
            Mat result = new Mat(image.rows(), image.cols(), CvType.CV_8UC3);
            cvtColor(image, result, Imgproc.COLOR_RGB2GRAY);

            if (result == null) {
                throw new JavaCVHelperException("le noir et blanc ne marche pas");

        }return result;

    }
}
