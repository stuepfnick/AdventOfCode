package y2022.day8;

import java.util.List;

public class GridParser {
    public byte[][] parseLines(List<String> lines) {
        byte[][] grid = new byte[lines.size()][lines.get(0).length()];

        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                grid[y][x] = (byte) (lines.get(y).charAt(x) - 48);
            }
        }

        return grid;
    }
}
