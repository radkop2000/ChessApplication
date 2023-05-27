package test;

import board.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pieces.Rook;

import static org.mockito.Mockito.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

public class RookTest {

    private Board board;
    private Rook rook;

    @BeforeEach
    public void setUp() {
        board = mock(Board.class);
        when(board.getPiece(anyInt(), anyInt())).thenReturn('N'); // Assume all positions are empty
        when(board.getColor(anyInt(), anyInt())).thenReturn('N'); // Assume all pieces are black

        // Create a Rook
        rook = new Rook(board, 4, 4, 'w'); // Placing a white Rook at the center of the board
    }

    @Test
    public void testLengthOfPossibleMoves() {
        // Get the possible moves
        List<int[]> moves = rook.possibleMoves();

        assertEquals(14, moves.size());
    }

    @ParameterizedTest
    @MethodSource("provideMovesForTesting")
    public void testPossibleMovesContainsSpecificMove(String expectedMove) {
        // Get the possible moves
        List<int[]> moves = rook.possibleMoves();

        // Convert list of int[] to list of String
        List<String> stringMoves = moves.stream()
                .map(moveArr -> moveArr[0] + "," + moveArr[1]).toList();

        // Verify specific move is present in the list
        assertTrue(stringMoves.contains(expectedMove));
    }

    static Stream<String> provideMovesForTesting() {
        return Stream.concat(
                Stream.of("4,0", "4,1", "4,2", "4,3", "4,5", "4,6", "4,7"),
                Stream.of("0,4", "1,4", "2,4", "3,4", "5,4", "6,4", "7,4")
        );
    }
}
