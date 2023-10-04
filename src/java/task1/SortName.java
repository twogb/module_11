package task1;

import java.util.*;

public class SortName {
    public static void main(String[] args) {

        List<String> names = List.of("Ivan", "Sam", "Peter", "Bob", "Svetlana", "Anna");

        String format = formatName(names);

        System.out.println(format);


    }


    public static String formatName(List<String> names) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < names.size(); i++) {
            if (i % 2 == 0) {
                int position = i + 1;
                String name = names.get(i);

                stringBuilder.append(position).append(". ").append(name);

                if (i < names.size() - 2) {
                    stringBuilder.append(", ");
                }
            }
        }
        return stringBuilder.toString();
    }

}
