package amazons.board;

import amazons.figures.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static amazons.figures.EmptyFigure.*;
import static org.assertj.core.api.Assertions.*;

public class MatrixIteratorTest {
    private MatrixIterator<Position> positionsIterator;
    private MatrixIterator<Figure> figuresIterator;
    private MatrixIterator<Position> nullIterator;

    @BeforeEach
    void setUp(){
        final int numberOfColumns = 3;
        final int numberOfRows = 3;
        final Position[][] positions = new Position[numberOfColumns][numberOfRows];
        final Figure[][] figures = new Figure[numberOfColumns][numberOfRows];

        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                positions[x][y] = new Position(x,y);
                figures[x][y] = EMPTY_FIGURE;
            }
        }
        positionsIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, positions);
        figuresIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, figures);
        nullIterator = new MatrixIterator<>(0,0,null);
    }

    @Test
    void hasNext(){
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isTrue();
        positionsIterator.next();
        assertThat(positionsIterator.hasNext()).isFalse();

        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isTrue();
        figuresIterator.next();
        assertThat(figuresIterator.hasNext()).isFalse();

        assertThat(nullIterator.hasNext()).isFalse();
    }
}
