package amazons.board;

import amazons.figures.Figure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static amazons.figures.EmptyFigure.*;

public class MatrixIteratorTest {
    private final int numberOfColumns = 3;
    private final int numberOfRows = 3;
    private Position[][] positions;
    private Figure[][] figures;
    private MatrixIterator positionsIterator;
    private MatrixIterator figuresIterator;


    @BeforeEach
    void setUp(){
        for(int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                positions[x][y] = new Position(x,y);
                figures[x][y] = EMPTY_FIGURE;
            }
        }
        positionsIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, positions);
        figuresIterator = new MatrixIterator<>(numberOfColumns, numberOfRows, figures);
    }

    @Test
    void hasNext(){

    }
}
