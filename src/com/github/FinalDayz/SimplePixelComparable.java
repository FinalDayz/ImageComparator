package com.github.FinalDayz;

public class SimplePixelComparable implements PixelComparable {

    @Override
    public double compare(int rgb1, int rgb2, int x, int y) {
        double finalValue = 0;

        int red1 = (rgb1 >> 16) & 0xFF, green1 = (rgb1 >> 8) & 0xFF, blue1 = (rgb1) & 0xFF;
        int red2 = (rgb2 >> 16) & 0xFF, green2 = (rgb2 >> 8) & 0xFF, blue2 = (rgb2) & 0xFF;

        //each pixel can make a max difference of 0.5
        finalValue += Math.abs(red1 - red2)     / 255.0 / 0.5;
        finalValue += Math.abs(green1 - green2) / 255.0 / 0.5;
        finalValue += Math.abs(blue1 - blue2)   / 255.0 / 0.5;

        return Math.min(finalValue, 1);
    }

    @Override
    public double compare(int rgb1, int rgb2, boolean outOfRange, int x, int y) {
        if(outOfRange)
            return 1;
        else
            return compare(rgb1, rgb2, x, y);
    }

    /**
     * Takes the average of all values
     */
    @Override
    public double compareValues(Double[] totalValues) {
        double total = 0;
        for(double value : totalValues) {
            total += value;
        }

        return total / totalValues.length;
    }

}
