package day11;

import java.util.List;
import java.util.function.Function;

public class Monkey {

    private final List<Long> items;
    private final Function<Long, Long> operation;
    private final int divisibleBy;
    private final List<Integer> targets;
    private long numberOfInspections;

    public Monkey(List<Long> items, Function<Long, Long> operation, int divisibleBy, List<Integer> targets) {
        this.items = items;
        this.operation = operation;
        this.divisibleBy = divisibleBy;
        this.targets = targets;
    }

    public List<Long> getItems() {
        return items;
    }

    public Function<Long, Long> getOperation() {
        return operation;
    }

    public int getDivisibleBy() {
        return divisibleBy;
    }

    public List<Integer> getTargets() {
        return targets;
    }

    public long getNumberOfInspections() {
        return numberOfInspections;
    }

    public void increaseNumberOfInspections() {
        numberOfInspections++;
    }

    @Override
    public String toString() {
        return "Monkey{" +
                "items=" + items +
                //", operation=" + operation +
                ", divisibleBy=" + divisibleBy +
                ", targets=" + targets +
                ", numberOfInspections=" + numberOfInspections +
                '}';
    }
}
