package y2022.day13;

public class Main {
    public static void main(String[] args) {
        PacketsParser parser = new PacketsParser();

//        var left = parser.parsePacket("[]");
//        var right = parser.parsePacket("[3]");

        System.out.println(parser.parsePacket("[1,[2,[3,[4,[5,6,7]]]],8,9]"));
    }
}
