package task5;

import java.util.Objects;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.*;

public class Task5 {
    public static void main(String[] args) {

        Stream<Integer> first = Stream.of(1, 2, 3, 4, 5);
        Stream<Integer> second = Stream.of(6, 7, 8);

        Stream<Integer> ziped = zip(first, second);

        ziped.forEach(System.out::println);
    }

    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);

        Spliterator<T> firstSpliterator = first.spliterator();
        Spliterator<T> secondSpliterator = second.spliterator();

        int characteristics = firstSpliterator.characteristics()
                & secondSpliterator.characteristics()
                & ~(Spliterator.DISTINCT | Spliterator.SORTED);//вычисляем характеристики объединенного сплитератора ислкючая дистинкт и сортед

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(firstSpliterator.getExactSizeIfKnown(), secondSpliterator.getExactSizeIfKnown()) : -1;//рассчитываем размер объединенного
        //сплитератора если размерность известна для обоих сплитераторов

        Spliterator<T> spliterator = new Spliterators.AbstractSpliterator<T>(zipSize, characteristics) {
            @Override
            public boolean tryAdvance(Consumer<? super T> action) {
                boolean first = firstSpliterator.tryAdvance(action);
                boolean second = secondSpliterator.tryAdvance(action);
                return first && second;
            }
        };

        return StreamSupport.stream(spliterator, first.isParallel() || second.isParallel())
                .onClose(() -> {
                    first.close();//закрываем первый поток при закрытии объединенного потока
                    second.close();//закрытие второй поток при закрытии объединенного потока
                });

    }
}
