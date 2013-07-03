package com.zielm.bots;

import java.util.*;

public class FollowAndShootAI implements BotAI {
    /**
     * This bot sit in place shooting and hitting until
     * it sees an opportunity to shoot enemy just by turing around.
     */
    public List<BotAction> getNextActions(World.Bot self, World world, int numberOfActions) {
        List<BotAction> out = new ArrayList<BotAction>();

        World.Bot otherBot = null;
        for(World.Bot bot : world.getBots()) {
            if(bot == self) continue;
            otherBot = bot;
        }

        int dx = otherBot.getX() - self.getX(),
            dy = otherBot.getY() - self.getY();

        if(dx == 0 || dy == 0) {
            int dir = self.getDir();
            int turnDir = Utils.sign(Vector.fromDirection(dir).cross(new Vector(dx, dy)));
            while(Vector.fromDirection(dir).cross(new Vector(dx, dy)) != 0) {
                if(turnDir == -1) {
                    out.add(BotAction.LEFT);
                    dir ++;
                } else {
                    out.add(BotAction.RIGHT);
                    dir --;
                }
            }
            for(int i=0; i<numberOfActions; i++) {
                out.add(BotAction.SHOOT);
            }
        } else if(Math.abs(dx) == 1 && Math.abs(dy) == 1) {
            // this doesn't really help, but whatever
            for(int i=0; i<numberOfActions; i++) {
                out.add(BotAction.HIT);
                out.add(BotAction.LEFT);
            }
        } else {
            for(int i=0; i<numberOfActions; i++) {
                out.add(BotAction.SHOOT);
                out.add(BotAction.HIT);
            }
        }

        return out;
    }

}
