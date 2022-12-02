package day2;

public enum Move {
    ROCK(1, 2),
    PAPER(2, 0),
    SCISSORS(3, 1);

    private final int value;
    private final int winsOverOrdinal;

    Move(int value, int winsOverOrdinal) {
        this.value = value;
        this.winsOverOrdinal = winsOverOrdinal;
    }

    public int getValue() {
        return value;
    }

    public Move getWinsOverMove() {
        return Move.values()[winsOverOrdinal];
    }
}
