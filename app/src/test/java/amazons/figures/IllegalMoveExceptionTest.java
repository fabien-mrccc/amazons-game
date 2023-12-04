package amazons.figures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class IllegalMoveExceptionTest {

    @Test
    void testGetMessage(){
        IllegalMoveException firstException = new IllegalMoveException("");
        assertThat(firstException.getMessage()).isEqualTo("");

        IllegalMoveException secondException = new IllegalMoveException("My test is correct!");
        assertThat(secondException.getMessage()).isEqualTo("My test is correct!");

        IllegalMoveException thirdException = new IllegalMoveException(null);
        assertThat(thirdException.getMessage()).isEqualTo(null);
    }

}
