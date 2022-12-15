package y2022.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PacketsParser {

    public int parseLines(List<String> lines) {

        lines = List.of(String.join("\n", lines).split("\n\n"));

        var packetStrings = lines.stream()
                .map(line -> List.of(line.split("\n")))
                .toList();

        int sum = 0;
        for (int i = 0; i < packetStrings.size(); i++) {
            var packetString = packetStrings.get(i);
            var left = parsePacket(packetString.get(0));
            var right = parsePacket(packetString.get(1));
            int result = left.compareTo(right);
            sum += result > 0 ? i + 1 : 0;
        }
        return sum;
    }

    public int parseLines2(List<String> lines) {

        var packets = new ArrayList<>(lines.stream()
                .filter(line -> !line.isEmpty())
                .map(this::parsePacket)
                .toList());

        var dividerPacket1 = parsePacket("[[2]]");
        var dividerPacket2 = parsePacket("[[6]]");

        packets.add(dividerPacket1);
        packets.add(dividerPacket2);

        packets.sort(Collections.reverseOrder());

        int index1 = packets.indexOf(dividerPacket1) + 1;
        int index2 = packets.indexOf(dividerPacket2) + 1;

        return index1 * index2;
    }

    Packet parsePacket(String input) {
        Packet packet = new Packet();
        for (int i = 1; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '[') {
                int depth = 1;
                for (int subEnd = i + 1; subEnd < input.length(); subEnd++) {
                    if (input.charAt(subEnd) == '[') {
                        depth++;
                    } else if (input.charAt(subEnd) == ']') {
                        depth--;
                    }
                    if (depth == 0) {
                        String sub = input.substring(i, subEnd + 1);
                        packet.getPackets().add(parsePacket(sub));
                        i = subEnd;
                        break;
                    }
                }
            } else {
                if (c == ',' || c == ']') {
                    continue;
                }
                int subEnd = input.indexOf(',', i);
                if (subEnd == -1) {
                    subEnd = input.indexOf(']', i);
                }
                String numString = input.substring(i, subEnd);
                packet.getPackets().add(new Packet(Integer.parseInt(numString)));
                i = subEnd;
            }

        }
        return packet;
    }

    Packet parsePacketOld(String input) {
        input = input.substring(1, input.length() - 1);
        Packet packet = new Packet();
        int subStart = input.indexOf('[');
        while (subStart != -1) {
            int depth = 0;
            for (int i = subStart + 1; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == ']') {
                    if (depth == 0) {
                        String sub = input.substring(subStart, i + 1);
                        packet.getPackets().add(parsePacket(sub));
                        input = input.substring(0, subStart) + input.substring(i + 1);
                        break;
                    } else {
                        depth--;
                    }
                } else if (c == '[') {
                    depth++;
                }
            }
            subStart = input.indexOf('[');
        }
        for (String numString : input.split(",")) {
            if (!numString.isEmpty()) {
                packet.getPackets().add(new Packet(Integer.parseInt(numString)));
            }
        }
        return packet;
    }

}
