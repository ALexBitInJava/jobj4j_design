package ru.job4j.io.serialization.json;

import java.util.Arrays;

public class Person2 {
    private final boolean sex;
    private final int age;
    private final Contact contact;
    private final String name;
    private final String[] property;

    public Person2(boolean sex, int age, Contact contact, String name, String[] property) {
        this.sex = sex;
        this.age = age;
        this.contact = contact;
        this.name = name;
        this.property = property;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public Contact getContact() {
        return contact;
    }

    public String getName() {
        return name;
    }

    public String[] getProperty() {
        return property;
    }

    @Override
    public String toString() {
        return "Person2{" + "sex=" + sex + ", age=" + age
                + ", contact=" + contact + ", name='" + name
                + '\'' + ", statuses=" + Arrays.toString(property)
                + '}';
    }
}
