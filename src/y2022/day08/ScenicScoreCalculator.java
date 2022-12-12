package y2022.day08;

public class ScenicScoreCalculator {

    byte[][] grid;
    byte[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public ScenicScoreCalculator(byte[][] grid) {
        this.grid = grid;
    }

    public int getHighestScore() {
        int highestScore = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                highestScore = Math.max(getScore(x, y), highestScore);
            }
        }
        return highestScore;
    }

    public int getScore(int x, int y) {
        int score = 1;
        for (var dir : directions) {
            score *= getScoreDirection(x, y, dir);
        }
        return score;
    }

    private int getScoreDirection(int x, int y, byte[] dir) {
        byte value = grid[y][x];
        int score = 0;
        while (true) {
            x += dir[0];
            y += dir[1];
            if (x < 0 || x >= grid[0].length || y < 0 || y >= grid.length) {
                return score;
            }
            score++;
            if (grid[y][x] >= value) {
                return score;
            }
        }
    }

    private int getScoreDirection2(int x, int y, byte[] dir) {
        byte value = grid[y][x];
        x += dir[0];
        y += dir[1];
        int score = 0;
        while (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
            score++;
            if (grid[y][x] >= value) {
                break;
            }
            x += dir[0];
            y += dir[1];
        }
        return score;
    }

}
