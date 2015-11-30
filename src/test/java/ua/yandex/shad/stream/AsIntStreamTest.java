package ua.yandex.shad.stream;

import org.junit.Test;
import ua.yandex.shad.containers.IntList;

import static org.junit.Assert.*;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class AsIntStreamTest {
    private static final double EPS = 1e-10;

    //<editor-fold desc="static of">
    @Test
    public void staticOf_withNoParameter_shouldReturnEmptyStream() {
        IntList expectedInts = new IntList();

        IntStream stream = AsIntStream.of();
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }

    @Test
    public void staticOf_withOneParameter_shouldReturnCorrectStream() {
        IntList expectedInts = new IntList(1);

        IntStream stream = AsIntStream.of(1);
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }

    @Test
    public void staticOf_withManyParameters_shouldReturnCorrectStream() {
        IntList expectedInts = new IntList(1, 2, 3);

        IntStream stream = AsIntStream.of(1, 2, 3);
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }
    //</editor-fold>

    //<editor-fold desc="average">
    @Test(expected = IllegalArgumentException.class)
    public void average_emptyStream_shouldThrowException() {
        AsIntStream.of().average();
    }

    @Test
    public void average_streamWithOneElement_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(5);

        double actualAverage = stream.average();

        assertEquals(5.0, actualAverage, EPS);
    }

    @Test
    public void average_streamWithManyElements_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(3, 1, 1, 2, 3, 2);

        double actualAverage = stream.average();

        assertEquals(2.0, actualAverage, EPS);
    }
    //</editor-fold>

    //<editor-fold desc="max">
    @Test(expected = IllegalArgumentException.class)
    public void max_emptyStream_shouldThrowException() {
        AsIntStream.of().max();
    }

    @Test
    public void max_streamWithOneElement_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(-5);

        int actualMax = stream.max();

        assertEquals(-5, actualMax);
    }

    @Test
    public void max_streamWithManyElements_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(13, 10, -1, 2, 33, 2);

        int actualMax = stream.max();

        assertEquals(33, actualMax);
    }
    //</editor-fold>

    //<editor-fold desc="min">
    @Test(expected = IllegalArgumentException.class)
    public void min_emptyStream_shouldThrowException() {
        AsIntStream.of().min();
    }

    @Test
    public void min_streamWithOneElement_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(-1);

        int actualMin = stream.min();

        assertEquals(-1, actualMin);
    }

    @Test
    public void min_streamWithManyElements_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(-314, -1, 1, -2, 3, -2);

        int actualMin = stream.min();

        assertEquals(-314, actualMin);
    }
    //</editor-fold>

    //<editor-fold desc="count">
    @Test
    public void count_emptyStream_shouldReturnZero() {
        IntStream stream = AsIntStream.of();

        int actualCount = stream.count();

        assertEquals(0, actualCount);
    }

    @Test
    public void count_streamWithOneElement_shouldReturnOne() {
        IntStream stream = AsIntStream.of(-100);

        int actualCount = stream.count();

        assertEquals(1, actualCount);
    }

    @Test
    public void count_streamWithManyElements_shouldReturnCorrectValue() {
        IntStream stream = AsIntStream.of(-314, -1, 1, -2, 3, -2, 7, 9);

        int actualCount = stream.count();

        assertEquals(8, actualCount);
    }
    //</editor-fold>

    //<editor-fold desc="filter">
    @Test
    public void filter_emptyStream_streamShouldNotChange() {
        IntStream stream = AsIntStream.of();

        stream.filter(x -> x > 0);

        assertEquals(AsIntStream.of(), stream);
    }

    @Test
    public void filter_streamWithManyElementsAndTotallyNegativePredicate_streamShouldBeEmpty() {
        IntStream stream = AsIntStream.of(-1, -4, 2, 0, -1);

        stream.filter(x -> false);

        assertEquals(AsIntStream.of(), stream);
    }

    @Test
    public void filter_streamWithManyElementsAndTotallyPositivePredicate_streamShouldNotChange() {
        IntStream stream = AsIntStream.of(-1, -4, 2, 0, -1);

        stream.filter(x -> true);

        assertEquals(AsIntStream.of(-1, -4, 2, 0, -1), stream);
    }

    @Test
    public void filter_streamWithManyElementsAndNotNegativePredicate_streamShouldBeCorrect() {
        IntStream stream = AsIntStream.of(-1, -4, 2, 0, -1);

        stream.filter(x -> x >= 0);

        assertEquals(AsIntStream.of(2, 0), stream);
    }
    //</editor-fold>

    //<editor-fold desc="forEach">
    // All tests connected with forEach are using IntList::add method reference
    @Test
    public void forEach_emptyStream_listShouldBeEmpty() {
        IntStream stream = AsIntStream.of();
        IntList objective = new IntList();

        stream.forEach(objective::add);

        assertEquals(new IntList(), objective);
    }

    @Test
    public void forEach_streamWithOneElement_listShouldContainsCorrectElement() {
        IntStream stream = AsIntStream.of(45);
        IntList objective = new IntList();

        stream.forEach(objective::add);

        assertEquals(new IntList(45), objective);
    }

    @Test
    public void forEach_streamWithManyElements_listShouldContainsCorrectElements() {
        IntStream stream = AsIntStream.of(45, 1, 25);
        IntList objective = new IntList();

        stream.forEach(objective::add);

        assertEquals(new IntList(45, 1, 25), objective);
    }
    //</editor-fold>
}
