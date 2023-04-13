package game;

import game.card.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A class representing a player.
 */
public class Player {
    public static final Board board = new Board();
    private final List<Card> deck = new ArrayList<>();
    private final List<Card> hand = new ArrayList<>();
    private final Alliance alliance;
    private final Random rand = new Random();
    private int health;
    private int mana;
    private int numberOfTurns = 0;

    /**
     * Constructs a new Player
     *
     * @param health The player's health
     * @param mana   The player's mana
     */
    public Player(int health, int mana, Alliance alliance) {
        this.health = health;
        this.mana = mana;
        this.alliance = alliance;
        initializeRandomDeck();
        initializeHand();
    }

    /**
     * Initializes the player's hand by removing 5 cards from the deck.
     */
    private void initializeHand() {
        for (int i = 0; i < 5; i++) {
            hand.add(drawCard());
        }
    }

    /**
     * Initializes a deck of random cards.
     * //TODO make more cards to complete this method
     */
    private void initializeRandomDeck() {
        int numberOfUniqueCards = 6;
        List<Card> uniqueCards = List.of(new Bear(alliance, this), new Wolf(alliance, this), new Squirrel(alliance,
                this), new Cat(alliance, this), new Crow(alliance, this), new Shark(alliance, this));
        for (int i = 0; i < 10; i++) {
            int r = rand.nextInt(numberOfUniqueCards);
            deck.add(uniqueCards.get(r));
            //uniqueCards.remove(r);
            //numberOfUniqueCards--;
        }
    }

    /**
     * Draws a random card from the players deck
     *
     * @return A random card from the players deck
     */
    public Card drawCard() {
        //TODO check if the card is already in the player's hand?
        int n = rand.nextInt(deck.size());
        Card drawnCard = deck.get(n);
        deck.remove(n);
        return drawnCard;
    }

    /**
     * Returns the player's deck
     *
     * @return the player's deck
     */
    public List<Card> getDeck() {
        return this.deck;
    }

    /**
     * Returns the player's hand
     *
     * @return the player's hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Adds a card to the player's hand
     *
     * @param card A Card
     */
    public void setHand(Card card) {
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

    public Alliance getAlliance() {
        return alliance;
    }
}
