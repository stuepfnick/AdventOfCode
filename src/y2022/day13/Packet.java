package y2022.day13;

import java.util.LinkedList;
import java.util.List;

public class Packet implements Comparable<Packet> {

    private int number;
    private final List<Packet> packets = new LinkedList<>();

    public Packet() {
        number = -1;
    }

    public Packet(Integer number) {
        this.number = number;
    }

    public List<Packet> getPackets() {
        return packets;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int compareTo(Packet o) { // o is right
        if (number > -1 && o.number > -1) {
            return Integer.compare(o.number, number);
        }
        for (int i = 0; i < Math.max(packets.size(), o.packets.size()); i++) {
            if (i >= packets.size()) {
                return 1;
            } else if (i >= o.packets.size()) {
                return -1;
            }
            var left = packets.get(i);
            var right = o.packets.get(i);
            if (left.number > -1 && !right.packets.isEmpty()) {
                left.packets.add(new Packet(left.number));
                left.number = -1;
            }
            if (right.number > -1 && !left.packets.isEmpty()) {
                right.packets.add(new Packet(right.number));
                right.number = -1;
            }
            int x = left.compareTo(right);
            if (x != 0) {
                return x;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "number=" + number +
                ", packets=" + packets +
                '}';
    }
}
