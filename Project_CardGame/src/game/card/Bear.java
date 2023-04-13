package game.card;

import game.Alliance;
import game.Player;

/**
 * A bear card
 */
public class Bear extends Card {

    /**
     * Constructs a new Bear
     */
    public Bear(Alliance alliance, Player player) {
        super(5, 5, 5, Ability.LAND, alliance, player);
    }
}
