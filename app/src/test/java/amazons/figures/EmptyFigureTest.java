package amazons.figures;

import amazons.board.Position;
import amazons.player.PlayerID;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class EmptyFigureTest {
    @Test
    void testGetPlayerID(){
        assertThat(EmptyFigure.EMPTY_FIGURE.getPlayerID()).isEqualTo(PlayerID.NONE);
    }

    @Test
    void testCanMoveTo(){
        assertThat(EmptyFigure.EMPTY_FIGURE.canMoveTo(new Position (0,0), null)).isEqualTo(false);
    }
}
