package com.github.FinalDayz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageComparator {

    public BufferedImage image1, image2;

    public ImageComparator(File image1, File image2) throws IOException {
        this.image1 = ImageIO.read(image1);
        this.image2 = ImageIO.read(image2);
    }



    public double compareImages(PixelComparable comparable) {
        ArrayList<Double> comparedValues = new ArrayList<>();

        int maxWidth = Math.min(image1.getWidth(), image2.getWidth());
        int maxHeight = Math.min(image1.getHeight(), image2.getHeight());

        for(int x = 0; x < maxWidth; x++) {

            for(int y = 0; y < maxHeight; y++) {
                int rgb1 = 0, rgb2 = 0;
                boolean image1OutOfBounds = x > image1.getWidth() || y > image1.getHeight();
                boolean image2OutOfBounds = x > image2.getWidth() || y > image2.getHeight();

                if(!image1OutOfBounds)
                    rgb1 = image1.getRGB(x, y);
                if(!image2OutOfBounds)
                    rgb2 = image2.getRGB(x, y);

                comparedValues.add(comparable.compare(rgb1, rgb2, image1OutOfBounds | image2OutOfBounds, x, y));

            }
        }

        return comparable.compareValues(comparedValues.toArray(new Double[comparedValues.size()]));
    }
}
