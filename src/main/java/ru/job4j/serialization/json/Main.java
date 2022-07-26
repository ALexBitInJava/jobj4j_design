package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Person person1 = new Person(false, 30, new Contact("11 - 111"),
                new String[]{"Worker, Married"});
        final Gson gson1 = new GsonBuilder().create();
        System.out.println(gson1.toJson(person1));

        final String personJson1 = "{"
                + "\"sex\":false,"
                + "\"age\":35,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"+7(924)111-111-11-11\""
                + "},"
                + "\"statuses\":"
                + "[\"Student\",\"Free\"]"
                + "}";
        final Person personMod1 = gson1.fromJson(personJson1, Person.class);
        System.out.println(personMod1);

        final Person2 person2 = new Person2(true, 27, new Contact("88-999"),
                 "Bob", new String[] {"Car, House, Flat"});
        final Gson gson2 = new GsonBuilder().create();
        System.out.println(gson2.toJson(person2));

        final String personJson2 = "{"
                + "\"sex\":true,"
                + "\"age\":30,"
                + "\"contact\":"
                + "{"
                + "\"phone\":\"77-888\""
                + "},"
                + "\"name\": Tom,"
                + "\"property\":"
                + "[\"Bike\",\"House\"]"
                + "}";
        final Person2 personMod2 = gson2.fromJson(personJson2, Person2.class);
        System.out.println(personMod2);
    }
}
