package day3;

import filehandlers.FileReader;

import java.util.List;
import java.util.Optional;

public class Day3 {
    public static void main(String[] args) {

        FileReader reader = new FileReader();
        var lines = reader.read("src/day3/files/input");

        int sum = 0;
        for (String line : lines) {
            var oC = findInBothHalfs(line);
            sum += oC.map(Day3::getValue).orElse(0);
        }

        System.out.println("part1: " + sum);

        sum = 0;
        for (int i = 0; i < lines.size(); i += 3) {
            var oC = findInThreeBags(lines.subList(i, i + 3));
            sum += oC.map(Day3::getValue).orElse(0);
        }

        System.out.println("part2: " + sum);
    }

    private static Optional<Character> findInBothHalfs(String items) {
        String part1 = items.substring(0, items.length() / 2);
        String part2 = items.substring(items.length() / 2);

        for (char c : part1.toCharArray()) {
            if (part2.contains(String.valueOf(c))) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private static Optional<Character> findInThreeBags(List<String> bags) {

        for (char c : bags.get(0).toCharArray()) {
            if (bags.get(1).contains(String.valueOf(c)) && bags.get(2).contains(String.valueOf(c))) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    private static int getValue(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a' + 1;
        } else if (c >= 'A' && c <= 'Z') {
            return c - 'A' + 27;
        }
        return -1;
    }
}
