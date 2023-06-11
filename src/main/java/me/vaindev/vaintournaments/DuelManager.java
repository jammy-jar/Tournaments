package me.vaindev.vaintournaments;

import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.HashMap;

public class DuelManager {

    Player player1;
    Player player2;

    public Player[] getCurrentDuel() {
        if (this.player1 == null || this.player2 == null)
            return null;
        return new Player[]{this.player1, this.player2};
    }

    public void setCurrentDuel(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
