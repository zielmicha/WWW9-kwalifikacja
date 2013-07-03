package com.zielm.bots;

import java.util.*;

public interface BotAI {
    List<BotAction> getNextActions(World.Bot self, World world, int numberOfActions);
}
