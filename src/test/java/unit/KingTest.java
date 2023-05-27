package unit;

import board.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pieces.King;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class KingTest {

    @Test
    void testLengthOfPossibleMoves() {
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('b'); // Assume all pieces are black

        // Create a King
        King king = new King(board, 4, 4, 'w'); // Placing a white King at the center of the board

        // Get the possible moves
        List<int[]> moves = king.possibleMoves();

        assertEquals(8, moves.size());
    }

    @ParameterizedTest
    @MethodSource("provideMovesForTesting")
    void testPossibleMovesContainsSpecificMove(String expectedMove) {
        // Mock the Board class.
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('b'); // Assume all pieces are black

        // Create a King
        King king = new King(board, 4, 4, 'w'); // Placing a white King at the center of the board

        // Get the possible moves
        List<int[]> moves = king.possibleMoves();

        // Convert list of int[] to list of String
        List<String> stringMoves = moves.stream()
                .map(moveArr -> moveArr[0] + "," + moveArr[1]).toList();

        // Verify specific move is present in the list
        assertTrue(stringMoves.contains(expectedMove));
    }

    static Stream<String> provideMovesForTesting() {
        return Stream.of(
                "3,4", "5,4", "4,3", "4,5",
                "3,3", "3,5", "5,3", "5,5"
        );
    }
}
