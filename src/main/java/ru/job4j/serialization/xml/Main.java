package ru.job4j.serialization.xml;

public class Main {
    public static void main(String[] args) {
        final Person person1 = new Person(false, 30, new Contact("11 - 111"),
                new String[]{"Worker, Married"});
        System.out.println(person1);

        final Person2 person2 = new Person2(true, 27, new Contact("88-999"),
                 "Bob", new String[] {"Car, House, Flat"});
        System.out.println(person2);
    }
}
