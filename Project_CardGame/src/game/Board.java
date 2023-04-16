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
    private final Player player = new Player(5, 5, PLAYER);
    private final Computer computer = new Computer(5, 5);

    /**
     * Constructs the board and two players.
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
        return (card.getAlliance() == COMPUTER && cardLocation >= 4) || (card.getAlliance() == PLAYER && cardLocation <= 3);
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

            //Lower the players mana
            if (card.getAlliance() == PLAYER) player.setMana(player.getMana() - card.getMana());
            else computer.setMana(computer.getMana() - card.getMana());
        }
    }

    /**
     * Removes the card at the given location
     * @param location The location of the card to be removed
     */
    public void removeCardAtLocation(int location) {
        cardLocations[location] = null;
    }

    /**
     * Attacks the enemy card at the location opposite the card provided as argument.
     * @param attackingCard The attacking card.
     */
    public void attackEnemy(Card attackingCard){
        Card enemyCard = getEnemyCard(attackingCard);
        if (enemyCard == null){
            //If the enemy card is null, attack the enemy player.
            if (attackingCard.getAlliance() == COMPUTER) player.setHealth(player.getHealth() - attackingCard.getPower());
            else computer.setHealth(computer.getHealth() - attackingCard.getPower());
        } else {
            //If the enemy card is not null, attack the enemy card.
            enemyCard.setHealth(enemyCard.getHealth() - attackingCard.getPower());
            if (enemyCard.getHealth() <= 0) removeCardAtLocation(enemyCard.getCardLocation());
        }
    }

    /**
     * Returns the player
     * @return The player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns the computer
     * @return The computer
     */
    public Computer getComputer() {
        return computer;
    }
}
