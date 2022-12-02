package day2;

import java.util.List;

public class Puzzle2 {

    public int solve(List<String> lines) {
        MovesParser movesParser = new MovesParser();
        StrategyParser strategyParser = new StrategyParser();
        Judge judge = new Judge();
        int totalScore = 0;

        for (String line : lines) {
            var moves = movesParser.parse(line);
            Move otherMove = moves.get(0);
            Move myMove = strategyParser.parseToMove(line);
            totalScore += myMove.getValue();
            totalScore += judge.getPoints(myMove, otherMove);
        }

        return totalScore;
    }
}
