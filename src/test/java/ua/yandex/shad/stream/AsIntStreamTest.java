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

    @Test
    public void staticOf_withNoInt_shouldReturnEmptyIntStream() {
        IntList expectedInts = new IntList();

        IntStream stream = AsIntStream.of();
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }

    @Test
    public void staticOf_withOneInt_shouldReturnCorrectIntStream() {
        IntList expectedInts = new IntList(1);

        IntStream stream = AsIntStream.of(1);
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }

    @Test
    public void staticOf_withManyInts_shouldReturnCorrectIntStream() {
        IntList expectedInts = new IntList(1, 2, 3);

        IntStream stream = AsIntStream.of(1, 2, 3);
        AsIntStream asStream = (AsIntStream) stream;

        assertEquals(expectedInts, asStream.getInts());
    }
}
