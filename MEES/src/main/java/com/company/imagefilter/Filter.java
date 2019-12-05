package com.company.imagefilter;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public abstract  class Filter {
    /**
     *  A VOIR
     * @param image Processed Image
     * @param args CommandLine Argument
     * @param file Directory output
     * @throws JavaCVHelperException
     */
    abstract Mat process (Mat image, String file) throws JavaCVHelperException;

    }





