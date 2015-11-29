package io.github.lionell;

import ua.yandex.shad.containers.IntList;
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
        //streamOperations(intStream);
        //streamToArray(intStream);
        //streamForEach(intStream);
        intListTest();
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

    public static void intListTest() {
        IntList list1 = new IntList();
        list1.add(3);
        list1.add(4);
        IntList list2 = new IntList();
        list2.add(5);
        list2.add(6);
        list1.addList(list2);
        list1.forEach(System.out::println);
//        list2.forEach(System.out::println);
    }
}
