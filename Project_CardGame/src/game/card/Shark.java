package game.card;

import game.Alliance;
import game.Player;

/**
 * A bear card
 */
public class Shark extends Card {

    /**
     * Constructs a new Shark
     */
    public Shark(Alliance alliance) {
        super(5, 5, 5, Ability.WATER, alliance);
    }
}