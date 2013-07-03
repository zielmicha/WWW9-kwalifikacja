package com.zielm.bots;

public class Utils {
    public static int mod(int x, int mod) {
        return ((x % mod) + mod) % mod;
    }

    public static void sleep(double seconds) {
        try {
            Thread.sleep((int)(seconds * 1000));
        } catch(InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }
}
