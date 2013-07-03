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

    public static int sign(int v) {
        return v == 0 ? 0 : (v < 0 ? -1 : 1);
    }

    public static boolean sameSign(int a, int b) {
        return sign(a) == sign(b);
    }
}
