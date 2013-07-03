package com.zielm.bots;

import java.util.*;

public class RandomAI implements BotAI {
    private Random rand = new Random(13);

    public List<BotAction> getNextActions(World.Bot self, World world, int numberOfActions) {
        List<BotAction> out = new ArrayList<BotAction>();
        for(int i=0; i<numberOfActions; i++) {
            out.add(BotAction.values()[rand.nextInt(6)]);
        }
        return out;
    }
}
