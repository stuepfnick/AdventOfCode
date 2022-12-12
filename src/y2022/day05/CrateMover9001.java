package y2022.day05;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CrateMover9001 {
    public void moveCrates(Deque<Character>[] crates, List<Command> commands) {
        for (var cmd : commands) {
            if (cmd.moves() == 1) {
                char crate = crates[cmd.from() - 1].removeLast();
                crates[cmd.to() - 1].addLast(crate);
            } else {
                var fromList = (LinkedList<Character>) crates[cmd.from() - 1];
                var toList = (LinkedList<Character>) crates[cmd.to() - 1];

                int startIndex = fromList.size() - (cmd.moves() );

                toList.addAll(fromList.subList(startIndex, fromList.size()));
                fromList.subList(startIndex, fromList.size()).clear();
            }
        }
    }
}
