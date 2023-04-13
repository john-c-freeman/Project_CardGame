package game.board;

import game.Alliance;
import game.Board;
import game.Computer;
import game.Player;
import game.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board board;
    private Player player;
    private Computer computer;

    @BeforeEach
    void init() {
        board = new Board();
        player = new Player(5, 5, Alliance.PLAYER);
        computer = new Computer(5, 5);
    }

    @Test
    void Board_EmptyBoard_EmptySlotReturnsNull() {
        assertNull(board.getCardAtLocation(3));
    }

    @Test
    void Board_PlacingCards() {
        Bear bear = new Bear(player.getAlliance(), player);
        Wolf wolf = new Wolf(computer.getAlliance(), computer);
        board.setCardAtLocation(bear, 3);
        board.setCardAtLocation(wolf, 7);
        assertEquals(wolf, board.getCardAtLocation(7));
        assertEquals(bear, board.getCardAtLocation(3));
    }

    @Test
    void Board_GetEnemyCard() {
        for (int i = 0; i < 4; i++) {
            Bear bear = new Bear(player.getAlliance(), player);
            Wolf wolf = new Wolf(computer.getAlliance(), computer);
            board.setCardAtLocation(bear, i);
            int middleRow = i + 4;
            board.setCardAtLocation(wolf, middleRow);
            assertEquals(wolf, board.getEnemyCard(bear));
            assertEquals(bear, board.getEnemyCard(wolf));
            Wolf wolf2 = new Wolf(computer.getAlliance(), computer);
            int backRow = i + 8;
            board.setCardAtLocation(wolf2, backRow);
            assertEquals(bear, board.getEnemyCard(wolf2));
        }
    }

    @Test
    void Board_CardOutOfRange_ThrowsIllegalArgumentException() {
        Shark shark = new Shark(player.getAlliance(), player);
        board.setCardAtLocation(shark, -1);
        //The card is never placed at -1, but the exception is thrown when you try to get it.
        //TODO handle this in a better way
        assertThrows(IllegalArgumentException.class, () -> board.getCardAtLocation(-1));
    }

    @Test
    void Board_RemoveCardAndReplaceCard() {
        Shark shark = new Shark(player.getAlliance(), player);
        board.setCardAtLocation(shark, 2);
        assertEquals(shark, board.getCardAtLocation(2));

        Cat cat = new Cat(computer.getAlliance(), computer);
        board.setCardAtLocation(cat, 6);
        assertEquals(cat, board.getCardAtLocation(6));
        assertEquals(shark, board.getEnemyCard(cat));

        board.removeCardAtLocation(6);
        assertNull(board.getCardAtLocation(6));
        //Successfully removes the card

        //Successfully replaces a new card at the location
        Bear bear = new Bear(computer.getAlliance(), computer);
        board.setCardAtLocation(bear, 6);
        assertEquals(bear, board.getCardAtLocation(6));
        assertEquals(shark, board.getEnemyCard(bear));
        assertEquals(bear, board.getEnemyCard(shark));
    }
}