package com.github.FinalDayz;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        System.out.println("Argument 0: path to input png1");
        System.out.println("Argument 1: path to input png2");
        System.out.println("Argument 2 (optional): path to diff output png");

        if(args.length <= 1) {
            System.exit(0);
        }

        String inputFile1 = args[0];
        String inputFile2 = args[1];

        String outputFile = args.length > 2 ? args[2] : null;


        try {
            ImageComparator comparator = new ImageComparator(
                    new File(inputFile1),
                    new File(inputFile2)
            );

            VisualDifferenceComparable visualComparator = new VisualDifferenceComparable(
                    new DistancePixelComparable(),
                    comparator.image1,
                    comparator.image2
            );

            double comparedValue = comparator.compareImages(visualComparator);

            System.out.println("Images are " + comparedValue * 100+" % different");
            if(outputFile != null) {
                ImageIO.write(visualComparator.getDiffImage(), "png",
                        new File(outputFile));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
