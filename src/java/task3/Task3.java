package task3;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Task3
{
    public static void main(String[] args) {
        String [] array = {"1, 2, 0", "4, 5"};

        String result = Arrays.stream(array)
                .flatMap(s->Arrays.stream(s.split(", ")))
                .map(Integer::parseInt)
                .distinct()
                .sorted()
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        System.out.println(result);
    }
}
