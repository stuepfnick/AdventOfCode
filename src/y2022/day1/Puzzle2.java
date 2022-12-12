package y2022.day1;

import java.util.Comparator;
import java.util.List;

public class Puzzle2 {
    public int solve(List<Elf> elves) {
        return elves.stream()
                .map(elf -> elf.getCalories().stream()
                        .reduce(0, Integer::sum))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);
    }
}
