package amazons.figures;

import amazons.board.Board;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static amazons.figures.EmptyFigure.*;

public class EmptyFigureTest {

    private final Board board = new MatrixBoard(3,3);
    private final Position originPosition = new Position(0,0);

    @Test
    void testCanMoveTo(){
        assertThat(EMPTY_FIGURE.canMoveTo(null, null)).isEqualTo(false);
        assertThat(EMPTY_FIGURE.canMoveTo(originPosition, board)).isEqualTo(false);
    }

    @Test
    void testMoveTo(){
        assertThatThrownBy(() -> EMPTY_FIGURE.moveTo(null, null))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("A neutral figure can't be moved");
        assertThatThrownBy(() -> EMPTY_FIGURE.moveTo(originPosition, board))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("A neutral figure can't be moved");
    }

    @Test
    void testGetPlayerID(){
        assertThat(EMPTY_FIGURE.getPlayerID()).isEqualTo(PlayerID.NONE);
    }

}
