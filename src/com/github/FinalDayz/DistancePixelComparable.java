package com.github.FinalDayz;

public class DistancePixelComparable implements PixelComparable{
    @Override
    public double compare(int rgb1, int rgb2, int x, int y) {
        double finalValue = 0;

        int red1 = (rgb1 >> 16) & 0xFF, green1 = (rgb1 >> 8) & 0xFF, blue1 = (rgb1) & 0xFF;
        int red2 = (rgb2 >> 16) & 0xFF, green2 = (rgb2 >> 8) & 0xFF, blue2 = (rgb2) & 0xFF;

        //each pixel can make a max difference of 0.5
        finalValue += Math.pow(red1 - red2, 2);
        finalValue += Math.pow(green1 - green2, 2);
        finalValue += Math.pow(blue1 - blue2, 2);

        return Math.sqrt(finalValue) / Math.sqrt(Math.pow(255, 2) * 3);
    }

    @Override
    public double compare(int rgb1, int rgb2, boolean outOfRange, int x, int y) {
        if(outOfRange)
            return 1;
        else
            return compare(rgb1, rgb2, x, y);
    }

    @Override
    public double compareValues(Double[] totalValues) {
        double total = 0;
        for(double value : totalValues) {
            total += value;
        }

        return total / totalValues.length;
    }
}
