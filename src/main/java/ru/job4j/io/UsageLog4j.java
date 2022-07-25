package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Petr Arsentev";
        int age = 33;
        LOG.debug("User info name : {}, age : {}", name, age);

        short s = 100;
        byte b = 10;
        long l = 100000L;
        float f = 1000.3F;
        double d = 124.32D;
        char c = 'o';
        boolean rsl = false;
        int i = 10000;
        LOG.debug("Info about primitive variables : s = {}, b = {}, l = {}, f = {}, d = {}, c = {}, rsl = {}, i = {} ",
                s, b, l, f, d, c, rsl, i);

        try {
            throw new Exception("Not supported code");
        } catch (Exception e) {
            LOG.error("Exception in log example", e);
        }
    }
}
