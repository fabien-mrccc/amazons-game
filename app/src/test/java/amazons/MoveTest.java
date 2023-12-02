package amazons;

import amazons.board.Position;
import amazons.player.Move;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class MoveTest {

    private Move testMove;
    private final Position amazonStartPosition = new Position(0,0);
    private final Position amazonDstPosition = new Position(1,1);
    private final Position arrowDstPosition  = new Position(1,2);

    private Move testMove2;
    private final Position amazonStartPosition2 = new Position(0,0);
    private final Position amazonDstPosition2 = new Position(1,1);
    private final Position arrowDstPosition2  = new Position(1,2);

    private Move testMove3;
    private final Position amazonStartPosition3 = new Position(1,0);
    private final Position amazonDstPosition3 = new Position(1,1);
    private final Position arrowDstPosition3  = new Position(1,2);
    @BeforeEach
    void setTestMove(){
        testMove = new Move(amazonStartPosition, amazonDstPosition, arrowDstPosition);
        testMove2 = new Move(amazonStartPosition2, amazonDstPosition2,arrowDstPosition2);
        testMove3 = new Move(amazonStartPosition3, amazonDstPosition3, arrowDstPosition3);
    }
    @Test
    void testGetters() {
        assertThat(testMove.getAmazonStartPosition()).isEqualTo(amazonStartPosition);
        assertThat(testMove.getAmazonDstPosition()).isEqualTo(amazonDstPosition);
        assertThat(testMove.getArrowDstPosition()).isEqualTo(arrowDstPosition);
    }
    @Test
    void testEquals(){
        assertThat(testMove.equals(testMove2)).isTrue();
        assertThat(testMove.equals(testMove3)).isFalse();
    }
    @Test
    void testToString(){
        assertThat(testMove.toString()).isEqualTo("(0,0):(1,1)->(1,2)");
        assertThat(testMove2.toString()).isEqualTo("(0,0):(1,1)->(1,2)");
        assertThat(testMove3.toString()).isEqualTo("(1,0):(1,1)->(1,2)");
    }
    @Test
    void testHashCode(){
        assertThat(testMove.hashCode()).isNotEqualTo(testMove3.hashCode());
        assertThat(testMove.hashCode()).isEqualTo(testMove2.hashCode());
    }
}