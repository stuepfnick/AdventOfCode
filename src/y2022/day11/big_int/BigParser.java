package y2022.day11.big_int;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class BigParser {
    public List<BigMonkey> parseLines(List<String> lines) {
        List<BigMonkey> monkeys = new LinkedList<>();
        List<BigInteger> items = null;
        Function<BigInteger, BigInteger> operation = null;
        int divisibleBy = 0;
        List<Integer> targets = new LinkedList<>();

        for (String line : lines) {
            if (line.startsWith("Monkey")) continue;
            if (line.isEmpty()) {
                monkeys.add(new BigMonkey(items, operation, divisibleBy, targets));
                targets = new LinkedList<>();
            }
            var info = line.split(": ");
            switch (info[0].stripLeading()) {
                case "Starting items" -> items = new LinkedList<>(Arrays.stream(info[1].split(", "))
                        .map(BigInteger::new)
                        .toList());
                case "Operation" -> {
                    String[] s = info[1].split("= old ")[1].split("\\s");
                    if (s[1].equals("old")) {
                        operation = (x) -> x.multiply(x);
                    } else {
                        BigInteger value = new BigInteger(s[1]);
                        if (s[0].charAt(0) == '*') {
                            operation = (x) -> x.multiply(value);
                        } else if (s[0].charAt(0) == '+') {
                            operation = (x) -> x.add(value);
                        }
                    }
                }
                case "Test" -> divisibleBy = Integer.parseInt(info[1].split("by ")[1]);
                case "If true", "If false" -> targets.add(Integer.parseInt(info[1].split("monkey ")[1]));
            }
        }
        monkeys.add(new BigMonkey(items, operation, divisibleBy, targets));
        return monkeys;
    }
}
