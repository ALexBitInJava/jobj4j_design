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
        User user1 = new User("Alex", 1, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        User user2 = new User("Alex", 1, new GregorianCalendar(2000, Calendar.JANUARY, 1));
        Map<User, Object> map1 = new HashMap<>(16);
        map1.put(user2, new Object());
        map1.put(user1, new Object());

        int hash1 = (user1.hashCode() ^ (user1.hashCode() >>> 16));
        int hash2 = (user2.hashCode() ^ (user2.hashCode() >>> 16));

        System.out.println("Результат расчёта hash() для user1: " + hash1);
        System.out.println("Результат расчёта hash() для user1: " + hash2);

        int backet1 = hash1 & (16 - 1);
        int backet2 = hash2 & (16 - 1);
        System.out.println("Бакет для user1: " + backet1);
        System.out.println("Бакет для user2: " + backet2);
        for (Map.Entry<User, Object> s : map1.entrySet()) {
            System.out.println(s);
        }
    }

}
