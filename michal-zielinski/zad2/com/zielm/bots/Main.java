package com.zielm.bots;

public class Main {
    public static void main(String[] args) {
        testAI(new RandomAI(191));
        testAI(new AlwaysShootAI());
        testAI(new RunAndShootAI());
    }

    public static void testAI(BotAI ai) {
        int sum = 0;
        final int nIter = 100;
        for(int i=0; i<nIter; i++)
            sum += getScoreForSeed(i, ai);
        float score = sum / (float)(nIter);
        System.out.printf("%s score: %d%%\n", ai.getClass().getName(), (int)(score * 100));
    }

    public static float getScoreForSeed(int seed, BotAI ai) {
        World world = new World();
        world.addBot(new RandomAI(seed), 5, 5);
        world.addBot(ai, 1, 0);
        return getScore(world);
    }

    public static float getScore(final World world) {
        int roundCount = 0;
        while(world.getNumberOfBotsAlive() > 1) {
            roundCount ++;
            world.simulate(null);
            if(roundCount > 50000) // end game without winner
                return 0;
        }
        World.Bot alive = world.getAliveBot();
        if(alive.id == 2)
            return alive.getHp() / 5;
        else
            return -alive.getHp() / 5;
    }
}
