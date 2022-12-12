package y2022.day12;

import java.util.List;

public class GridParser {

    private Vector2Int startPos;
    private Vector2Int endPos;

    public char[][] parseLines(List<String> lines) {
        var grid = new char[lines.size()][lines.get(0).length()];
        for (int y = 0; y < lines.size(); y++) {
            for (int x = 0; x < lines.get(0).length(); x++) {
                char c = lines.get(y).charAt(x);
                grid[y][x] = c;
                if (c == 'S') {
                    startPos = new Vector2Int(x, y);
                    grid[y][x] = 'a' - 1;
                } else if (c == 'E') {
                    endPos = new Vector2Int(x, y);
                    grid[y][x] = 'z' + 1;
                }
            }
        }
        return grid;
    }

    public Vector2Int getStartPos() {
        return startPos;
    }

    public Vector2Int getEndPos() {
        return endPos;
    }
}
