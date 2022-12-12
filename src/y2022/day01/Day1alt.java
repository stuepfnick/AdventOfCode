package y2022.day01;

import java.util.List;

public class Day1alt {
    private final List<Elf> elves;
    public static void main(String[] args) {
        Day1alt day1Alt = new Day1alt();
        day1Alt.solvePuzzles();
    }
    private Day1alt() {
        FoodParser foodParser = new FoodParser();
        elves = foodParser.parse("src/y2022/day1/files/input");
    }
    private void solvePuzzles() {
        Puzzle1 puzzle1 = new Puzzle1();
        Puzzle2 puzzle2 = new Puzzle2();

        System.out.println(puzzle1.solve(elves));
        System.out.println(puzzle2.solve(elves));
    }
}
