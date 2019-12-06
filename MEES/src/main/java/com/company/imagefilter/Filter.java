package com.company.imagefilter;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public abstract  class Filter {
    /**
     *  mother class filter
     * @param image Processed Image
     * @param file Directory output
     * @throws JavaCVHelperException
     */
    abstract Mat process (Mat image, String file) throws JavaCVHelperException;

    }





