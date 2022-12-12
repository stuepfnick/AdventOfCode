package y2022.day02;

public class Judge {
    public int getPoints(Move myMove, Move otherMove) {
        if (myMove.getWinsOverMove() == otherMove) {
            return 6;
        }
        if (myMove == otherMove) {
            return 3;
        }
        return 0;
    }
}
