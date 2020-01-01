package com.github.FinalDayz;

import java.awt.*;
import java.awt.image.BufferedImage;

public class VisualDifferenceComparable implements PixelComparable {

    private PixelComparable comparable;
    private BufferedImage imageDiff;

    public VisualDifferenceComparable(PixelComparable comparable, BufferedImage img1, BufferedImage img2) {
        imageDiff = new BufferedImage(
                Math.max(img1.getWidth(), img2.getWidth()),
                Math.max(img1.getHeight(), img2.getHeight()),
                img1.getType()
        );


        this.comparable = comparable;
    }

    @Override
    public double compare(int rgb1, int rgb2, int x, int y) {
        double comparedValue = comparable.compare(rgb1, rgb2, x, y);
        setDiffImg(comparedValue, x, y);
        return comparedValue;
    }

    public BufferedImage getDiffImage() {
        return this.imageDiff;
    }

    private void setDiffImg(double comparedValue, int x, int y) {
        int diffimgValue = (int) Math.round(comparedValue * 255);
        diffimgValue = Math.min(diffimgValue, 255);
        diffimgValue = Math.max(diffimgValue, 0);
        diffimgValue = diffimgValue;
      //  System.out.println(diffimgValue << 8);
        Color diffColor = new Color(diffimgValue, 0, diffimgValue);
        imageDiff.setRGB(x, y, diffColor.getRGB());
    }

    @Override
    public double compare(int rgb1, int rgb2, boolean outOfRange, int x, int y) {
        double comparedValue = comparable.compare(rgb1, rgb2, outOfRange, x, y);
        setDiffImg(comparedValue, x, y);
        return comparedValue;
    }

    @Override
    public double compareValues(Double[] totalValues) {
        return comparable.compareValues(totalValues);
    }
}
