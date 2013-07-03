package com.zielm.bots;

import java.util.*;

public class AlwaysShootAI implements BotAI {
    public List<BotAction> getNextActions(World.Bot self, World world, int numberOfActions) {
        List<BotAction> out = new ArrayList<BotAction>();
        for(int i=0; i<numberOfActions; i++) {
            out.add(BotAction.SHOOT);
        }
        return out;
    }
}
