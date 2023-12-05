package amazons.figures;

import amazons.board.Board;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static amazons.figures.ArrowFigure.*;

public class ArrowFigureTest {

    private final Board board = new MatrixBoard(3,3);
    private final Position originPosition = new Position(0,0);

    @Test
    void testCanMoveTo(){
        assertThat(ARROW_FIGURE.canMoveTo(null, null)).isEqualTo(false);
        assertThat(ARROW_FIGURE.canMoveTo(originPosition, board)).isEqualTo(false);
    }

    @Test
    void testMoveTo(){
        assertThatThrownBy(() -> ARROW_FIGURE.moveTo(null, null))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("A neutral figure can't be moved");
        assertThatThrownBy(() -> ARROW_FIGURE.moveTo(originPosition, board))
                .isInstanceOf(IllegalMoveException.class)
                .hasMessage("A neutral figure can't be moved");
    }

    @Test
    void testSetPosition(){
    }

    @Test
    void testGetPlayerID(){
        assertThat(ARROW_FIGURE.getPlayerID()).isEqualTo(PlayerID.NONE);
    }

}
