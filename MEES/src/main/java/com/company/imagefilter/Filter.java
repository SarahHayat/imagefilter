package com.company.imagefilter;

import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.opencv_core.Mat;

public abstract  class Filter {
   abstract Mat process (Mat image, int args, String file) throws JavaCVHelperException;

    }





