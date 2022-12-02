package day2;

import java.util.Map;

public class MoveConverter {
    private final Map<Character, Move> conversionMap = Map.of(
            'A', Move.ROCK, 'X', Move.ROCK,
            'B', Move.PAPER, 'Y', Move.PAPER,
            'C', Move.SCISSORS, 'Z', Move.SCISSORS
    );

    public Move convertToMove(char letter) {
        return conversionMap.get(letter);
    }
}
