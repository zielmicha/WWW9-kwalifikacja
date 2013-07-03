package com.zielm.bots;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.addBot(new RandomAI(), 5, 5);
        world.addBot(new RandomAI(), 1, 0);
        simulate(world);
    }

    public static void simulate(final World world) {
        while(world.getNumberOfBotsAlive() > 1) {
            world.simulate(new Runnable() {
                    public void run() {
                        printWorld(world);
                    }
                });
            printWorld(world);
            Utils.sleep(0.3);
        }
    }

    public static void printWorld(World world) {
        for(int y=0; y < world.h; y++) {
            for(int x=0; x < world.w; x++) {
                char c = 'X';
                char d = ' ';
                World.Bot bot = world.getBotAt(x, y);
                if(bot != null) {
                    c = (char)((int)'A' + bot.id - 1);
                    d = bot.getDirectionLetter();
                }
                System.out.print(" " + c + "" + d);
            }
            System.out.println();
        }
        System.out.println();
    }
}
