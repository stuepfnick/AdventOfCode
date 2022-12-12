package y2022.day02;

import java.util.Map;

public class StrategyParser {
    private final Map<String, Move> conversionMap = Map.of(
            "A X", Move.SCISSORS,
            "A Y", Move.ROCK,
            "A Z", Move.PAPER,

            "B X", Move.ROCK,
            "B Y", Move.PAPER,
            "B Z", Move.SCISSORS,

            "C X", Move.PAPER,
            "C Y", Move.SCISSORS,
            "C Z", Move.ROCK
    );

    public Move parseToMove(String line) {
        return conversionMap.get(line);
    }
}
