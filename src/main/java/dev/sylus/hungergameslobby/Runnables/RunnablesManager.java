package dev.sylus.hungergameslobby.Runnables;

import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.game.Scorebord;
import dev.sylus.hungergameslobby.utils.Logging;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class RunnablesManager {
    PregameRunnable pregameRunnable;
    GameRunnable gameRunnable;
    Map<String, Object> runnablesMap = new HashMap<>();
    int gameNumber = 0;

    public RunnablesManager(Game gameInstance, Scorebord scoreboardInstance){
        pregameRunnable = new PregameRunnable();
        runnablesMap.put("pregameRunnable", pregameRunnable);
        gameRunnable = new GameRunnable(gameInstance, scoreboardInstance);
        runnablesMap.put("gameRunnable", gameRunnable);
        Logging.log(Level.INFO, "Initialised the runnables manager");
    }

    public Object getRunnable(String runnableName){
        Logging.log(Level.INFO, "Returned the runnable: " + runnableName);
        return runnablesMap.get(runnableName);
    }
}
