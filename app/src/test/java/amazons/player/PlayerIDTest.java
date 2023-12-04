package amazons.player;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static amazons.player.PlayerID.*;
public class PlayerIDTest {
    @Test
    void testToString(){
        assertThat(PLAYER_ONE.toString()).isEqualTo("player 1 (black)");
        assertThat(PLAYER_ZERO.toString()).isEqualTo("player 0 (white)");
        assertThat(NONE.toString()).isEqualTo("player -1 ()");
    }
    @Test
    void testOpponent(){
        assertThat(PLAYER_ZERO.opponent()).isEqualTo(PLAYER_ONE);
        assertThat(PLAYER_ONE.opponent()).isEqualTo(PLAYER_ZERO);
        assertThat(NONE.opponent()).isEqualTo(NONE);
    }
    @Test
    void testGetPlayerIDFromIndex(){
        assertThat(getPlayerIDFromIndex(0)).isEqualTo(PLAYER_ZERO);
        assertThat(getPlayerIDFromIndex(-1)).isEqualTo(NONE);
        assertThat(getPlayerIDFromIndex(1)).isEqualTo(PLAYER_ONE);
    }
}
