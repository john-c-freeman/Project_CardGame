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
    Card[] tempCards = {new Bear(getAlliance(), this), new Wolf(getAlliance(), this)};

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

        //Get a random valid location
        int computerMoveLocation = rand.nextInt(4, 12);
        while (!(board.isLocationOccupied(computerMoveLocation) && board.isValidLocation(tempCards[0], computerMoveLocation))) {
            computerMoveLocation = rand.nextInt(4, 12);
        }

        //Get a random card and place it on the board
        Card card = getHand().get(rand.nextInt(5));
        board.setCardAtLocation(card, computerMoveLocation);

        //Attack the player
        card.attack();
    }
}
