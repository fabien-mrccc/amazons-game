package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.List;

public class AIBasicPlayerTest {

    private final List<Amazon> amazons = new ArrayList<>();
    private final AIBasicPlayer player = new AIBasicPlayer();

    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

    private final int boardWidth = 5;
    private final int boardHeight = 5;
    private final PlayerID playerID = PlayerID.PLAYER_ONE;
    private final List<Position>[] initialPositions = new List[]{DEFAULT_PLAYER0_POSITIONS, DEFAULT_PLAYER1_POSITIONS};

    @BeforeEach
    void setUp(){
        for(int y = 0; y < 3; y++){
            for(int x = 0; x < 3; x++) {
                amazons.add(new Amazon(new Position(x, y), 0));
            }
        }
        player.initialize(boardWidth, boardHeight, playerID, initialPositions);
    }

}
