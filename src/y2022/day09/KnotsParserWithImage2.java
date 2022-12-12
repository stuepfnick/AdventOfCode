package y2022.day09;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KnotsParserWithImage2 {

    private final Set<String> visitedString = new HashSet<>();
    private final int numberOfTails;
    private int[][] knotPos;

    private BufferedImage image = new BufferedImage(403, 217, BufferedImage.TYPE_INT_RGB);
    private byte[][] visited = new byte[217][403];

    public BufferedImage getImage() {
        return image;
    }

    Map<Character, int[]> directionMap = Map.of(
            'L', new int[]{-1, 0},
            'R', new int[]{1, 0},
            'U', new int[]{0, -1},
            'D', new int[]{0, 1});

    public KnotsParserWithImage2(int numberOfTails) {
        this.numberOfTails = numberOfTails;
        knotPos = new int[numberOfTails + 1][2];
    }

    public int parseLines(List<String> lines) {
        int maxX = 0, minX = 0;
        int maxY = 0, minY = 0;
        for (int i = numberOfTails; i >= 0; i--) {
            knotPos = new int[numberOfTails + 1][2];
            for (String line : lines) {
                int numberOfMoves = Integer.parseInt(line.split("\\s")[1]);
                move(directionMap.get(line.charAt(0)), numberOfMoves, i);
    //            maxX = Math.max(maxX, knotPos[0][0]);
    //            minX = Math.min(minX, knotPos[0][0]);
    //            maxY = Math.max(maxY, knotPos[0][1]);
    //            minY = Math.min(minY, knotPos[0][1]);
            }
        }
//        System.out.println(maxX + ", " + maxY);
//        System.out.println(minX + ", " + minY);

//        int highest = 0;
//        for (int y = 0; y < visited.length; y++) {
//            for (int x = 0; x < visited[0].length; x++) {
//                highest = Math.max(highest, visited[y][x]);
//                float b = Math.min(visited[y][x] / 92f, 1f);
//                b = (float) Math.sqrt(b);
//                image.setRGB(x, y, Color.HSBtoRGB(.8f + (b*.5f), 1f - (b *.8f), b));
//            }
//        }
//        System.out.println("hi: " + highest);
        return visitedString.size();
    }

    private void move(int[] dir, int numberOfMoves, int iter) {
        for (int i = 0; i < numberOfMoves; i++) {
            knotPos[0][0] += dir[0];
            knotPos[0][1] += dir[1];
            updateTail();
//            visitedString.add(Arrays.toString(knotPos[knotPos.length - 1]));
//            visited[knotPos[0][1] + 51][knotPos[0][0] + 7]++;
//            for (int j = numberOfTails; j > 0; j--) {
//                image.setRGB(knotPos[j][0] + 7, knotPos[j][1] + 51, Color.HSBtoRGB((j-1)/9f, 1f, .8f));
//            }
//            image.setRGB(knotPos[0][0] + 7, knotPos[0][1] + 51, Color.WHITE.getRGB());
            image.setRGB(knotPos[iter][0] + 7, knotPos[iter][1] + 51, Color.HSBtoRGB(iter / 11f, 1f, .7f));
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
            //visited[knotPos[i][1] + 51][knotPos[i][0] + 7]++;
        }
    }

}
