package com.zielm.bots;

public class Main {
    public static void main(String[] args) {
        World world = new World();
        world.addBot(new RandomAI(), 5, 5);
        world.addBot(new RandomAI(), 15, 5);
        simulate(world);
    }

    public static void simulate(World world) {
        while(world.getNumberOfBotsAlive() > 1) {
            world.simulate();
            Utils.sleep(0.1);
        }
    }

}
