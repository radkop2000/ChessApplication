package test.unit;

import board.Board;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pieces.Horse;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {

    @Test
    public void testLengthOfPossibleMoves() {
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('b'); // Assume all pieces are black

        // Create a Horse
        Horse horse = new Horse(board, 4, 4, 'w'); // Placing a white Horse at the center of the board

        // Get the possible moves
        List<int[]> moves = horse.possibleMoves();

        assertEquals(8, moves.size());
    }

    @ParameterizedTest
    @MethodSource("provideMovesForTesting")
    public void testPossibleMovesContainsSpecificMove(String expectedMove) {
        // Mock the Board class.
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('b'); // Assume all pieces are black

        // Create a Horse
        Horse horse = new Horse(board, 4, 4, 'w'); // Placing a white Horse at the center of the board

        // Get the possible moves
        List<int[]> moves = horse.possibleMoves();

        // Convert list of int[] to list of String
        List<String> stringMoves = moves.stream()
                .map(moveArr -> moveArr[0] + "," + moveArr[1]).toList();

        // Verify specific move is present in the list
        assertTrue(stringMoves.contains(expectedMove));
    }

    static Stream<String> provideMovesForTesting() {
        return Stream.of(
                "2,3", "2,5", // Moves in the upper left direction
                "3,2", "3,6", // Moves in the upper right direction
                "5,2", "5,6", // Moves in the lower left direction
                "6,3", "6,5"  // Moves in the lower right direction
        );
    }
}
