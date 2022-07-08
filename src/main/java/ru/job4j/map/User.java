package ru.job4j.map;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.hash;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
    }

    public static void main(String[] args) {
        User us1 = new User("Alex", 1, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User us2 = new User("Alex", 1, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        Map<User, Object> map1 = new HashMap<>();
        map1.put(us1, new Object());
        map1.put(us2, new Object());
        System.out.println(map1.get(us1).hashCode());
        System.out.println(hash(map1.get(us1)));
        System.out.println(us1.hashCode());

        System.out.println(map1.get(us2).hashCode());
        System.out.println(hash(map1.get(us2)));
        System.out.println(us2.hashCode());

        int i1 = hash(map1.get(us1).hashCode()) & (map1.size() - 1);
        System.out.println(i1);
        int i2 = hash(map1.get(us2).hashCode()) & (map1.size() - 1);
        System.out.println(i2);
        System.out.println("--------------");
        for (Map.Entry<User, Object> s : map1.entrySet()) {
            System.out.println(s);
        }
    }
}
