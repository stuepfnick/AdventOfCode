package y2022.day04;

public class Parser2 {
    public boolean parse(String line) {
        String[] parts = line.split(",");

        int[] start = new int[2];
        int[] end = new int[2];

        for (int i = 0; i < 2; i++) {
            String[] boundString = parts[i].split("-");
            start[i] = Integer.parseInt(boundString[0]);
            end[i] = Integer.parseInt(boundString[1]);
        }

        for (int i = 0; i < 2; i++) {
            if (start[i] >= start[1 - i] && start[i] <= end[1 - i]
            || end[i] >= start[1 - i] && end[i] <= end[1 - i]) {
                return true;
            }
        }
        return false;
    }
}
