package day1;

import java.util.List;

public class Elf {
    private final List<Integer> calories;

    public Elf(List<Integer> calories) {
        this.calories = calories;
    }

    public List<Integer> getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return "Elf{" +
                "calories=" + calories +
                '}';
    }
}
