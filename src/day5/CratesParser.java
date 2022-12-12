package day5;

import java.util.*;

public class CratesParser {
    public Deque<Character>[] parseLines(List<String> lines) {
        List<String> upperLines = new LinkedList<>();
        int numberOfStacks = -1;
        String previous = "";
        for (String line : lines) {
            if (line.isEmpty()) {
                numberOfStacks = previous.replaceAll("\\s+", " ").trim().split(" ").length;
                break;
            } else {
                upperLines.add(0, line);
            }
            previous = line;
        }
        upperLines.remove(0);

        Deque<Character>[] crates = new LinkedList[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            crates[i] = new LinkedList<>();
        }

        for (String line : upperLines) {
            for (int i = 0; i < numberOfStacks; i++) {
                int pos = 1 + (i * 4);
                if (line.length() > pos && line.charAt(pos) != ' ') {
                    crates[i].add(line.charAt(pos));
                }
            }
        }

        return crates;
    }
}
