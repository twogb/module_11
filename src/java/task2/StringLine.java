package task2;
import java.util.*;
import java.util.stream.Collectors;

public class StringLine
{
    public static void main(String[] args) {
        List<String> list= List.of("apple","banana","cherry","date");

        List<String > result = sortList(list);

        System.out.println(result);
    }

    public static List<String> sortList(List<String> inputList)
    {
        List<String> stringList = inputList.stream()
                .map(String::toUpperCase)
                .sorted((s1,s2)->s2.compareTo(s1))
                .collect(Collectors.toList());

        return stringList;
    }
}
