package game;

import game.board.Board;
import game.card.Card;
import game.card.Deck;

import java.util.*;

/**
 * A class representing a player.
 */
public class Player {
    private final Random rand = new Random();
    private final Deck deck;
    private int health;
    private int mana;
    private int numberOfTurns = 0;
    private final List<Card> hand;
    public static final Board board = new Board();

    /**
     * Constructs a new Player
     * @param deck A Deck object
     * @param health The player's health
     * @param mana The player's mana
     * @param hand A List of Cards representing the hand the player is playing with.
     */
    public Player(Deck deck, int health, int mana, List<Card> hand) {
        this.deck = deck;
        this.health = health;
        this.mana = mana;
        this.hand = hand;
    }

    /**
     * Draws a random card from the players deck
     * @return A random card from the players deck
     */
    public Card drawCard(){
        return deck.drawCard();
    }

    /**
     * Returns the player's deck
     * @return the player's deck
     */
    public List<Card> getDeck(){
        return deck.getDeck();
    }

    /**
     * Returns the player's hand
     * @return the player's hand
     */
    public List<Card> getHand(){
        return hand;
    }

    /**
     * Adds a card to the player's hand
     * @param card A Card
     */
    public void setHand(Card card){
        hand.add(card);
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

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public Random getRand() {
        return rand;
    }

    public Board board(){
        return board;
    }
}
