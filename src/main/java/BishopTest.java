import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BishopTest {

    List<int[]> moves;

    @BeforeEach
    public void setUp() {
        // Mock the Board class.
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('b'); // Assume all pieces are black

        // Create a Bishop
        Bishop bishop = new Bishop(board, 4, 4, 'w'); // Placing a white Bishop at the center of the board

        // Get the possible moves
        moves = bishop.possibleMoves();
    }

    @Test
    public void testPossibleMoves() {

        // Verify the size of the list
        assertEquals(13, moves.size()); // The Bishop should have 13 valid moves from the center of an empty 8x8 board
        for (int[] move : moves) {
            System.out.println(Arrays.toString(move));
        }
        // Verify some specific moves are present in the list
        assertTrue(moves.contains(new int[] {5, 5}));
        assertTrue(moves.contains(new int[] {3, 3}));
        assertTrue(moves.contains(new int[] {5, 3}));
        assertTrue(moves.contains(new int[] {3, 5}));
    }
}