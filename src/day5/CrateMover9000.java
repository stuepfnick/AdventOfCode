package day5;

import java.util.Deque;
import java.util.List;

public class CrateMover9000 {

    public void moveCrates(Deque<Character>[] crates, List<Command> commands) {
        for (var cmd : commands) {
            for (int i = 0; i < cmd.moves(); i++) {
                char crate = crates[cmd.from() - 1].removeLast();
                crates[cmd.to() - 1].addLast(crate);
            }
        }
    }
}
