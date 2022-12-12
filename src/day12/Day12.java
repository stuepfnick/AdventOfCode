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

    private char[][] grid;
    private boolean[][] visited;
    private Vector2Int size;

    public static void main(String[] args) {
        var day = new Day12();
        day.solve("example");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/day12/files/" + fileName);

        GridParser parser = new GridParser();
        grid = parser.parseLines(lines);
        size = new Vector2Int(grid[0].length, grid.length);
        System.out.println("size: " + size);

        var result = findPath2(parser.getStartPos(), parser.getEndPos());

        System.out.println("part1: " + result);
    }

    private int findPath2(Vector2Int startPos, Vector2Int endPos) {
        Vector2Int[] dirs = {
                Vector2Int.down(),
                Vector2Int.right(),
                Vector2Int.up(),
                Vector2Int.left()};

        var pos = startPos;
        int steps = 0;

        while (true) {
            char height = pos.equals(startPos) ? 'a' : grid[pos.getY()][pos.getX()];

            List<Vector2Int> possibleMoves = new LinkedList<>();
            for (var dir : dirs) {
                var newPos = pos.add(dir);
                if (newPos.equals(endPos)) {
                    return steps + 1;
                }
                if (isValid(newPos, height)) {
                    possibleMoves.add(newPos);
                }
            }
            steps++;
            pos = possibleMoves.stream()
                    .max(Comparator.comparing(p -> grid[p.getY()][p.getX()]))
                    .orElse(startPos);
        }
    }

    private int findPath(Vector2Int startPos, Vector2Int endPos) {
         Vector2Int[] dirs = {
                Vector2Int.down(),
                Vector2Int.right(),
                Vector2Int.up(),
                Vector2Int.left()};

        Queue<Vector2Int> queue = new LinkedList<>();
        queue.add(startPos);

        boolean[][] visited = new boolean[size.getY()][size.getX()];

        int steps = 0;

        Vector2Int newPos;
        while (!queue.isEmpty()) {
            var pos = queue.remove();
            char height = grid[pos.getY()][pos.getX()];
            height = height == 'S' ? 'a' : height;
            steps++;

            for (int i = 0; i < 4; i++) {
                newPos = pos.add(dirs[i]);

                if (newPos.equals(endPos)) {
                    drawImage(visited, "path");
                    return steps;
                }
                if (isValid(newPos, height, visited)) {
                    queue.add(newPos);
                    visited[newPos.getY()][newPos.getX()] = true;
                }
            }
        }
        return 3;
    }

    private boolean isValid(Vector2Int newPos, char height, boolean[][] visited) {
        if (newPos.getX() >= 0 && newPos.getX() < size.getX() && newPos.getY() >= 0 && newPos.getY() < size.getY()) {
            if (!visited[newPos.getY()][newPos.getX()]) {
                char newHeight = grid[newPos.getY()][newPos.getX()];
                return newHeight == height || newHeight == height + 1;
            }
        }
        return false;
    }

    private boolean isValid(Vector2Int pos, char height) {
        if (pos.getX() >= 0 && pos.getX() < size.getX() && pos.getY() >= 0 && pos.getY() < size.getY()) {
            char newHeight = grid[pos.getY()][pos.getX()];
            return newHeight == height || newHeight == height + 1;
        }
        return false;
    }

    private void drawImage(boolean[][] visited, String fileName) {
        BufferedImage image = new BufferedImage(size.getX(), size.getY(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < visited.length; y++) {
            for (int x = 0; x < visited[0].length; x++) {
                image.setRGB(x, y, visited[y][x] ? Color.BLACK.getRGB() : Color.WHITE.getRGB());
            }
        }
        try {
            ImageIO.write(image, "png", new File( "src/day12/files/" + fileName + "_out.png"));
            System.out.println(fileName + " image file created.");
        } catch (IOException e) {
            System.out.println("Could not write image file.");
        }
    }
}
