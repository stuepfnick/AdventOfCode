package y2022.day11.big_int;

import java.math.BigInteger;
import java.util.List;
import java.util.function.Function;

public record BigMonkey(List<BigInteger> items, Function<BigInteger, BigInteger> operation, int divisibleBy, List<Integer> targets) {

}
