package amazons.board;

import amazons.figures.Amazon;
import amazons.figures.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

public class MatrixIteratorTest {
    private MatrixIterator<Position> positionsIterator;
    private MatrixIterator<Figure> figuresIterator;
    private MatrixIterator<Position> nullIterator;
    private final int numberOfColumns = 3;
    private final int numberOfRows = 3;

    @BeforeEach
    void setUp(){
        Position[][] positions = new Position[numberOfColumns][numberOfRows];
        Figure[][] figures = new Figure[numberOfColumns][numberOfRows];

        for(int y = 0; y < numberOfRows; y++) {
            for (int x = 0; x < numberOfColumns; x++) {
                positions[x][y] = new Position(x,y);
                figures[x][y] = new Amazon(positions[x][y],0);
            }
        }
        positionsIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, positions);
        figuresIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, figures);
        nullIterator = new MatrixIterator<>(0,0,null);
    }

    @Test
    void testHasNext(){

        for(int i = 0; i < numberOfColumns * numberOfRows ; i++) {
            assertThat(positionsIterator.hasNext()).isTrue();
            positionsIterator.next();
            assertThat(figuresIterator.hasNext()).isTrue();
            figuresIterator.next();
            assertThat(nullIterator.hasNext()).isFalse();
        }

        assertThat(positionsIterator.hasNext()).isFalse();
        assertThat(figuresIterator.hasNext()).isFalse();
    }

    @Test
    void testNext(){

        for(int y = 0; y < numberOfColumns; y++) {
            for (int x = 0; x < numberOfRows; x++) {
                assertThat(positionsIterator.next().equals(new Position(x,y))).isTrue();
                Amazon amazon = (Amazon) figuresIterator.next();
                assertThat(amazon.getPosition().equals(new Position(x,y))).isTrue();
                assertThatThrownBy(() -> nullIterator.next()).isInstanceOf(NoSuchElementException.class);
            }
        }
    }

    @Test
    void testGetNumberOfColumns(){

        for(int i = 0; i < numberOfColumns * numberOfRows ; i++) {
            assertThat(positionsIterator.getNumberOfColumns() == numberOfColumns).isTrue();
            positionsIterator.next();
            assertThat(figuresIterator.getNumberOfColumns() == numberOfColumns).isTrue();
            figuresIterator.next();
            assertThat(nullIterator.getNumberOfColumns() == 0).isTrue();
        }
    }

    @Test
    void testGetNumberOfRows(){

        for(int i = 0; i < numberOfColumns * numberOfRows ; i++) {
            assertThat(positionsIterator.getNumberOfRows() == numberOfRows).isTrue();
            positionsIterator.next();
            assertThat(figuresIterator.getNumberOfRows() == numberOfRows).isTrue();
            figuresIterator.next();
            assertThat(nullIterator.getNumberOfRows() == 0).isTrue();
        }
    }
}
