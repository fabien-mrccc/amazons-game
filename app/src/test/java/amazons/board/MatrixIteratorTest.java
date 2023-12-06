package amazons.board;

import amazons.figures.Amazon;
import amazons.figures.Figure;
import org.junit.jupiter.api.AfterEach;
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

        for(int x = 0; x < numberOfColumns; x++) {
            for (int y = 0; y < numberOfRows; y++) {
                positions[x][y] = new Position(x,y);
                figures[x][y] = new Amazon(positions[x][y],0);
            }
        }
        positionsIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, positions);
        figuresIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, figures);
        nullIterator = new MatrixIterator<>(0,0,null);
    }

    @Test
    void hasNext(){

        for(int i = 0; i < numberOfColumns * numberOfRows ; i++) {
            assertThat(positionsIterator.hasNext()).isTrue();
            positionsIterator.next();
            assertThat(figuresIterator.hasNext()).isTrue();
            figuresIterator.next();
        }

        assertThat(positionsIterator.hasNext()).isFalse();
        assertThat(figuresIterator.hasNext()).isFalse();
        assertThat(nullIterator.hasNext()).isFalse();
    }

    @Test
    void next(){

/*
        for(int x = 0; x < numberOfColumns; x++) {
            for (int y = 0; y < numberOfRows; y++) {
                assertThat(positionsIterator.next().equals(new Position(x,y))).isTrue();
                assertThat(figuresIterator.next().equals(new Amazon(new Position(x,y),0))).isTrue();
                assertThatThrownBy(() -> nullIterator.next()).isInstanceOf(NoSuchElementException.class);
            }
        }

 */
    }
}
