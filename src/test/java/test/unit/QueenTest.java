package test.unit;

import board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pieces.Queen;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class QueenTest {

    private Queen queen;

    @BeforeEach
    public void setUp() {
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('N'); // Assume all pieces are black

        // Create a Queen
        queen = new Queen(board, 4, 4, 'w'); // Placing a white Queen at the center of the board
    }

    @Test
    public void testLengthOfPossibleMoves() {
        // Get the possible moves
        List<int[]> moves = queen.possibleMoves();

        assertEquals(27, moves.size());
    }

    @ParameterizedTest
    @MethodSource("provideMovesForTesting")
    public void testPossibleMovesContainsSpecificMove(String expectedMove) {
        // Get the possible moves
        List<int[]> moves = queen.possibleMoves();

        // Convert list of int[] to list of String
        List<String> stringMoves = moves.stream()
                .map(moveArr -> moveArr[0] + "," + moveArr[1]).toList();

        // Verify specific move is present in the list
        assertTrue(stringMoves.contains(expectedMove));
    }

    static Stream<String> provideMovesForTesting() {
        return Stream.of(
                "5,4", "3,4", "4,5", "4,3",
                "5,5", "3,3", "5,3", "3,5",
                "6,6", "7,7", "2,2", "1,1",
                "2,6", "1,7", "6,2", "7,1",
                "4,0", "4,1", "4,2", "4,6",
                "4,7", "0,4", "1,4", "2,4",
                "6,4", "7,4", "4,7"
        );
    }
}
