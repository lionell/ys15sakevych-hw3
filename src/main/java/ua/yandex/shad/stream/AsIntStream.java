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
    private State state = State.OPENED;

    AsIntStream() {
    }

    public static IntStream of(int... values) {
        AsIntStream intStream = new AsIntStream();
        intStream.ints = new IntList(values);
        return intStream;
    }

    private void applyModifiers() {
        checkState();
        for (IntFunction function : functions) {
            if (function instanceof IntPredicate) {
                applyFilter((IntPredicate) function);
            } else if (function instanceof IntUnaryOperator) {
                applyMap((IntUnaryOperator) function);
            } else {
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

    private void close() {
        state = State.CLOSED;
    }

    private void checkState() {
        if (state == State.CLOSED) {
            throw new IllegalStateException();
        }
    }

    private void checkEmptiness() {
        if (ints.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double average() {
        applyModifiers();
        checkEmptiness();
        return (double) sum() / count();
    }

    @Override
    public int max() {
        applyModifiers();
        checkEmptiness();
        int maximum = Integer.MIN_VALUE;
        for (int x : ints) {
            if (x > maximum) {
                maximum = x;
            }
        }
        return maximum;
    }

    @Override
    public int min() {
        applyModifiers();
        checkEmptiness();
        int minimum = Integer.MAX_VALUE;
        for (int x : ints) {
            if (x < minimum) {
                minimum = x;
            }
        }
        return minimum;
    }

    @Override
    public int count() {
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
        close();
    }

    @Override
    public IntStream map(IntUnaryOperator mapper) {
        functions.add(mapper);
        return this;
    }

    @Override
    public int reduce(int identity, IntBinaryOperator operator) {
        applyModifiers();
        int result = identity;
        for (int x : ints) {
            result = operator.apply(result, x);
        }
        return result;
    }

    @Override
    public int sum() {
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
        applyModifiers();
        return ints.hashCode();
    }

    IntList getInts() {
        return ints;
    }

    private enum State {
        OPENED, CLOSED
    }
}