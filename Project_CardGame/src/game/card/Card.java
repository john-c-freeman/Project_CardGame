package game.card;

import game.Alliance;
import game.board.Board;

public abstract class Card {
    private int power;
    private int health;
    private int mana;
    private Ability ability;
    private Alliance alliance;
    private int cardLocation;
    private final Board board;

    /**
     * Constructs a new card
     * @param power The card's power
     * @param health The card's health
     * @param mana The card's mana
     * @param ability The type ability
     * @param alliance The card's alliance
     */
    public Card(int power, int health, int mana, Ability ability, Alliance alliance){
        this.power = power;
        this.health = health;
        this.mana = mana;
        this.ability = ability;
        this.alliance = alliance;
        board = new Board();
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public Ability getAbility() {
        return ability;
    }

    public Alliance getAlliance() {
        return alliance;
    }

    public int getCardLocation() {
        return cardLocation;
    }

    /**
     * Sets the card at the specified location on the board;
     * @param cardLocation The location on the board. (Must align with the card's alliance or an IllegalArgumentException will be thrown)
     */
    public void setCardLocation(int cardLocation) {
        if ((getAlliance() == Alliance.COMPUTER && cardLocation >= 5 && cardLocation <= 12)
                || (getAlliance() == Alliance.PLAYER && cardLocation >= 0 && cardLocation <= 4)) {
            if (board.getCardAtLocation(cardLocation) == null){
                this.cardLocation = cardLocation;  //Needs testing idk how to check for empty slot
            }
        } else {
            throw new IllegalArgumentException("Invalid card location");
        }
    }
}
