package com.order.phoenix.tracker.utils;

/**
 * Created on 4/19/14.
 */
public class Extensions {

    public static <K, V> boolean equals(K first, V second) {
        if (first == null) {
            return second == null;
        }
        if (second == null) {
            return false;
        }
        return first.equals(second);
    }
}
