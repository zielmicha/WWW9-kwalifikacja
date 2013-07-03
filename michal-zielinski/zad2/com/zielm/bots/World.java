package com.zielm.bots;

import static com.zielm.bots.Utils.mod;
import java.util.*;

public class World {
    private Map<Bot, BotAI> ais = new HashMap<Bot, BotAI>();
    private List<Bot> botOrder = new ArrayList<Bot>();
    public final int w = 8;
    public final int h = 8;
    public final int numberOfSteps = 5;

    public static class Bot {
        private int hp = 5;
        private int x, y;

        public Bot(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getHp() {
            return hp;
        }

        public boolean isAlive() {
            return getHp() > 0;
        }
    }

    public void addBot(BotAI ai, int x, int y) {
        Bot bot = new Bot(x, y);
        ais.put(bot, ai);
        botOrder.add(bot);
    }

    public void simulate() {
        HashMap<Bot, LinkedList<BotAction>> expectedActions = new HashMap<Bot, LinkedList<BotAction>>();
        for(Map.Entry<Bot, BotAI> e: ais.entrySet()) {
            Bot bot = e.getKey();
            BotAI ai = e.getValue();
            expectedActions.put(bot, new LinkedList(ai.getNextActions(bot, this, numberOfSteps)));
        }
        for(int i=0; i<numberOfSteps; i++) {
            for(Bot bot: botOrder) {
                BotAction actionToTake = expectedActions.get(bot).removeFirst();
                takeAction(bot, actionToTake);
            }
        }
    }

    public int getNumberOfBotsAlive() {
        int count = 0;
        for(Bot bot: botOrder) {
            if(bot.isAlive()) count ++;
        }
        return count;
    }

    public void takeAction(Bot bot, BotAction action) {
        System.err.printf("%s does %s\n", bot, action);
        if(!bot.isAlive()) return;
        switch(action) {
        case SHOOT:
            break;
        case HIT:
            break;
        case FORWARD:
            break;
        case BACKWARD:
            break;
        case LEFT:
            break;
        case RIGHT:
            break;
        }
    }
}
