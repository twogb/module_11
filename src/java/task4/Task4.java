package task4;

import java.util.stream.*;

public class Task4 {
    public static void main(String[] args) {

        long a = 5214903917L;
        long c = 11L;
        long m = (long) Math.pow(2, 48);
        long seed = System.currentTimeMillis();

        Stream<Long> random = generatorRandomStream(seed, a, c, m);

        random.limit(10).forEach(System.out::println);
    }

    public static Stream<Long> generatorRandomStream(long seed, long a, long c, long m) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }


}
