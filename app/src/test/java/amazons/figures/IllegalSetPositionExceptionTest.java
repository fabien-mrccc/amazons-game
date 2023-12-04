package amazons.figures;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class IllegalSetPositionExceptionTest {

    @Test
    void testGetMessage(){
        IllegalSetPositionException firstException = new IllegalSetPositionException("");
        assertThat(firstException.getMessage()).isEqualTo("");

        IllegalSetPositionException secondException = new IllegalSetPositionException("My test is correct!");
        assertThat(secondException.getMessage()).isEqualTo("My test is correct!");

        IllegalSetPositionException thirdException = new IllegalSetPositionException(null);
        assertThat(thirdException.getMessage()).isEqualTo(null);
    }

}
