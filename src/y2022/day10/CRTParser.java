package y2022.day10;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CRTParser {

    private final List<Integer> cycles = new LinkedList<>();

    public void parseLines(List<String> lines) {
        int x = 1;
        cycles.add(x);
        for (String line : lines) {
            var cmds = line.split("\\s");
            if (cmds[0].equals("noop")) {
                cycles.add(x);
            } else if (cmds[0].equals("addx")) {
                cycles.add(x);
                x = x + Integer.parseInt(cmds[1]);
                cycles.add(x);
            }
        }
    }

    public int solvePart1() {
        int result = 0;
        for (int i = 19; i < cycles.size(); i += 40) {
            result += cycles.get(i) * (i + 1);
            //System.out.println("cycle: " + (i + 1) + " x: " + cycles.get(i));
        }
        return result;
    }

    public List<String> solvePart2() {
        List<String> lines = new ArrayList<>(6);
        StringBuilder line = new StringBuilder(40);
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 40; x++) {
                int value = cycles.get(y * 40 + x) - 1;
                if (x >= value && x <= value + 2) {
                    line.append("#");
                } else {
                    line.append(" ");
                }
            }
            lines.add(line.toString());
            line = new StringBuilder(40);
        }
        return lines;
    }

    public BufferedImage solvePart2AsImage() {
        BufferedImage image = new BufferedImage(40, 6, BufferedImage.TYPE_BYTE_GRAY);
        for (int y = 0; y < 6; y++) {
            for (int x = 0; x < 40; x++) {
                int value = cycles.get(y * 40 + x) - 1;
                if (x >= value && x <= value + 2) {
                    image.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }
        return image;
    }
}
