package com.github.FinalDayz;

public interface PixelComparable {
    public double compare(int rgb1, int rgb2, int x, int y);

    public double compare(int rgb1, int rgb2, boolean outOfRange, int x, int y);

    public double compareValues(Double[] totalValues);

}
