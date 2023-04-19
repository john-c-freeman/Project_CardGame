package game.card;

import game.Alliance;

public class Hound extends Card{

    public Hound(Alliance alliance){
        super(3, 3, 3, Ability.LAND, alliance);
    }
}
