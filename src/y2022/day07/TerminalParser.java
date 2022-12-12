package y2022.day07;

import java.util.List;

public class TerminalParser {
    public Folder parseLines(List<String> lines) {
        Folder root = new Folder("root", null);
        Folder currentFolder = root;

        for (String line : lines) {
            var inputs = line.split("\\s");
            if (line.charAt(0) == '$') { // command
                if ("cd".equals(inputs[1])) {
                    switch (inputs[2]) {
                        case "/" -> currentFolder = root;
                        case ".." -> currentFolder = currentFolder.getParent();
                        default -> currentFolder = getOrCreateFolder(currentFolder, inputs[2]);
                    }
                }
            } else { // always ls?
                switch (inputs[0]) {
                    case "dir" -> getOrCreateFolder(currentFolder, inputs[1]);
                    default -> currentFolder.getFiles().put(inputs[1], Integer.parseInt(inputs[0]));
                }
            }
        }
        return root;
    }

    private Folder getOrCreateFolder(Folder currentFolder, String name) {
        var folder = currentFolder.getFolders().getOrDefault(name, new Folder(name, currentFolder));
        currentFolder.getFolders().put(name, folder);
        return folder;
    }
}
