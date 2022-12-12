package day12;

import filehandlers.FileReader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Day12 {

    private final FileReader reader = new FileReader();
    private final GridParser parser = new GridParser();

    private char[][] grid;
    private BufferedImage visited;
    private Vector2Int size;

    public static void main(String[] args) {
        var day = new Day12();
        String inputFile = "input";
        day.prepare(inputFile);
        day.solve1(inputFile);
    }

    private void prepare(String fileName) {
        var lines = reader.read("src/day12/files/" + fileName);

        grid = parser.parseLines(lines);
        size = new Vector2Int(grid[0].length, grid.length);
    }

    private void solve1(String fileName) {
        visited = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_RGB);

        var result = findPath(parser.getStartPos(), parser.getEndPos());
        writeImage(visited, fileName + "1");

        System.out.println("part1: " + result);
    }

    private int findPath(Vector2Int startPos, Vector2Int endPos) {
        List<Vector2Int> dirs = new ArrayList<>(List.of(
                Vector2Int.down(),
                Vector2Int.right(),
                Vector2Int.up(),
                Vector2Int.left()));

        var pos = startPos;
        Queue<Vector2Int> queue = new LinkedList<>(List.of(startPos));
        Map<Vector2Int, Vector2Int> parent = new HashMap<>();
        visited.setRGB(pos.getX(), pos.getY(), Color.RED.getRGB());

        while (!queue.isEmpty()) {
            pos = queue.remove();
            char height = pos.equals(startPos) ? 'a' : grid[pos.getY()][pos.getX()];
            if (pos.equals(endPos)) {
                visited.setRGB(pos.getX(), pos.getY(), Color.GREEN.getRGB());
                List<Vector2Int> path = new LinkedList<>();
                System.out.println(pos);
                while(parent.containsKey(pos)) {
                    path.add(pos);
                    pos = parent.get(pos);
                    if (!pos.equals(startPos))
                        visited.setRGB(pos.getX(), pos.getY(), Color.BLUE.getRGB());
                }
                return path.size();
            }

            for (var dir : dirs) {
                var newPos = pos.add(dir);
                if (isValid(newPos, height)) {
                    visited.setRGB(newPos.getX(), newPos.getY(), Color.WHITE.getRGB());
                    queue.add(newPos);
                    parent.put(newPos, pos);
                }
            }
        }
        return -1;
    }

    private int findPath2(Vector2Int startPos, Vector2Int endPos) {
        List<Vector2Int> dirs = new ArrayList<>(List.of(
                Vector2Int.down(),
                Vector2Int.right(),
                Vector2Int.up(),
                Vector2Int.left()));

        var pos = startPos;
        Queue<Vector2Int> queue = new LinkedList<>(List.of(startPos));
        Map<Vector2Int, Vector2Int> parent = new HashMap<>();
        visited.setRGB(pos.getX(), pos.getY(), Color.RED.getRGB());

        while (!queue.isEmpty()) {
            pos = queue.remove();
            char height = pos.equals(startPos) ? 'a' : grid[pos.getY()][pos.getX()];
            if (pos.equals(endPos)) {
                visited.setRGB(pos.getX(), pos.getY(), Color.GREEN.getRGB());
                List<Vector2Int> path = new LinkedList<>();
                System.out.println(pos);
                while(parent.containsKey(pos)) {
                    path.add(pos);
                    pos = parent.get(pos);
                    if (!pos.equals(startPos))
                        visited.setRGB(pos.getX(), pos.getY(), Color.BLUE.getRGB());
                }
                return path.size();
            }

            for (var dir : dirs) {
                var newPos = pos.add(dir);
                if (isValid(newPos, height)) {
                    visited.setRGB(newPos.getX(), newPos.getY(), Color.WHITE.getRGB());
                    queue.add(newPos);
                    parent.put(newPos, pos);
                }
            }
        }
        return -1;
    }

    private boolean isValid(Vector2Int pos, char height) {
        if (pos.getX() >= 0 && pos.getX() < size.getX() && pos.getY() >= 0 && pos.getY() < size.getY()) {
            if (visited.getRGB(pos.getX(), pos.getY()) == Color.BLACK.getRGB()) {
                char newHeight = grid[pos.getY()][pos.getX()];
                return newHeight <= height + 1;
            }
        }
        return false;
    }

    private void writeImage(BufferedImage image, String fileName) {
        try {
            ImageIO.write(image, "png", new File( "src/day12/files/" + fileName + "_out.png"));
            //System.out.println(fileName + " image file created.");
        } catch (IOException e) {
            System.out.println("Could not write image file.");
        }
    }
}
