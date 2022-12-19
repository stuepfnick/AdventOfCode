package y2022.day06;

import filehandlers.FileReader;

import java.util.*;

public class Day6b {

    FileReader reader = new FileReader();

    public static void main(String[] args) {
        Day6b day = new Day6b();
//        for (int i = 1; i <= 5; i++) {
//            day.solvePuzzle("example" + i, 14);
//        }
        day.solvePuzzle("input", 4);
        day.solvePuzzle("input", 14);
    }

    public void solvePuzzle(String fileName, int targetLength) {
        var input = reader.read("src/y2022/day06/files/" + fileName).get(0);

        int result = findFirstNonRepeating(input, targetLength);
        System.out.println(result);
    }
    public int findFirstNonRepeating(String input, int targetLength) {
        List<Character> marker = new LinkedList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            marker.add(c);
            if (marker.size() > targetLength) {
                marker.remove(0);
                if (marker.size() == new HashSet<>(marker).size()) {
                    return i + 1;
                }
            }
        }
        return -1;
    }
}
