package day1.old;

import day1.Elf;
import filehandlers.FileReader;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Puzzle2 {
    public static void main(String[] args) {
        FileReader reader = new FileReader();

        var lines = reader.read("src/day1/files/input");

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
                .map(elf -> elf.getCalories().stream()
                        .reduce(0, Integer::sum))
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .reduce(0, Integer::sum);

        System.out.println(result);
    }
}
