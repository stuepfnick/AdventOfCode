package y2022.day2;

import java.util.List;

public class Puzzle1 {

    public int solve(List<String> lines) {
        MovesParser parser = new MovesParser();
        Judge judge = new Judge();
        int totalScore = 0;

        for (String line : lines) {
            var moves = parser.parse(line);
            Move otherMove = moves.get(0);
            Move myMove = moves.get(1);
            totalScore += myMove.getValue();
            totalScore += judge.getPoints(myMove, otherMove);
        }

        return totalScore;
    }
}
