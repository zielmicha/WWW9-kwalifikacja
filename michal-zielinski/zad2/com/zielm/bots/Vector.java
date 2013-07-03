package com.zielm.bots;

public class Vector {
    public final int x, y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int cross(Vector other) {
        return this.x * other.y - this.y * other.x;
    }

    public static Vector fromDirection(int dir) {
        int mul = 1;
        if(dir == 2 || dir == 3) {
            dir %= 2;
            mul *= -1;
        }
        int x = dir == 1 ? mul : 0;
        int y = dir == 0 ? mul : 0;
        return new Vector(x, y);
    }
}
