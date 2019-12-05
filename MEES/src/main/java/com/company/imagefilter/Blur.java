package com.company.imagefilter;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

import static org.bytedeco.opencv.global.opencv_imgproc.GaussianBlur;

public class Blur extends Filter  {
    private int argc;
    public Blur(int argc) {
        this.argc = argc;
    }

    /**
     * Applicate a filter blur
     * @param image Images Processed
     * @param args Program Argument
     * @param file Output Directory
     * @return result = Filtered Image
     * @throws JavaCVHelperException
     */
    @Override
    Mat process(Mat image, String file) throws JavaCVHelperException {
            Mat result = image.clone();
            GaussianBlur(image, result, new Size(this.argc, this.argc), 0);
        System.out.println(" image :" + file + "\n" + "filter : " + Blur.class.getSimpleName() + "\n");
            App.l.log(" image " + file + "\n" + "filter : " + Blur.class.getSimpleName() + "\n");

            if (result == null) {
                throw new JavaCVHelperException("The blur doesn't work");

            }return result;
        }

}

