package day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class MonkeyParser {

    public List<Monkey> parseLines(List<String> lines) {
        
        List<Monkey> monkeys = new LinkedList<>();
        List<Long> items = null;
        Function<Long, Long> operation = null;
        int divisibleBy = 0;
        List<Integer> targets = new ArrayList<>(2);

        for (String line : lines) {
            if (line.startsWith("Monkey")) continue;
            if (line.isEmpty()) {
                monkeys.add(new Monkey(items, operation, divisibleBy, targets));
                targets = new LinkedList<>();
            }
            var info = line.split(": ");
            switch (info[0].stripLeading()) {
                case "Starting items" -> items = new LinkedList<>(Arrays.stream(info[1].split(", "))
                            .map(Long::valueOf)
                            .toList());
                case "Operation" -> {
                    String[] s = info[1].split("= old ")[1].split("\\s");
                    if (s[1].equals("old")) {
                        operation = (x) -> x * x;
                    } else {
                        int value = Integer.parseInt(s[1]);
                        if (s[0].charAt(0) == '*') {
                            operation = (x) -> x * value;
                        } else if (s[0].charAt(0) == '+') {
                            operation = (x) -> x + value;
                        }
                    }
                }
                case "Test" -> divisibleBy = Integer.parseInt(info[1].split("by ")[1]);
                case "If true", "If false" -> targets.add(Integer.parseInt(info[1].split("monkey ")[1]));
            }
        }
        monkeys.add(new Monkey(items, operation, divisibleBy, targets));
        return monkeys;
    }
}
