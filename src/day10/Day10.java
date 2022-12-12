package day10;

import filehandlers.FileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Day10 {
    FileReader reader = new FileReader();

    public static void main(String[] args) {
        var day = new Day10();
        day.solve("example");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/day10/files/" + fileName);

        var parser = new CRTParser();
        parser.parseLines(lines);

        var result = parser.solvePart1();

        System.out.println("part1: " + result);

        var results = parser.solvePart2();

        System.out.println("part2: ");
        results.forEach(System.out::println);

        var image = parser.solvePart2AsImage();
        writeImage(image, "src/day10/files/" + fileName);
    }

    private void writeImage(BufferedImage image, String fileName) {
        try {
            ImageIO.write(image, "png", new File( fileName + "_out.png"));
            System.out.println(fileName + " image file created.");
        } catch (IOException e) {
            System.out.println("Could not write image file.");
        }
    }
}
