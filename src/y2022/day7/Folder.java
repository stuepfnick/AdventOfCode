package y2022.day7;

import java.util.HashMap;
import java.util.Map;

public class Folder {

    private final String name;
    private final Map<String, Folder> folders = new HashMap<>();
    private final Map<String, Integer> files = new HashMap<>();
    private final Folder parent;

    public Folder(String name, Folder parent) {
        this.name = name;
        this.parent = parent;
    }

    public Map<String, Folder> getFolders() {
        return folders;
    }

    public Map<String, Integer> getFiles() {
        return files;
    }

    public Folder getParent() {
        return parent;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Folder{" +
                //"name='" + name + '\'' +
                "folders=" + folders +
                ", files=" + files +
                //", parent=" + parent +
                '}';
    }
}
