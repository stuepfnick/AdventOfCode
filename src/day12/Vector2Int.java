package day12;

import java.util.Objects;

public class Vector2Int {
    private int x;
    private int y;

    public Vector2Int(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2Int clone() {
        return new Vector2Int(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Vector2Int add(Vector2Int delta) {
        return new Vector2Int(x + delta.x, y + delta.y);
    }

    public Vector2Int addLimited(Vector2Int delta, Vector2Int size) {
        return new Vector2Int(limit(this.x + delta.x, size.x - 1), limit(this.y + delta.y, size.y - 1));
    }

    public void translate(Vector2Int delta) {
        this.x += delta.x;
        this.y += delta.y;
    }

    public void moveLimited(Vector2Int delta, Vector2Int size) {
        this.x = limit(this.x + delta.x, size.x - 1);
        this.y = limit(this.y + delta.y, size.y - 1);
    }

    private int limit(int value, int max) {
        value = Math.max(0, value);
        return Math.min(max, value);
    }

    public int getSquareLength() {
        return (x * x) + (y * y);
    }

    public double getLength() {
        return Math.sqrt(getSquareLength());
    }

    public static Vector2Int up() {
        return new Vector2Int(0, -1);
    }
    public static Vector2Int down() {
        return new Vector2Int(0, 1);
    }
    public static Vector2Int left() {
        return new Vector2Int(-1, 0);
    }
    public static Vector2Int right() {
        return new Vector2Int(1, 0);
    }
    public static Vector2Int zero() {
        return new Vector2Int(0, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2Int that = (Vector2Int) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}
