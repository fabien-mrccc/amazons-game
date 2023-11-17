package amazons.board;

import amazons.board.CardinalDirection;
import amazons.board.Position;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class PositionTest {


    private final Position position00 = new Position(0,0);
    private final Position position10 = new Position(1,0);
    private final Position position20 = new Position(2,0);

    private final Position position02 = new Position(0,2);
    private final Position position12 = new Position(1,2);
    private final Position position22 = new Position(2,2);


    private final Position position01 = new Position(0,1);
    private final Position position11 = new Position(1,1);
    private final Position position21 = new Position(2,1);

    @Test
    void testGetX() {
        assertThat(position00.getX()).isEqualTo(0);
        assertThat(position12.getX()).isEqualTo(1);
    }

    @Test
    void testGetY() {
        assertThat(position00.getY()).isEqualTo(0);
        assertThat(position12.getY()).isEqualTo(2);
    }

    @Test
    void testIsOutOfBounds() {
        assertThat(position12.isOutOfBounds(2,3)).isFalse();
        assertThat(position12.isOutOfBounds(1,1)).isTrue();
        assertThat(new Position(-1,0).isOutOfBounds(4,6)).isTrue();
    }

    @Test
    void testNext() {
        assertThat(position11.next(CardinalDirection.NORTH)).isEqualTo(position10);
        assertThat(position11.next(CardinalDirection.NORTH_EAST)).isEqualTo(position20);
        assertThat(position11.next(CardinalDirection.EAST)).isEqualTo(position21);
        assertThat(position11.next(CardinalDirection.SOUTH_EAST)).isEqualTo(position22);
        assertThat(position11.next(CardinalDirection.SOUTH)).isEqualTo(position12);
        assertThat(position11.next(CardinalDirection.SOUTH_WEST)).isEqualTo(position02);
        assertThat(position11.next(CardinalDirection.WEST)).isEqualTo(position01);
        assertThat(position11.next(CardinalDirection.NORTH_WEST)).isEqualTo(position00);

    }

    @Test
    void testEquals() {
        assertThat(position12).isEqualTo(new Position(1,2)).isNotEqualTo(new Position(2,1));
    }

    @Test
    void testToString() {
        assertThat(position12.toString()).isEqualTo("(1,2)");
    }




    @Test
    void testHashCode() {
        // two positions must have same hash code if and
        // only if they have the same coordinates
        assertThat(position00.hashCode()).isNotEqualTo(position01.hashCode());
        assertThat(position00.hashCode()).isEqualTo(new Position(0,0).hashCode());
    }



}