package amazons.figures;

import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrowFigureTest {
    @Test
    void testGetPlayerID(){
        assertThat(ArrowFigure.ARROW_FIGURE.getPlayerID()).isEqualTo(PlayerID.NONE);
    }

    @Test
    void testCanMoveTo(){
        assertThat(ArrowFigure.ARROW_FIGURE.canMoveTo(new Position(0,0), null)).isEqualTo(false);
    }
}
