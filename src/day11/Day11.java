package day11;

import filehandlers.FileReader;

import java.util.Comparator;
import java.util.List;

public class Day11 {

    private final FileReader reader = new FileReader();

    public static void main(String[] args) {
        Day11 day = new Day11();
        long startTime = System.currentTimeMillis();
        day.solve("example");
        System.out.println("Mills: " + (System.currentTimeMillis() - startTime));
    }

    private void solve(String fileName) {
        var lines = reader.read("src/day11/files/" + fileName);
        MonkeyParser parser = new MonkeyParser();
        var monkeys = parser.parseLines(lines);

        for (int i = 0; i < 20; i++) {
            playRound(monkeys, 0);
        }

        var result = monkeys.stream()
                .sorted(Comparator.comparing(Monkey::getNumberOfInspections).reversed())
                .mapToLong(Monkey::getNumberOfInspections)
                .limit(2)
                .reduce(1, (a, b) -> a * b);

        System.out.println("part1: " + result);

        monkeys = parser.parseLines(lines);

        int gcd = monkeys.stream().mapToInt(Monkey::getDivisibleBy).reduce(1, (a, b) -> a * b);
        for (int i = 0; i < 10000; i++) {
            playRound(monkeys, gcd);
//            if (i == 0 || i == 19 || (i + 1) % 1000 == 0) {
//                System.out.println(monkeys.stream().map(Monkey::getItems).toList());
//                System.out.println((i + 1) + ": " + monkeys.stream().map(Monkey::getNumberOfInspections).toList());
//            }
        }

        result = monkeys.stream()
                .sorted(Comparator.comparing(Monkey::getNumberOfInspections).reversed())
                .mapToLong(Monkey::getNumberOfInspections)
                .limit(2)
                .reduce(1, (a, b) -> a * b);

        System.out.println("part2: " + result);
    }

    private void playRound(List<Monkey> monkeys, int gdc) {
        for (var monkey : monkeys) {
            while (monkey.getItems().size() > 0) {
                monkey.increaseNumberOfInspections();
                long item = monkey.getItems().remove(0);
                item = monkey.getOperation().apply(item);
                item = gdc == 0 ? item / 3 : item % gdc;
                int targetIndex = item % monkey.getDivisibleBy() == 0 ? 0 : 1;
                monkeys.get(monkey.getTargets().get(targetIndex)).getItems().add(item);
            }
        }
    }

}
