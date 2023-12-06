package amazons.player;

import amazons.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

public class AIBasicPlayerTest {
    private final AIBasicPlayer player = new AIBasicPlayer();

    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

    private final int boardWidth = 10;
    private final int boardHeight = 10;
    private final PlayerID playerID = PlayerID.PLAYER_ONE;
    private final List<Position>[] initialPositions = new List[]{DEFAULT_PLAYER0_POSITIONS, DEFAULT_PLAYER1_POSITIONS};

    @BeforeEach
    void setUp(){
        player.initialize(boardWidth, boardHeight, playerID, initialPositions);
    }

    @Test
    void testPlay(){
        assertThat(player.play(new Move(null,null,null))
                .getAmazonStartPosition().columnIndex()).isBetween(0,9);
        assertThat(player.play(new Move(null,null,null))
                .getAmazonStartPosition().rowIndex()).isBetween(0,9);
    }
}
