package game.card;

import game.Alliance;
import game.Player;

/**
 * A bear card
 */
public class Squirrel extends Card {

    /**
     * Constructs a new Squirrel
     */
    public Squirrel(Alliance alliance, Player player) {
        super(5, 5, 5, Ability.LAND, alliance, player);
    }
}