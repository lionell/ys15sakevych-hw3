package ua.yandex.shad.stream;

import org.junit.Before;
import org.junit.Test;
import ua.yandex.shad.containers.FunctionList;
import ua.yandex.shad.containers.IntList;

import static org.junit.Assert.*;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class AsIntStreamTest {
    private static final double EPS = 1e-10;
    private AsIntStream intStream;
    private IntList ints;
    private FunctionList functions;

    @Before
    public void init() {
        intStream = new AsIntStream();
        ints = intStream.getInts();
        functions = intStream.getFunctions();
        ints.add(-1);
        ints.add(1);
        ints.add(0);
        ints.add(-1);
        ints.add(2);
    }

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
    @Test
    public void average_emptyStream_shouldReturnZero() {
        IntStream stream = AsIntStream.of();
        double actualAverage = stream.average();

        assertEquals(0.0, actualAverage, EPS);
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
}
