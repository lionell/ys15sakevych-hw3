package ua.yandex.shad.function;

import ua.yandex.shad.stream.IntStream;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public interface IntToIntStreamFunction extends IntFunction {
     IntStream applyAsIntStream(int value);
}
