package io.github.lionell;

import ua.yandex.shad.containers.IntList;
import ua.yandex.shad.stream.AsIntStream;

/**
 * Created by lionell on 12/1/15.
 *
 * @author Ruslan Sakevych
 */
public class Sieve {
    public static void main(String[] args) {
        IntList primes = new IntList();
        AsIntStream.of(range(2, 20))
                .filter(x -> {
                    for (int prime : primes) {
                        if (x % prime == 0) {
                            return false;
                        }
                    }
                    primes.add(x);
                    return true;
                })
                .map(x -> x * x)
                .filter(x -> x < 300)
                .forEach(System.out::println);
    }

    public static int[] range(int l, int r) {
        IntList numbers = new IntList();
        for (int i = l; i < r; i++) {
            numbers.add(i);
        }
        return numbers.toArray();
    }
}
