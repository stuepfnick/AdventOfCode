package y2022.day13;

import filehandlers.FileReader;

public class Day13 {

    private final FileReader reader = new FileReader();

    public static void main(String[] args) {
        var day = new Day13();
        day.solve("input");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/y2022/day13/files/" + fileName);
        PacketsParser parser = new PacketsParser();

        var result = parser.parseLines(lines);

        System.out.println("part1: " + result);

        result = parser.parseLines2(lines);

        System.out.println("part2: " + result);
    }
}
