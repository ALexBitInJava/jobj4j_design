package ru.job4j.map;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutReturnTrue() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        assertTrue(simpleMap.put(1, "Tom"));
        assertThat(simpleMap.get(1), is("Tom"));
    }

    @Test
    public void whenPutAndDoNotChange() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        simpleMap.put(1, "Tom");
        assertFalse(simpleMap.put(1, "Jon"));
        assertThat(simpleMap.get(1), is("Tom"));
    }

    @Test
    public void whenNotExistGetReturnNull() {
    SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
    stringSimpleMap.put(1, "Tom");
    stringSimpleMap.put(3, "Jon");
    assertNull(stringSimpleMap.get(2));
    }

    @Test
    public void whenNotExistGetReturnValue() {
        SimpleMap<Integer, String> stringSimpleMap = new SimpleMap<>();
        stringSimpleMap.put(1, "Tom");
        stringSimpleMap.put(2, "Jon");
        assertThat(stringSimpleMap.get(1), is("Tom"));
    }

    @Test
    public void whenRemoveValue() {
        SimpleMap<Integer, String> test1 = new SimpleMap<>();
        test1.put(1, "Tom");
        test1.put(2, "Bob");
        test1.put(3, "Jon");
        assertTrue(test1.remove(1));
    }

    @Test
    public void whenDoNotRemoveValue() {
        SimpleMap<Integer, String> test1 = new SimpleMap<>();
        test1.put(1, "Tom");
        test1.put(2, "Bob");
        test1.put(3, "Jon");
        assertFalse(test1.remove(4));
    }

    @Test
    public void whenHasNextValue() {
        SimpleMap<Integer, String> test1 = new SimpleMap<>();
        test1.put(1, "Tom");
        test1.put(2, "Bob");
        Iterator<Integer> iterator = test1.iterator();
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void whenNoNextValue() {
        SimpleMap<Integer, String> test1 = new SimpleMap<>();
        test1.put(1, "Tom");
        Iterator<Integer> iterator = test1.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenAfterRemoveGetConcurrentModificationException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();
        Iterator<Integer> iterator = simpleMap.iterator();
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenNextReturnConcurrentModificationException() {
        SimpleMap<Integer, String> simpleMap = new SimpleMap<>();

        Iterator<Integer> iterator = simpleMap.iterator();
        simpleMap.put(1, "Tom");
        simpleMap.remove(1);
        iterator.next();
    }
}