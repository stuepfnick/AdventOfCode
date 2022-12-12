package y2022.day5;

import java.util.LinkedList;
import java.util.List;

public class CommandsParser {
    public List<Command> parseLines(List<String> lines) {

        List<Command> commands = new LinkedList<>();

        boolean isLower = false;
        for (String line : lines) {
            if (isLower) {
                line = line.replace("move ", "").replace("from ", "").replace("to ", "");
                String[] sNums = line.split(" ");
                Command cmd = new Command(Integer.parseInt(sNums[0]), Integer.parseInt(sNums[1]), Integer.parseInt(sNums[2]));
                commands.add(cmd);
            }
            if (line.isEmpty()) isLower = true;
        }
        return commands;
    }
}
