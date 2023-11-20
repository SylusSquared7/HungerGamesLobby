package dev.sylus.hungergameslobby.Runnables;

import dev.sylus.hungergameslobby.game.Game;

public class RunnablesManager {
    PregameRunnable pregameRunnable;
    int gameNumber = 0;

    public RunnablesManager(Game gameInstance){
        pregameRunnable = new PregameRunnable();
    }
}
