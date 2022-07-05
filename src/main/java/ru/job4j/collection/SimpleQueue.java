package ru.job4j.collection;

import java.util.NoSuchElementException;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        if (in == null) {
            throw new NoSuchElementException();
        }
        while (out != null) {
            out.push(in.pop());
            break;
        }
        return out.pop();
    }
    public void push(T value) {
        in.push(value);
    }
}
