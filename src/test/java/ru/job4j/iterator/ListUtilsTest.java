package ru.job4j.iterator;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBeforeSecondMethod() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBeforeSecondMethod(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeSecondMethodWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 4, 3));
        ListUtils.addBeforeSecondMethod(input, 5, 10);
    }

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLastSecondMethod() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfterSecondMethod(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test
    public void whenRemoveIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 8, 10));
        Predicate<Integer> predicate = p -> p < 5;
        ListUtils.removeIf(input, predicate);
        assertThat(input, is(Arrays.asList(6, 8, 10)));
    }

    @Test
    public void whenReplaceIfPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 8, 12));
        Predicate<Integer> predicate = p -> p < 5 || p % 3 == 0;
        ListUtils.replaceIf(input, predicate, 99);
        assertThat(input, is(Arrays.asList(99, 99, 99, 99, 8, 99)));
    }

    @Test
    public void whenRemoveAllPredicate() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 6, 8, 12));
        List<Integer> elements = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList(0, 1, 2, 12)));
    }
}