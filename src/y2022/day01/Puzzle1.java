package y2022.day01;

import java.util.List;

public class Puzzle1 {
    public int solve(List<Elf> elves) {
        return elves.stream()
                .mapToInt(elf -> elf.getCalories().stream()
                        .reduce(0, Integer::sum))
                .max().orElse(0);
    }
}
