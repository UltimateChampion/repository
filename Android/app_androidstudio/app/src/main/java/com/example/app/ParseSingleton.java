package com.example.app;

import java.util.HashMap;

/**
 * Created by richard on 3/5/14.
 */
public class ParseSingleton {
    private static class Holder {
        private static final ParseSingleton s = new ParseSingleton();
    }

    private HashMap<String, Object> stuff;

    /**
     * Puts an item into the Hashmap stored by ParseSingleton.
     * 
     * @param s The String Key to which the value is mapped to.
     * @param o The Object Value that is mapped to the Key.
     */
    public void put(String s, Object o) {
        stuff.put(s, o);
    }

    /**
     * Getter for a value within the hashmap.
     *
     * @param s The String Key of the hashmap.
     * @return The value stored at the given s.
     */
    public Object get(String s) {
        return stuff.get(s);
    }

    /**
     * Gets the instance of ParseSingleton held by Holder.
     *
     * @return The instance of parseSingleton held by Holder.
     */
    public static ParseSingleton getInstance() {
        return Holder.s;
    }

    /**
     * Constructor for ParseSingleton.
     */
    private ParseSingleton() {
        stuff = new HashMap<String, Object>();
    }
}
