package io.github.lionell;

import ua.yandex.shad.stream.AsIntStream;
import ua.yandex.shad.stream.IntStream;
import java.util.Scanner;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class App {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Hello from App!");
        System.out.println("----------------");
        System.out.println("Now we gonna filter elements with x > 0 predicate.");
        System.out.println("Than map them using x -> x * x function.");
        System.out.println("Flat map them just like x -> (x - 1, x, x + 1).");
        System.out.println("And sum using reduce method.");
        System.out.println("----------------");
        System.out.print("Enter elements count > ");
        int n = in.nextInt();
        System.out.print("Enter elements > ");
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = in.nextInt();
        }
        IntStream intStream = AsIntStream.of(ints);
        int res = intStream
                .filter(x -> x > 0)
                .map(x -> x * x)
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1))
                .reduce(0, (sum, x) -> sum += x);
        System.out.println("Here you are! You result is: " + res);
        in.close();
    }
}
