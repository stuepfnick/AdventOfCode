package y2022.day07;

import filehandlers.FileReader;

import java.util.*;

public class Day7 {

    private final FileReader reader = new FileReader();
    int unrealisticSize;

    List<Integer> sizes = new LinkedList<>();

    public static void main(String[] args) {
        Day7 day = new Day7();
        day.solve("input");
    }

    private void solve(String fileName) {
        var lines = reader.read("src/y2022/day07/files/" + fileName);
        TerminalParser parser = new TerminalParser();
        Folder root = parser.parseLines(lines);

        System.out.println("total size: " + getSize(root));
        //System.out.println(root);
        unrealisticSize = 0;
        System.out.println("realistic: " + getSizesUnder100k2(root));
        System.out.println("unrealistic: " + unrealisticSize);

        // Part2
        int total = 70_000_000;
        int neededFree = 30_000_000;
        int minSizeToDelete = neededFree - (total - getSize(root));
        findFoldersToDelete(root, minSizeToDelete);
        //System.out.println(sizes);
        System.out.println("Size of folder needed to delete: " + Collections.min(sizes));
    }

    private int getSize(Folder folder) {
        int size = 0;
        for (var file : folder.getFiles().values()) {
            size += file;
        }
        for (var subFolder : folder.getFolders().values()) {
            size += getSize(subFolder);
        }
        return size;
    }

    private int getSizesUnder100k(Folder folder) {
        int size = 0;
        for (var file : folder.getFiles().values()) {
            size += file;
        }
        for (var subFolder : folder.getFolders().values()) {
            size += getSizesUnder100k(subFolder);
        }
        if (!folder.getName().equals("root") && size < 100000) {
            unrealisticSize += size;
        }
        return size;
    }

    private int getSizesUnder100k2(Folder folder) {
        int size = 0;
        for (var subFolder : folder.getFolders().values()) {
            size += getSizesUnder100k2(subFolder);
        }
        if (getSize(folder) < 100_000) {
            size += getSize(folder);
        }
        return size;
    }

    private int findFoldersToDelete(Folder folder, int required) {
        int size = 0;
        for (var file : folder.getFiles().values()) {
            size += file;
        }
        for (var subFolder : folder.getFolders().values()) {
            size += findFoldersToDelete(subFolder, required);
        }
        if (size >= required) {
            sizes.add(size);
        }
        return size;
    }

}
