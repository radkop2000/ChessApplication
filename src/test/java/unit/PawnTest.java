package unit;

import board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pieces.Pawn;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class PawnTest {

    private Pawn pawn;

    @BeforeEach
    public void setUp() {
        Board board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('N'); // Assume all pieces are black

        // Create a Pawn
        pawn = new Pawn(board, 1, 1, 'w'); // Placing a white Pawn at its starting position

    }

    @Test
    public void testLengthOfPossibleMoves() {
        // Get the possible moves
        List<int[]> moves = pawn.possibleMoves();
        for (int[] move : moves) {
            System.out.println(move[0] + "," + move[1]);
        }
        assertEquals(2, moves.size());
    }

    @ParameterizedTest
    @MethodSource("provideMovesForTesting")
    public void testPossibleMovesContainsSpecificMove(String expectedMove) {
        // Get the possible moves
        List<int[]> moves = pawn.possibleMoves();

        // Convert list of int[] to list of String
        List<String> stringMoves = moves.stream()
                .map(moveArr -> moveArr[0] + "," + moveArr[1]).toList();

        // Verify specific move is present in the list
        assertTrue(stringMoves.contains(expectedMove));
    }

    static Stream<String> provideMovesForTesting() {
        return Stream.of(
                "2,1", "3,1"
        );
    }
}
