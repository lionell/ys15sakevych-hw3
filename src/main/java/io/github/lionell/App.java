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
        System.out.print("Enter elements count > ");
        int n = in.nextInt();
        System.out.print("Enter elements > ");
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = in.nextInt();
        }
        in.close();
        IntStream intStream = AsIntStream.of(ints);
        int res = intStream
                .filter(x -> x > 0)
                .map(x -> x * x)
                .flatMap(x -> AsIntStream.of(x - 1, x, x + 1))
                .reduce(0, (sum, x) -> sum += x);
        System.out.println("Result: " + res);
    }
}
