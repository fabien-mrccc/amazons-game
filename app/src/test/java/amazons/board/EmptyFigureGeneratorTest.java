package amazons.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static amazons.figures.EmptyFigure.*;

import static org.assertj.core.api.Assertions.assertThat;
public class EmptyFigureGeneratorTest {
    private final Position position11 = new Position(1,1);
    private FigureGenerator emptyFigureGenerator;
    @BeforeEach
    void setUp(){
        emptyFigureGenerator = new EmptyFigureGenerator();
    }
    @Test
    void testNextFigure(){
        assertThat(emptyFigureGenerator.nextFigure(position11)).isEqualTo(EMPTY_FIGURE);
    }
}
