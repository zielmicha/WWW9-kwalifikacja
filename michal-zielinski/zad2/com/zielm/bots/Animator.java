package com.zielm.bots;

public class Animator {
    public static void main(String[] args) {
        World world = new World();
        world.addBot(new RandomAI(8), 5, 5);
        world.addBot(new MyAI(13), 1, 0);
        simulate(world);
    }

    public static void simulate(final World world) {
        int roundCount = 0;
        while(world.getNumberOfBotsAlive() > 1) {
            roundCount ++;
            world.simulate(new Runnable() {
                    public void run() {
                        printWorld(world);
                    }
                });
            printWorld(world);
            Utils.sleep(0.02);
        }
        World.Bot alive = world.getAliveBot();
        System.out.printf("after %d rounds ", roundCount);
        if(alive != null) {
            System.out.println(alive + " wins!");
        } else {
            System.out.println("draw!");
        }
    }

    public static void printWorld(World world) {
        for(int y=0; y < world.h; y++) {
            for(int x=0; x < world.w; x++) {
                char c = 'X';
                char d = ' ';
                World.Bot bot = world.getBotAt(x, y);
                if(bot != null) {
                    c = bot.getIdLetter();
                    d = bot.getDirectionLetter();
                }
                System.out.print(" " + c + "" + d);
            }
            System.out.println();
        }
        System.out.println();
    }
}
