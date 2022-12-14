package y2022.day13;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Packet implements Comparable<Packet> {

    private final int number;
    private final List<Packet> packets = new ArrayList<>();

    public Packet() {
        number = -1;
    }

    public Packet(Packet packet) {
        packets.add(packet);
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
        if (number > -1 || o.number > -1) {
            return Integer.compare(o.number, number);
        }
        for (int i = 0; i < Math.max(packets.size(), o.packets.size()); i++) {
            if (i >= packets.size()) {
                return 1;
            } else if (i >= o.packets.size()) {
                return -1;
            }
            var left = (Packet) packets.get(i);
            var right = (Packet) o.packets.get(i);
            if (left.number > -1 && !right.packets.isEmpty()) {
                var leftCompare = new Packet(new Packet(left.number));
                int x = leftCompare.compareTo(right);
                if (x != 0) return x;
            }
            if (right.number > -1 && !left.packets.isEmpty()) {
                var rightCompare = new Packet(new Packet(right.number));
                int x = left.compareTo(rightCompare);
                if (x != 0) return x;
            }
            int x = left.compareTo(right);
            if (x != 0) return x;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Packet packet = (Packet) o;
        return number == packet.number && Objects.equals(packets, packet.packets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, packets);
    }

    @Override
    public String toString() {
        return "Packet{" +
                "number=" + number +
                ", packets=" + packets +
                '}';
    }
}
