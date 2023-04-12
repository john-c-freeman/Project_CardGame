package game.card;

import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * Class represents a Deck containing 0-10 Cards.
 */
public class Deck {
    private final List<Card> deck;
    private final Random rand = new Random();

    /**
     * Constructs a new deck
     */
    public Deck(List<Card> deck) {
        //TODO how should we initialize a deck;
        this.deck = deck;
    }

    public List<Card> getDeck(){
        return deck;
    }

    /**
     * Returns a random card from the deck.
     * //TODO more game logic like checking if the card is in the players hand
     */
    public Card drawCard(){
        return deck.get(rand.nextInt(deck.size()));
    }
}
