package game.board;

import game.Alliance;
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
    private Card[] cardLocations = new Card[12];

    /**
     * Constructs the board
     */
    public Board(){
    }

    /**
     * Places the provided card at the given location
     * @param card The card to be place
     * @param location The location for the card to be placed
     */
    public void placeCard(Card card, int location){
        if ((card.getAlliance() == PLAYER && location <= 3) || (card.getAlliance() == COMPUTER && location > 3)) {
            cardLocations[location] = card;
        } else {
            throw new IllegalArgumentException("The provided location belongs to the opponent.");
        }
    }

    /**
     * Returns the card opposite the card passed as argument.
     * @param card the card being checked.
     * @return The card at the specified index.
     */
    public Card getEnemyCard(Card card){

        if (card.getAlliance() == COMPUTER){
            int cardLocation = card.getCardLocation();
            if (cardLocation > 7) { //Card is on the top row.
                return cardLocations[cardLocation - 8];
            } else { //Card is on the middle row.
                return cardLocations[cardLocation - 4];
            }
        }

        //If above condition wasn't met, the card belongs to the player and is on the first row.
        return cardLocations[card.getCardLocation() + 4];
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
     */
    public boolean isValidLocation(Alliance alliance, int cardLocation){
        return (alliance == COMPUTER && cardLocation >= 4 && cardLocation <= 11) ||
                (alliance == PLAYER && cardLocation >= 0 && cardLocation <= 3);
    }

    /**
     * Returns a boolean stating whether the given location is occupied.
     * @param cardLocation
     * @return
     */
    public boolean isLocationOccupied(int cardLocation){
        return cardLocations[cardLocation] != null;
    }

    public void setCardAtLocations(){

    }
}
