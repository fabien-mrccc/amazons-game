package amazons.player;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PlayerIDTest {
    @Test
    void testToString(){
        assertThat(PlayerID.PLAYER_ONE.toString()).isEqualTo("player 1 (black)");
        assertThat(PlayerID.PLAYER_ZERO.toString()).isEqualTo("player 0 (white)");
        assertThat(PlayerID.NONE.toString()).isEqualTo("player -1 ()");
    }
}
