package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        if (table[i] == null) {
            table[i] = new MapEntry<>(key, value);
            rsl = true;
        }
        return rsl;
    }

    private int hash(int hashCode) {
        return hashCode ^ hashCode >>> 16;
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        int i = indexFor(hash(key.hashCode()));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            table[i].key = null;
            table[i].value = null;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int counter = 0;
            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                int c = counter;
                while (c < capacity - 1 && table[c] == null) {
                    c++;
                    counter = c;
                }
                return c < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[counter++].key;
            }
        };
    }

    @Override
    public V get(K key) {
        int i  = indexFor(hash(key.hashCode()));
        if (table[i] != null && Objects.equals(table[i].key, key)) {
            return table[i].value;
        }
        return null;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        MapEntry<K, V>[] table2 = table;
        int cap = capacity * 2;
        table2 = new MapEntry[cap];
        int i = 0;
        while (i < capacity) {
            if (table2[i] != null) {
                put(table2[i].key, table2[i].value);
            }
            i++;
        }
    }
}