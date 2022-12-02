package day2;

import filehandlers.FileReader;

public class Day2 {
    public static void main(String[] args) {
        FileReader reader = new FileReader();
        var lines = reader.read("src/day2/input.txt");

        Puzzle1 puzzle1 = new Puzzle1();
        System.out.println(puzzle1.solve(lines));

        Puzzle2 puzzle2 = new Puzzle2();
        System.out.println(puzzle2.solve(lines));
    }
}
