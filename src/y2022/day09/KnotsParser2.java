package y2022.day09;

import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KnotsParser2 {

    private final Set<Point> visited = new HashSet<>();
    private final Set<String> vString = new HashSet<>();
    private final int numberOfTails;
    private final Point[] knotPos;

    Map<Character, Point> directionMap = Map.of(
            'L', new Point(-1, 0),
            'R', new Point(1, 0),
            'U', new Point(0, -1),
            'D', new Point(0, 1));

    public KnotsParser2(int numberOfTails) {
        this.numberOfTails = numberOfTails;
        knotPos = new Point[numberOfTails + 1];
        for (int i = 0; i <= numberOfTails; i++) {
            knotPos[i] = new Point();
        }
    }

    public int parseLines(List<String> lines) {
        for (String line : lines) {
            int numberOfMoves = Integer.parseInt(line.split("\\s")[1]);
            move(directionMap.get(line.charAt(0)), numberOfMoves);
        }
        System.out.println(visited);
        return visited.size();
    }

    private void move(Point dir, int numberOfMoves) {
        for (int i = 0; i < numberOfMoves; i++) {
            knotPos[0].x += dir.x;
            knotPos[0].y += dir.y;
            updateTail();
            vString.add(knotPos[knotPos.length - 1].toString());
            visited.add((Point) knotPos[knotPos.length - 1].clone());
        }
    }

    private void updateTail() {
        for (int i = 1; i <= numberOfTails; i++) {
            int deltaX = knotPos[i - 1].x - knotPos[i].x;
            int deltaY = knotPos[i - 1].y - knotPos[i].y;
            int diffX = Math.abs(deltaX);
            int diffY = Math.abs(deltaY);

            if (diffX > 1) {
                if (diffY > 0) {
                    knotPos[i].y += Math.signum(deltaY);
                }
                knotPos[i].x += Math.signum(deltaX);
            } else if (diffY > 1) {
                if (diffX > 0) {
                    knotPos[i].x += Math.signum(deltaX);
                }
                knotPos[i].y += Math.signum(deltaY);
            }
        }
    }

}
