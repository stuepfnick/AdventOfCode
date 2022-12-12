package day8;

import filehandlers.FileReader;

public class Day8 {

    FileReader reader = new FileReader();
    byte[][] grid;

    public static void main(String[] args) {
        Day8 day = new Day8();
        day.solve("example");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/day8/files/" + fileName);

        GridParser parser = new GridParser();
        grid = parser.parseLines(lines);

        VisibleTreesCounter counter = new VisibleTreesCounter(grid);
        System.out.println("part1: " + counter.checkVisibility());

        ScenicScoreCalculator calculator = new ScenicScoreCalculator(grid);
        System.out.println("part2: " + calculator.getHighestScore());
    }

}
