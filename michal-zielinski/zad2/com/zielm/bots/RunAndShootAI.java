package com.zielm.bots;

import java.util.*;

public class RunAndShootAI implements BotAI {
    // this bot is supposed to run away when in range
    public List<BotAction> getNextActions(World.Bot self, World world, int numberOfActions) {
        List<BotAction> out = new ArrayList<BotAction>();

        boolean run = false;
        for(World.Bot bot : world.getBots()) {
            if(bot == self) continue;
            if(Math.abs(bot.getX() - self.getX()) == 0 ||
               Math.abs(bot.getY() - self.getY()) == 0)
                run = true;
        }

        if(run) {
            out.add(BotAction.SHOOT);
            out.add(BotAction.SHOOT);
            out.add(BotAction.FORWARD);
            out.add(BotAction.LEFT);
            out.add(BotAction.FORWARD);
        } else {
            for(int i=0; i<numberOfActions; i++) {
                out.add(BotAction.SHOOT);
            }
        }
        return out;
    }
}
