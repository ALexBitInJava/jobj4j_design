package ru.job4j.io.serialization.xml;


import java.util.Arrays;

public class Person2 {
    private boolean sex;
    private int age;
    private Contact contact;
    private String name;
    private String[] property;

    public Person2(boolean sex, int age, Contact contact, String name, String[] property) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.property = property;
    }

    @Override
    public String toString() {
        return "Person2{" + "sex=" + sex + ", age=" + age
                + ", contact=" + contact + ", name='" + name
                + '\'' + ", statuses=" + Arrays.toString(property)
                + '}';
    }
}
