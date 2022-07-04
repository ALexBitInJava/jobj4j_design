package ru.job4j.collection;

import java.util.*;

public class SimpleArrayList<T> implements SimpleList<T> {
    private T[] container;
    private int size;
    private int modCount;

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    public void increase() {
        container = Arrays.copyOf(container, container.length * 2);
    }
    @Override
    public void add(T value) {
        if (size == container.length) {
            increase();
        }
        container[size] = value;
        size++;
        modCount++;
    }

    @Override
    public T set(int index, T newValue) {
        T replacement = get(index);
        container[index] = newValue;
        modCount++;
        return replacement;
    }

    @Override
    public T remove(int index) {
        T removal = get(index);
        System.arraycopy(container, index + 1, container, index,  size - index - 1);
        container[size - 1] = null;
        size--;
        modCount++;
        return removal;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int numberOfElements = 0;
            private final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return numberOfElements < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[numberOfElements++];
            }
        };
    }
}
