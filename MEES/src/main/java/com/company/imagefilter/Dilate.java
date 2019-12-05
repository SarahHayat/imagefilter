package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;
import org.opencv.imgproc.Imgproc;

import static org.bytedeco.opencv.global.opencv_imgproc.dilate;
import static org.bytedeco.opencv.global.opencv_imgproc.getStructuringElement;

public class Dilate extends Filter {
   // public Mat filterDilate(Mat image, Log l) throws JavaCVHelperException {
        @Override
        Mat process(Mat image, int args, String file) throws JavaCVHelperException {

            Mat result = image.clone();
            Mat element = getStructuringElement(Imgproc.MORPH_RECT, new Size(2 * args + 1, 2 * args + 1));
            dilate(image, result, element);
            App.l.Log(" image " + file + "\n" + "filtre : " + Dilate.class.getSimpleName() + "\n");

            if (result == null) {
                throw new JavaCVHelperException("la dilatation ne marche pas");

            }
            return result;
        }

    }
