package com.zielm.bots;

import static com.zielm.bots.Utils.mod;
import java.util.*;

public class World {
    private Map<Bot, BotAI> ais = new HashMap<Bot, BotAI>();
    private List<Bot> botOrder = new ArrayList<Bot>();
    public final static int w = 8;
    public final static int h = 8;
    public final static int numberOfSteps = 5;

    public static class Bot {
        private int hp = 5;
        private int x, y;
        private int dir = 0;
        public final int id;

        public char getDirectionLetter() {
            switch(dir) {
            case 0:
                return 'v';
            case 1:
                return '>';
            case 2:
                return '^';
            case 3:
                return '<';
            default:
                return '?';
            }
        }

        private void changeDir(int delta) {
            dir += delta;
            dir = mod(dir, 4);
        }

        public Bot(int x, int y, int id) {
            this.x = x;
            this.y = y;
            this.id = id;
            normalize();
        }

        private void normalize() {
            x = mod(x, w);
            y = mod(y, h);
        }

        public int getHp() {
            return hp;
        }

        public boolean isAlive() {
            return getHp() > 0;
        }

        public String toString() {
            return String.format("<Bot at %d pos=(%d,%d) hp=%d>",
                                 System.identityHashCode(this),
                                 x, y, hp);
        }
    }

    public void addBot(BotAI ai, int x, int y) {
        Bot bot = new Bot(x, y, botOrder.size() + 1);
        ais.put(bot, ai);
        botOrder.add(bot);
    }

    public void simulate(Runnable runBetweenSteps) {
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
            runBetweenSteps.run();
        }
    }

    public int getNumberOfBotsAlive() {
        int count = 0;
        for(Bot bot: botOrder) {
            if(bot.isAlive()) count ++;
        }
        return count;
    }

    private void takeAction(Bot bot, BotAction action) {
        //System.err.printf("%s does %s\n", bot, action);
        if(!bot.isAlive()) return;
        switch(action) {
        case SHOOT:
            break;
        case HIT:
            break;
        case FORWARD:
            tryMove(bot, 1);
            break;
        case BACKWARD:
            tryMove(bot, -1);
            break;
        case LEFT:
            bot.changeDir(1);
            break;
        case RIGHT:
            bot.changeDir(-1);
            break;
        }
    }

    public Bot getBotAt(int x, int y) {
        x = mod(x, w);
        y = mod(y, h);
        for(Bot bot : botOrder) {
            if(bot.x == x && bot.y == y)
                return bot;
        }
        return null;
    }

    private void tryMove(Bot bot, int dir) {
        int botDir = bot.dir;
        if(botDir == 2 || botDir == 3) {
            botDir %= 2;
            dir *= -1;
        }
        int x = botDir == 1 ? dir : 0;
        int y = botDir == 0 ? dir : 0;
        tryMoveTo(bot, bot.x + x, bot.y + y);
    }

    private void tryMoveTo(Bot bot, int newX, int newY) {
        if(getBotAt(newX, newY) == null) {
            bot.x = newX;
            bot.y = newY;
            bot.normalize();
        }
    }
}
