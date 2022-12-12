package day8;

public class VisibleTreesCounter {

    byte[][] grid;
    byte[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public VisibleTreesCounter(byte[][] grid) {
        this.grid = grid;
    }

    public int checkVisibility() {
        int count = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                if (isVisible(x, y)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isVisible(int x, int y) {
        boolean isVisible = false;
        for (var dir : directions) {
            isVisible = isVisible || isVisibleFromDirection(x, y, dir);
        }
        return isVisible;
    }

    private boolean isVisibleFromDirection(int x, int y, byte[] dir) {
        byte value = grid[y][x];
        while (true) {
            x += dir[0];
            y += dir[1];
            if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
                return true;
            }
            if (grid[y][x] >= value) {
                return false;
            }
        }
    }
}
