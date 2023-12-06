package dev.sylus.hungergameslobby.Runnables;

import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.game.Scorebord;

import java.util.HashMap;
import java.util.Map;

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
    }

    public Object getRunnable(String runnableName){
        return runnablesMap.get(runnableName);
    }
}
