package ru.job4j.generics;

public class User extends Base {
    private final String userName;

    public User(String id, String name) {
        super(id);
        this.userName = name;
    }

    public String getUsername() {
        return userName;
    }
}
