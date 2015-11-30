package ua.yandex.shad.stream;

import ua.yandex.shad.containers.FunctionList;
import ua.yandex.shad.containers.IntList;
import ua.yandex.shad.function.IntFunction;
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

    AsIntStream() {
    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        intStream.ints = new IntList(values);
        return intStream;
    }

    private void applyModifiers() {
        for (IntFunction function : functions) {
            if (function instanceof IntPredicate) {
                applyFilter((IntPredicate) function);
            } else if (function instanceof IntUnaryOperator) {
                applyMap((IntUnaryOperator) function);
            } else if (function instanceof IntToIntStreamFunction) {
                applyFlatMap((IntToIntStreamFunction) function);
            }
        }
        functions.clear();
    }

    private void applyFilter(IntPredicate predicate) {
        IntList filteredInts = new IntList();
        for (int x : ints) {
            if (predicate.test(x)) {
                filteredInts.add(x);
            }
        }
        ints = filteredInts;
    }

    private void applyMap(IntUnaryOperator mapper) {
        IntList mappedInts = new IntList();
        for (int x : ints) {
            mappedInts.add(mapper.apply(x));
        }
        ints = mappedInts;
    }

    private void applyFlatMap(IntToIntStreamFunction function) {
        IntList flatMappedInts = new IntList();
        for (int x : ints) {
            AsIntStream generatedIntStream =
                    (AsIntStream) function.applyAsIntStream(x);
            flatMappedInts.addList(generatedIntStream.ints);
        }
        ints = flatMappedInts;
    }

    @Override
    public Double average() {
        return (double) sum() / count();
    }

    @Override
    public Integer max() {
        applyModifiers();
        int maximum = Integer.MIN_VALUE;
        for (int x : ints) {
            if (x > maximum) {
                maximum = x;
            }
        }
        return maximum;
    }

    @Override
    public Integer min() {
        applyModifiers();
        int minimum = Integer.MAX_VALUE;
        for (int x : ints) {
            if (x < minimum) {
                minimum = x;
            }
        }
        return minimum;
    }

    @Override
    public long count() {
        applyModifiers();
        return ints.length();
    }

    @Override
    public IntStream filter(IntPredicate predicate) {
        functions.add(predicate);
        return this;
    }

    @Override
    public void forEach(IntConsumer action) {
        applyModifiers();
        for (int x : ints) {
            action.accept(x);
        }
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        functions.add(mapper);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator operator) {
        applyModifiers();
        for (int x : ints) {
            identity = operator.apply(identity, x);
        }
        return identity;
    }

    @Override
    public Integer sum() {
        applyModifiers();
        int sum = 0;
        for (int x : ints) {
            sum += x;
        }
        return sum;
    }

    @Override
    public int[] toArray() {
        applyModifiers();
        return ints.toArray();
    }

    @Override
    public IntStream flatMap(IntToIntStreamFunction function) {
        functions.add(function);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AsIntStream intStream = (AsIntStream) o;

        applyModifiers();
        intStream.applyModifiers();

        return ints.equals(intStream.ints);
    }

    @Override
    public int hashCode() {
        return ints.hashCode();
    }

    IntList getInts() {
        return ints;
    }

    FunctionList getFunctions() {
        return functions;
    }
}