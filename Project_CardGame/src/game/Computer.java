package game;

import game.card.Card;
import game.card.Deck;

import java.util.List;
import java.util.Random;

/**
 * A Computer opponent that the player will play against.
 */
public class Computer extends Player {
    private final Random rand = new Random();

    /**
     * Constructs a new Computer opponent
     *
     * @param deck   A Deck object
     * @param health The player's health
     * @param mana   The player's mana
     * @param hand   A List of Cards representing the hand the player is playing with.
     */
    public Computer(Deck deck, int health, int mana, List<Card> hand) {
        super(deck, health, mana, hand);
    }

    /**
     * Does a computer move.
     */
    public void doComputerMove() {

        //Get a random valid location
        int computerMoveLocation = rand.nextInt(4, 12);
        while (!(board.isLocationOccupied(computerMoveLocation) && board.isValidLocation(Alliance.COMPUTER, computerMoveLocation))) {
            computerMoveLocation = rand.nextInt(4, 12);
        }

        //Get a random card and place it on the board
        Card card = getHand().get(rand.nextInt(5));
        board.placeCard(card, computerMoveLocation);

        //Attack the player
        card.attack();
    }
}
