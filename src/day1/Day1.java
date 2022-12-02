package day1;

import java.util.List;

public class Day1 {

    private final List<Elf> elves;

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        day1.solvePuzzles();
    }

    private Day1() {
        FoodParser foodParser = new FoodParser();
        elves = foodParser.parse("src/day1/input.txt");
    }

    private void solvePuzzles() {
        Puzzle1 puzzle1 = new Puzzle1();
        Puzzle2 puzzle2 = new Puzzle2();

        System.out.println(puzzle1.solve(elves));
        System.out.println(puzzle2.solve(elves));
    }
}
