package day11.big_int;

import filehandlers.FileReader;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class Day11Big {

    FileReader reader = new FileReader();

    public static void main(String[] args) {
        Day11Big day = new Day11Big();
        day.solve("inputManuel");
    }

    public void solve(String fileName) {
        var lines = reader.read("src/day11/files/" + fileName);
        var bigMonkeys = new BigParser().parseLines(lines);
        long[] inspectionCounts = new long[bigMonkeys.size()];
        for (int i = 0; i < 10000; i++) {
            playRounds2(bigMonkeys, inspectionCounts);
            if (i == 0 || i == 19 || (i + 1) % 1000 == 0) {
                System.out.println(bigMonkeys.stream().map(BigMonkey::items).toList());
                System.out.println((i + 1) + ": " + Arrays.toString(inspectionCounts));
            }
        }
    }

    private void playRounds2(List<BigMonkey> monkeys, long[] inspectionCounts) {
        int i = 0;
        BigInteger zero = new BigInteger("0");
        for (var monkey : monkeys) {
            while (monkey.items().size() > 0) {
                BigInteger item = monkey.items().remove(0);
                inspectionCounts[i]++;
                item = monkey.operation().apply(item);
                int targetIndex = item.mod(new BigInteger(String.valueOf(monkey.divisibleBy()))).equals(zero) ? 0 : 1;
                monkeys.get(monkey.targets().get(targetIndex)).items().add(item);
            }
            i++;
        }
    }
}
