package y2022.day1.old;

import y2022.day1.Elf;
import filehandlers.FileReader;

import java.util.LinkedList;
import java.util.List;

public class Puzzle1 {
    public static void main(String[] args) {
        FileReader reader = new FileReader();

        var lines = reader.read("src/y2022.day1/files/input");

        List<Elf> elves = new LinkedList<>();
        List<Integer> calories = new LinkedList<>();
        for (String line : lines) {
            try {
                calories.add(Integer.parseInt(line));
            } catch (NumberFormatException ignore) {
                elves.add(new Elf(calories));
                calories = new LinkedList<>();
            }
        }

        int result = elves.stream()
                .mapToInt(elf -> elf.getCalories().stream()
                        .reduce(0, Integer::sum))
                .max().orElse(0);

        System.out.println(result);
    }
}
