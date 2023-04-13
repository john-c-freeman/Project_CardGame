package game;

import game.card.Card;
import static game.Alliance.*;

/**
 * This class represents the game board.
 * The locations start at the bottom left and go from left to right like so:
 * <li>8  9  10  11</li>
 * <li>4  5  6   7</li>
 * <li>0  1  2   3</li>
 */
public class Board {
    private final Card[] cardLocations = new Card[12];

    /**
     * Constructs the board
     */
    public Board(){}

    /**
     * Returns the card opposite the card passed as argument.
     * @param card the card being checked.
     * @return The card at the specified index.
     */
    public Card getEnemyCard(Card card){
        int location = card.getCardLocation();
        if (card.getAlliance() == COMPUTER){
            if (location >= 8) return getCardAtLocation(location - 8);
            if (location >= 4) return getCardAtLocation(location - 4);
        }
        return getCardAtLocation(location + 4);
    }

    /**
     * Returns the card at the specified index
     * @param location the location of the card
     * @return Card at location
     */
    public Card getCardAtLocation(int location){
        return cardLocations[location];
    }

    /**
     * Returns a boolean stating whether the given location is valid for the given card.
     * @param card A card
     * @param cardLocation The location being checked
     * @return Boolean stating whether the given location is valid for the given card.
     */
    public boolean isValidLocation(Card card, int cardLocation){
        if (cardLocation < 0 || cardLocation > 11) throw new IllegalArgumentException("Location out of bounds");

        return (card.getAlliance() == COMPUTER && cardLocation >= 4 && cardLocation <= 11) ||
                (card.getAlliance() == PLAYER && cardLocation >= 0 && cardLocation <= 3);
    }

    /**
     * Returns a boolean stating whether the given location is occupied.
     * @param cardLocation The location being checked
     * @return Boolean stating whether the given location is occupied.
     */
    public boolean isLocationOccupied(int cardLocation){
        if (cardLocations[cardLocation] != null) System.out.println("Location is occupied");
        return cardLocations[cardLocation] != null;
    }

    /**
     * Places the provided card at the given location
     * @param card The card to be placed
     * @param location The location for the card to be placed
     */
    public void setCardAtLocation(Card card, int location){
        if (isValidLocation(card, location) && !isLocationOccupied(location)){
            cardLocations[location] = card;
            card.setCardLocation(location);
        }
    }

    /**
     * Removes the card at the given location
     * @param location The location of the card to be removed
     */
    public void removeCardAtLocation(int location) {
        cardLocations[location] = null;
    }
}
