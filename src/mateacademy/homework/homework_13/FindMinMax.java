package mateacademy.homework.homework_13;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindMinMax {

    public static <T> void findMinMax(Stream<? extends T> stream,
                                      Comparator<? super T> order,
                                      BiConsumer<? super T, ? super T> minMaxConsumer) {
        List<? extends T> listFromStream = stream.sorted(order).collect(Collectors.toList());
        if (listFromStream.isEmpty()) {
            minMaxConsumer.accept(null, null);
        } else {
            minMaxConsumer
                    .accept(listFromStream.get(0),
                            listFromStream.get(listFromStream.size() - 1));
        }
    }
}
