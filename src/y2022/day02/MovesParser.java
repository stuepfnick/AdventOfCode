package y2022.day02;

import java.util.ArrayList;
import java.util.List;

public class MovesParser {

    private final MoveConverter moveConverter = new MoveConverter();

    public List<Move> parse(String line) {
        var result = new ArrayList<Move>(2);

        for (int i = 0; i < 3; i += 2) {
            char letter = line.charAt(i);
            result.add(moveConverter.convertToMove(letter));
        }

        return result;
    }
}
