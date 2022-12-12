package y2022.day01;

public class Day1 {
    public static void main(String[] args) {
        FoodParser foodParser = new FoodParser();
        var elves = foodParser.parse("src/y2022/day01/files/input");

        Puzzle1 puzzle1 = new Puzzle1();
        System.out.println(puzzle1.solve(elves));

        Puzzle2 puzzle2 = new Puzzle2();
        System.out.println(puzzle2.solve(elves));
    }
}
