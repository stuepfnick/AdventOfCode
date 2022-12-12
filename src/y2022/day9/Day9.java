package y2022.day9;

import filehandlers.FileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Day9 {

    FileReader reader = new FileReader();

    public static void main(String[] args) {
        Day9 day = new Day9();
        day.solve("input");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/y2022/day9/files/" + fileName);

        var parser = new KnotsParser(1);
        int result = parser.parseLines(lines);

        System.out.println("part1: " + result);

        var parser2 = new KnotsParserWithImage(9);
        result = parser2.parseLines(lines);

        System.out.println("part2: " + result);

        writeImage(parser2.getImage(), "src/y2022/day9/files/" + fileName);
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
