package io.github.lionell;

import ua.yandex.shad.stream.AsIntStream;
import ua.yandex.shad.stream.IntStream;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello from App!");
        IntStream intStream = AsIntStream.of(1, 2, 3);
        streamOperations(intStream);
        streamToArray(intStream);
        streamForEach(intStream);
    }

    public static void streamOperations(IntStream intStream) {
        int res = intStream
                .filter(x -> x > 0)
                .map(x -> x * x)
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1))
                .reduce(0, (sum, x) -> sum += x);
        System.out.println(res);
    }

    public static void streamToArray(IntStream intStream) {
        int[] intArray = intStream.toArray();
        for (int x : intArray) {
            System.out.println(x + " ");
        }
    }

    public static void streamForEach(IntStream intStream) {
        StringBuilder sb = new StringBuilder();
        intStream.forEach(sb::append);
        System.out.println(sb.toString());
    }
}
