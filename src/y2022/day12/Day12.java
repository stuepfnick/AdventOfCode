package y2022.day12;

import filehandlers.FileReader;
import utilities.Vector2Int;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Queue;
import java.util.*;
import java.util.function.BiFunction;

public class Day12 {

    private final FileReader reader = new FileReader();
    private final GridParser parser = new GridParser();
    private char[][] grid;
    private BufferedImage visited;
    private Vector2Int size;
    private View view;

    public static void main(String[] args) {
        var day = new Day12();
        String inputFile = "input";
        day.prepare(inputFile);
        day.solve1(inputFile);
        day.solve2(inputFile);
    }

    private void waitForStart(int partNumber) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to start part" + partNumber + "!");
        scanner.nextLine();
    }

    private void prepare(String fileName) {
        var lines = reader.read("src/y2022/day12/files/" + fileName);

        grid = parser.parseLines(lines);
        size = new Vector2Int(grid[0].length, grid.length);

        var background = drawHeightMap();
        visited = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_ARGB);
        view = new View(background, visited);
    }

    private void solve1(String fileName) {
        view.render(1d/60d);

        waitForStart(1);

        var result = findPath(parser.getStartPos(), (char) ('z' + 1), (height, newHeight) -> height + 1 >= newHeight);
        writeImage(visited, fileName + "1", false);

        System.out.println("part1: " + result);
    }

    private void solve2(String fileName) {
        waitForStart(2);
        visited = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_ARGB);
        view.setVisited(visited);

        var result = findPath(parser.getEndPos(), 'a', (height, newHeight) -> height - 1 <= newHeight);
        writeImage(visited, fileName + "2", false);

        System.out.println("part2: " + result);
    }

    private int findPath(Vector2Int startPos, char destination, BiFunction<Character, Character, Boolean> check) {
        List<Vector2Int> dirs = new ArrayList<>(List.of(
                Vector2Int.down(),
                Vector2Int.right(),
                Vector2Int.up(),
                Vector2Int.left()));

        var pos = startPos;
        Queue<Vector2Int> queue = new LinkedList<>(List.of(startPos));
        Map<Vector2Int, Vector2Int> parent = new HashMap<>();
        drawPixel(pos, Color.GREEN);

        while (!queue.isEmpty()) {
            pos = queue.remove();

            char height = getHeight(pos);
            if (height == destination) {
                //drawHeightMap();
                drawPixel(pos, Color.RED);
                List<Vector2Int> path = new LinkedList<>();
                while(parent.containsKey(pos)) {
                    path.add(pos);
                    pos = parent.get(pos);
                    if (!pos.equals(startPos))
                        drawPixel(pos, Color.BLUE);
                }
                return path.size();
            }

            for (var dir : dirs) {
                var newPos = pos.add(dir);
                if (isValid(newPos, height, check)) {
                    drawPixel(newPos, Color.WHITE);
                    queue.add(newPos);
                    parent.put(newPos, pos);
                }
            }
        }
        return -1;
    }

    private BufferedImage drawHeightMap() {
        var image = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < size.getY(); y++) {
            for (int x = 0; x < size.getX(); x++) {
                var pos = new Vector2Int(x, y);
                int height = getHeight(pos);
                float gray = (height - 'a' + 1) / 28f;
                //drawPixel(pos, Color.getHSBColor(0f, 0f, gray));
                image.setRGB(pos.getX(), pos.getY(), Color.getHSBColor(0f, 0f, gray).getRGB());
            }
        }
        return image;
    }

    private boolean isValid(Vector2Int pos, char height, BiFunction<Character, Character, Boolean> check) {
        if (pos.getX() >= 0 && pos.getX() < size.getX() && pos.getY() >= 0 && pos.getY() < size.getY()) {
            if (isTransparent(pos)) {
                return check.apply(height, getHeight(pos));
            }
        }
        return false;
    }

    private boolean isTransparent(Vector2Int pos) {
        return visited.getRGB(pos.getX(), pos.getY()) >> 24 == 0x00;
    }

    private char getHeight(Vector2Int pos) {
        return grid[pos.getY()][pos.getX()];
    }

    private void drawPixel(Vector2Int pos, Color color) {
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), 100);
        visited.setRGB(pos.getX(), pos.getY(), color.getRGB());
        if (color.getRed() > 250)
            view.render(1/1000d);
        else
            view.render(1/60d);
    }

    private void writeImage(BufferedImage image, String fileName, boolean doesPrint) {
        try {
            ImageIO.write(image, "png", new File( "src/y2022/day12/files/" + fileName + "_out.png"));
            if (doesPrint) System.out.println(fileName + " image file created.");
        } catch (IOException e) {
            System.out.println("Could not write image file.");
        }
    }
}
