package game.card;

import game.Alliance;
import game.Player;
import game.board.Board;

public abstract class Card {
    private int power;
    private int health;
    private int mana;
    private Ability ability;
    private Alliance alliance;
    private int cardLocation;
    private final Board board;
    private Player player;

    /**
     * Constructs a new card
     * @param power The card's power
     * @param health The card's health
     * @param mana The card's mana
     * @param ability The type ability
     * @param alliance The card's alliance
     */
    public Card(int power, int health, int mana, Ability ability, Alliance alliance, Player player){
        this.power = power;
        this.health = health;
        this.mana = mana;
        this.ability = ability;
        this.alliance = alliance;
        this.player = player;
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
        if (!board.isValidLocation(alliance, cardLocation))
            throw new IllegalArgumentException("Invalid card location");
        this.cardLocation = cardLocation;
    }

    public Player getPlayer() {
        return this.player;
    }

    /**
     * Attacks the card opposite this one.
     */
    public void attack(){
        Card enemyCard = board.getEnemyCard(this);
        if (enemyCard != null) {
            enemyCard.setHealth(this.getPower() - enemyCard.getHealth());
            //Set the enemy card to null if health is 0.
            if (enemyCard.getHealth() <= 0) {
                board.placeCard(null, enemyCard.cardLocation);
            }
        } else {
            enemyCard.getPlayer().setHealth(this.getPower() - enemyCard.getPlayer().getHealth());
        }
    }
}
