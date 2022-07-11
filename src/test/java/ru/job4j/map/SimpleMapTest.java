package ru.job4j.map;

import junit.framework.TestCase;
import org.junit.Test;

import java.util.Iterator;

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
    public void whenExistToFalse() {
        SimpleMap<Integer, String> test1 = new SimpleMap<>();
        test1.put(1, "Tom");
        test1.put(2, "Bob");
        test1.put(3, "Jon");
        assertTrue(test1.remove(1));

    }

    @Test
    public void testIterator() {
    }
}