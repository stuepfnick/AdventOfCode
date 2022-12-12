package y2022.day5;

import filehandlers.FileReader;

import java.util.Deque;

public class Day5 {

    FileReader reader = new FileReader();
    public static void main(String[] args) {
        Day5 day = new Day5();
        day.solveParts();
    }

    private void solveParts() {
        var lines = reader.read("src/y2022/day5/files/input");

        CratesParser cratesParser = new CratesParser();
        var crates1 = cratesParser.parseLines(lines);
        var crates2 = cratesParser.parseLines(lines);

        CommandsParser commandsParser = new CommandsParser();
        var commands = commandsParser.parseLines(lines);

        CrateMover9000 crane1 = new CrateMover9000();
        crane1.moveCrates(crates1, commands);

        System.out.println("result1: " + getResult(crates1));

        CrateMover9001 crane2 = new CrateMover9001();
        crane2.moveCrates(crates2, commands);

        System.out.println("result2: " + getResult(crates2));
    }

    private String getResult(Deque<Character>[] crates) {
        StringBuilder result = new StringBuilder();
        for (var crate : crates) {
            result.append(crate.peekLast());
        }
        return result.toString();
    }

}
