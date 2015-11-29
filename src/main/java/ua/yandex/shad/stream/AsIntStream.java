package ua.yandex.shad.stream;

import ua.yandex.shad.containers.FunctionList;
import ua.yandex.shad.containers.IntList;
import ua.yandex.shad.function.IntUnaryOperator;
import ua.yandex.shad.function.IntToIntStreamFunction;
import ua.yandex.shad.function.IntPredicate;
import ua.yandex.shad.function.IntConsumer;
import ua.yandex.shad.function.IntBinaryOperator;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class AsIntStream implements IntStream {
    private IntList ints = new IntList();
    private FunctionList functions = new FunctionList();

    private AsIntStream() {
    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        intStream.ints = new IntList(values);
        return intStream;
    }

    @Override
    public Double average() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer max() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer min() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void forEach(IntConsumer action) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int reduce(int identity, IntBinaryOperator op) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Integer sum() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction func) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}