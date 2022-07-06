package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

public class ListUtils {

    public static <T> void addBeforeSecondMethod(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index);
        iterator.add(value);
    }

    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void addAfterSecondMethod(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator(index + 1);
        iterator.add(value);
    }

    public static <T> void addAfter(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.next();
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (filter.test(iterator.next())) {
                iterator.set(value);
            }
        }
    }

    public static <T> void removeAll(List<T> list, List<T> elements) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (elements.contains(iterator.next())) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5,
                6, 7, 8, 9, 10));
        addAfterSecondMethod(list, list.size() - 1, 11);
        System.out.println(list);
        System.out.println("-----------------");
        addBeforeSecondMethod(list, 0, -1);
        System.out.println(list);
        System.out.println("-----------------");
        Predicate<Integer> predicate = p -> p / 5 > 0;
        removeIf(list, predicate);
        System.out.println(list);
        System.out.println("-----------------");
        List<Integer> list1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5,
                6, 7, 8, 9, 10));
        Predicate<Integer> predicate1 = p -> p % 5 == 0;
        replaceIf(list1, predicate1, 15);
        System.out.println(list1);
        System.out.println("-----------------");
        List<Integer> list2 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5,
                6, 7, 8, 9, 10));
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 5, 9));
        removeAll(list2, list3);
        System.out.println(list2);
    }
}
