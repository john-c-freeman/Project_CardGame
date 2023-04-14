package game;

import game.card.Card;
import game.card.Bear;
import game.card.Wolf;

import java.util.List;
import java.util.Random;

/**
 * A Computer opponent that the player will play against.
 */
public class Computer extends Player {
    private final Random rand = new Random();
    Card[] tempCards = {new Bear(getAlliance()), new Wolf(getAlliance())};

    /**
     * Constructs a new Computer opponent
     * @param health   The Computer's health
     * @param mana     The Computer's mana
     */
    public Computer(int health, int mana) {
        super(health, mana, Alliance.COMPUTER);
    }


    /**
     * Does a computer move.
     */
    public void doComputerMove() {
        final Board board = new Board();
        //Get a random card with mana less than or equal to the computer's mana.
        Card card = getHand().get(rand.nextInt(5));
        while (card.getMana() > getMana()) {
            card = getHand().get(rand.nextInt(5));
        }

        //Place the card at a random non-occupied location.
        int computerMoveLocation = rand.nextInt(4, 12);
        while (!(board.isLocationOccupied(computerMoveLocation) && board.isValidLocation(tempCards[0], computerMoveLocation))) {
            computerMoveLocation = rand.nextInt(4, 12);
        }
        board.setCardAtLocation(card, computerMoveLocation);

        //Attack the enemy card.
        board.attackEnemy(card);
    }
}
