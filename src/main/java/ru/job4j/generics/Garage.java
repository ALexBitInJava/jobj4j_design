package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public class Garage {
    public static void main(String[] args) {
        List<Integer> garage = new ArrayList<>(100);
        garage.add(1);
        garage.add(2);
        System.out.println(garage.size());
    }
}
