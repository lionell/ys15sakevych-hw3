package ua.yandex.shad.containers;

import ua.yandex.shad.function.IntFunction;

/**
 * Created by lionell on 11/30/15.
 *
 * @author Ruslan Sakevych
 */
public interface ListOfFunctions extends Iterable<IntFunction> {
    
    void add(IntFunction function);

    void clear();

    int length();

    boolean isEmpty();
}
