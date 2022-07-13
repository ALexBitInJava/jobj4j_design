package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {
    public static Info diff(Set<User> previous, Set<User> current) {
        int add = 0;
        int change = 0;
        int delete = 0;
        Map<Integer, String> previousMap = new HashMap<>();
        Map<Integer, String> currentMap = new HashMap<>();

        for (User user : current) {
            currentMap.put(user.getId(), user.getName());
        }
        for (User user : previous) {
            previousMap.put(user.getId(), user.getName());
        }
        for (Integer key: currentMap.keySet()) {
            if (!previousMap.containsKey(key)) {
                add++;
            }
        }
        for (Integer key : previousMap.keySet()) {
            if (!currentMap.containsKey(key)) {
                delete++;
            } else if (!currentMap.get(key).equals(previousMap.get(key))) {
                change++;
            }

        }
        return new Info(add, change, delete);
    }
}
