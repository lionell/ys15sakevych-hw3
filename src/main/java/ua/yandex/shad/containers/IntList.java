package ua.yandex.shad.containers;

import java.util.Iterator;

/**
 * Created by lionell on 11/29/15.
 *
 * @author Ruslan Sakevych
 */
public class IntList implements ListOfInts {
    private int size;
    private Node head;
    private Node tail;

    public IntList(int... values) {
        for (int value : values) {
            add(value);
        }
    }

    public void add(int value) {
        Node node = new Node(value);
        if (head != null) {
            head.next = node;
        }
        head = node;
        if (tail == null) {
            tail = head;
        }
        size++;
    }

    public void addList(IntList other) {
        if (isEmpty()) {
            tail = other.tail;

        } else {
            head.next = other.tail;
        }
        head = other.head;
        other.clear();
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int length() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IntListIterator();
    }

    private static class Node {
        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }

    }

    private class IntListIterator implements Iterator<Integer> {
        private Node current = tail;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Integer next() {
            int value = current.value;
            current = current.next;
            return value;
        }

    }
}
