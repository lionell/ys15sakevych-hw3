package ua.yandex.shad.containers;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public interface ListOfInts extends Iterable<Integer> {
    void add(int value);
    void addList(IntList other);
    void clear();
    int length();
    boolean isEmpty();
    int[] toArray();
}
