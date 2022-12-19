package y2022.day14;

import filehandlers.FileReader;
import utilities.Vector2Int;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day14 {

    private final FileReader reader = new FileReader();
    public static void main(String[] args) {
        var day = new Day14();
        day.solve1("input");
    }

    public void solve1(String fileName) {
        var lines = reader.read("src/y2022/day14/files/" + fileName);
        var caveScanner = new CaveScanner();
        caveScanner.parseLines(lines);

        caveScanner.drawCave1();
        View view = new View(caveScanner.getCave(), 4, 0);
        view.render(null, 1d / 60d);

        waitForStart(1);

        int result = -1;
        for (boolean doesFindRest = true; doesFindRest; result++) {
            doesFindRest = caveScanner.pourSandGrain(new Vector2Int(500, 0), view);
        }

        System.out.println("part1: " + result);
        writeImage(caveScanner.getCave(), fileName + "-cave1", true);

        int left = 20 + caveScanner.getCave().getWidth() * 4;
        caveScanner.drawCave2();
        var view2 = new View(caveScanner.getCave(), 4, left);
        view2.render(null, 1d / 60d);

        waitForStart(2);

        result = -1;
        for (boolean doesFindRest = true; doesFindRest; result++) {
            doesFindRest = caveScanner.pourSandGrain(new Vector2Int(500, 0), view2);
        }
        System.out.println("part2: " + result);
        writeImage(caveScanner.getCave(), fileName + "-cave2", true);
    }

    private void waitForStart(int partNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to start part" + partNumber + "!");
        scanner.nextLine();
    }

    private void writeImage(BufferedImage image, String fileName, boolean doesPrint) {
        try {
            ImageIO.write(image, "png", new File( "src/y2022/day14/files/" + fileName + "_out.png"));
            if (doesPrint) System.out.println(fileName + " image file created.");
        } catch (IOException e) {
            System.out.println("Could not write image file.");
        }
    }
}
