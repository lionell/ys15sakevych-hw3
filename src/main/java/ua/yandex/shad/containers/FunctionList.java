package ua.yandex.shad.containers;

import ua.yandex.shad.function.IntFunction;

import java.util.Iterator;

/**
 * Created by lionell on 11/30/15.
 *
 * @author Ruslan Sakevych
 */
public class FunctionList implements ListOfFunctions {
    private Node head;
    private Node tail;
    private int size;

    public FunctionList() {
    }

    public void add(IntFunction function) {
        Node node = new Node(function);
        if (head != null) {
            head.next = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<IntFunction> iterator() {
        return new FunctionListIterator();
    }

    private static class Node {
        private IntFunction function;
        private Node next;

        public Node(IntFunction function) {
            this.function = function;
        }
    }

    private class FunctionListIterator implements Iterator<IntFunction> {
        private Node current = tail;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public IntFunction next() {
            IntFunction function = current.function;
            current = current.next;
            return function;
        }
    }
}
