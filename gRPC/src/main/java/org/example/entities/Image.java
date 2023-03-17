package org.example.entities;

public class Image {
    byte[] buffer;
    int rows;
    int cols;
    int type;

    public byte[] getBuffer() {
        return buffer;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getType() {
        return type;
    }

    public Image(byte[] buffer, int rows, int cols, int type) {
        this.buffer = buffer;
        this.rows = rows;
        this.cols = cols;
        this.type = type;
    }
}
