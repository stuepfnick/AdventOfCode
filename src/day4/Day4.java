package day4;

import filehandlers.FileReader;

public class Day4 {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        var lines = reader.read("src/day4/files/input");

        Parser1 parser1 = new Parser1();
        Parser2 parser2 = new Parser2();

        int count1 = 0;
        int count2 = 0;
        for (String line : lines) {
            if (parser1.parse(line)) {
                count1++;
            }
            if (parser2.parse(line)) {
                count2++;
            }
        }

        System.out.println("part1: " + count1);
        System.out.println("part2: " + count2);
    }
}
