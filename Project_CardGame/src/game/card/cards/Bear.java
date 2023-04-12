package game.card.cards;

import game.Alliance;
import game.Player;
import game.card.Ability;
import game.card.Card;

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
