package game.card;

import game.Alliance;
import game.Player;
import game.card.Ability;
import game.card.Card;

/**
 * A bear card
 */
public class Wolf extends Card {

    /**
     * Constructs a new Bear
     */
    public Wolf(Alliance alliance) {
        super(5, 5, 5, Ability.LAND, alliance);
    }
}
