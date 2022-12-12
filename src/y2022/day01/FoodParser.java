package y2022.day01;

import filehandlers.FileReader;

import java.util.LinkedList;
import java.util.List;

public class FoodParser {
    private final FileReader reader = new FileReader();

    public List<Elf> parse(String file) {
        var lines = reader.read(file);

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
        return elves;
    }
}
