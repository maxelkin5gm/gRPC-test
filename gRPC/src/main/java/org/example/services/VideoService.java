package org.example.services;

import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_videoio.VideoCapture;
import org.example.entities.Image;

public class VideoService extends Thread {
    private static volatile Image image;

    VideoCapture capture = new VideoCapture(0);
    Mat mat = new Mat();

    @Override
    public void run() {

        while (capture.grab()) {
            capture.read(mat);
            byte[] newBuffer = new byte[mat.channels() * mat.cols() * mat.rows()];
            mat.data().get(newBuffer);
            setImage(newBuffer, mat.rows(), mat.cols(), mat.type());
        }

        capture.close();
        mat.close();
    }

    static synchronized void setImage(byte[] buffer, int rows, int cols, int type) {
        image = new Image(buffer, rows, cols, type);
    }

    public static synchronized Image getImage() {
        return image;
    }

}
