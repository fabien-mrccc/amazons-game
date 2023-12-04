package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;
import static amazons.figures.EmptyFigure.*;

public class EmptyFigureTest {
    @Test
    void testCanMoveTo(){
        assertThat(EMPTY_FIGURE.canMoveTo(null, null)).isEqualTo(false);
        assertThat(EMPTY_FIGURE.canMoveTo(new Position(0,0), new MatrixBoard(3,3))).isEqualTo(false);
    }

    @Test
    void testMoveTo(){
        assertThatExceptionOfType(IllegalMoveException.class)
                .isThrownBy(() -> EMPTY_FIGURE.moveTo(null,null))
                .withMessageMatching("A neutral figure can't be moved");
    }
    @Test
    void testGetPlayerID(){
        assertThat(EMPTY_FIGURE.getPlayerID()).isEqualTo(PlayerID.NONE);
    }

}
