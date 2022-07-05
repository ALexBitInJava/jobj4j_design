package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int changesIn = 0;
    private int changesOut = 0;

    public T poll() {
        if (changesIn == 0 && changesOut == 0) {
            throw new NoSuchElementException();
        }
        if (changesOut == 0) {
            while (changesIn != 0) {
                out.push(in.pop());
                changesIn--;
                changesOut++;
            }
            changesOut--;
        }
        return out.pop();
    }
    public void push(T value) {
        in.push(value);
        changesIn++;
    }
}
