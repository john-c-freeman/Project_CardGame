package game.card;

import game.Alliance;
import game.Player;

public class Cat extends Card {

        /**
        * Constructs a new Cat
        */
        public Cat(Alliance alliance, Player player) {
            super(5, 5, 5, Ability.LAND, alliance, player);
        }
}
