package game.card;

import game.Alliance;
import game.Player;

public class Crow extends Card {

    /**
     * Constructs a new Crow
     */
    public Crow(Alliance alliance, Player player) {
        super(5, 5, 5, Ability.AIR, alliance, player);
    }
}
