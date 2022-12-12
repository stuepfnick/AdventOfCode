package y2022.day05;

public record Command(int moves, int from, int to) {

    @Override
    public String toString() {
        return "Command{" +
                "moves=" + moves +
                ", from=" + from +
                ", to=" + to +
                '}';
    }
}
