package test.unit;

import MVC.GameUI;
import MVC.PGN;
import board.BoardGame;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pieces.Pawn;
import pieces.Piece;

import static org.mockito.Mockito.*;

class BoardGameTest {

    private GameUI mockUI;
    private PGN mockPGN;
    private BoardGame boardGame;

    @BeforeEach
    public void setup() {
        mockUI = mock(GameUI.class);
        mockPGN = mock(PGN.class);
        boardGame = new BoardGame(mockUI);
        boardGame.pgn = mockPGN;
    }
    @Test
    void putPiece_ShouldPlacePieceOnBoard() {
        // Arrange
        Piece expectedPiece = new Pawn(boardGame, 2, 3, 'W');

        // Act
        boardGame.putPiece("WP", 2, 3);
        Piece actualPiece = boardGame.pieces[2][3];

        // Assert
        Assertions.assertEquals(expectedPiece.getClass(), actualPiece.getClass(), "Piece should be placed on the board");
        verify(mockUI, times(1)).putPiece("WP", 2, 3);  // Verify that the updateBoard() method was called once
    }

    @Test
    void testRemovePiece() {
        boardGame.putPiece("WP", 0, 0);
        boardGame.removePiece(0, 0);

        Assertions.assertEquals( 'N', boardGame.getPiece(0, 0));
    }

    @Test
    void findKing_ShouldReturnKingPosition() {
        // Arrange
        boardGame.putPiece("WK", 0, 0);

        // Act
        int[] whiteKingPosition = boardGame.findKing('W');

        // Assert
        Assertions.assertArrayEquals(new int[]{0, 0}, whiteKingPosition, "White King should be at (0, 0)");
    }

    @Test
    void testMove() {
        // Set up test scenario
        int fromX = 0;
        int fromY = 0;
        int toX = 1;
        int toY = 0;

        //put some pieces on the board so there is no stalemate or checkmate
        boardGame.putPiece("WK", 0, 0);
        boardGame.putPiece("BK", 7, 7);
        boardGame.putPiece("BP", 6, 6);
        boardGame.putPiece("WP", 0, 2);

        boardGame.clickedOn[0] = fromX;
        boardGame.clickedOn[1] = fromY;

        // Call the method under test
        boardGame.move(toX, toY);

        // Verify that the expected methods were called
        verify(mockUI).putPiece("WK", fromX, fromY);
        verify(mockUI).putPiece("WK", toX, toY);
        verify(mockUI).removePiece(fromX, fromY);
        verify(mockPGN).updatePGN(fromX, fromY, toX, toY, false, 'K');
        verify(mockUI).updateText();
    }
}

