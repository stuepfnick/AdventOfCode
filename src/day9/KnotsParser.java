package day9;

import java.util.*;

public class KnotsParser {

    private final Set<String> visitedString = new HashSet<>();
    private final int numberOfTails;
    private final int[][] knotPos;

    Map<Character, int[]> directionMap = Map.of(
            'L', new int[]{-1, 0},
            'R', new int[]{1, 0},
            'U', new int[]{0, -1},
            'D', new int[]{0, 1});

    public KnotsParser(int numberOfTails) {
        this.numberOfTails = numberOfTails;
        knotPos = new int[numberOfTails + 1][2];
    }

    public int parseLines(List<String> lines) {
        for (String line : lines) {
            int numberOfMoves = Integer.parseInt(line.split("\\s")[1]);
            move(directionMap.get(line.charAt(0)), numberOfMoves);
        }
        return visitedString.size();
    }

    private void move(int[] dir, int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            knotPos[0][0] += dir[0];
            knotPos[0][1] += dir[1];
            updateTail();
            visitedString.add(Arrays.toString(knotPos[knotPos.length - 1]));
        }
    }

    private void updateTail() {
        for (int i = 1; i <= numberOfTails; i++) {
            int deltaX = knotPos[i - 1][0] - knotPos[i][0];
            int deltaY = knotPos[i - 1][1] - knotPos[i][1];
            int diffX = Math.abs(deltaX);
            int diffY = Math.abs(deltaY);

            if (diffX > 1) {
                if (diffY > 0) {
                    knotPos[i][1] += deltaY / diffY;
                }
                knotPos[i][0] += deltaX / diffX;
            } else if (diffY > 1) {
                if (diffX > 0) {
                    knotPos[i][0] += deltaX / diffX;
                }
                knotPos[i][1] += deltaY / diffY;
            }
        }
    }

}
