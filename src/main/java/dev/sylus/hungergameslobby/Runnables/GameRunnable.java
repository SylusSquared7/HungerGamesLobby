package dev.sylus.hungergameslobby.Runnables;

import dev.sylus.hungergameslobby.game.Game;
import dev.sylus.hungergameslobby.game.Scorebord;
import org.bukkit.scheduler.BukkitRunnable;

public class GameRunnable extends BukkitRunnable {
    int timerCountdown = 600;
    Game game;
    Scorebord scorebord;
    public GameRunnable(Game gameInstance, Scorebord scoreboardInstance){
        game = gameInstance;
        scorebord = scoreboardInstance;

    }

    @Override
    public void run() {
        if (timerCountdown == 0){
            cancel();
            return;
        }
        timerCountdown--;
    }

    public int getTimeLeft(){
        return timerCountdown;
    }
}
