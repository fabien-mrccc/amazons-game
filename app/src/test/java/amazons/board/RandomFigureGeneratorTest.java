package amazons.board;

import amazons.figures.Amazon;
import amazons.figures.MovableFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;


public class RandomFigureGeneratorTest {

    private final Random random = new Random();
    private final List<MovableFigure> movableFigures = new ArrayList<>();
    private final MatrixBoard matrixBoard = new MatrixBoard(10,10);
    private final Iterator<Position> iterator = matrixBoard.positionIterator();

    private RandomFigureGenerator randomFigureGenerator;

    @BeforeEach
    void setUp(){
        movableFigures.add(new Amazon(new Position(0,0),0));
        movableFigures.add(new Amazon(new Position(1,0),0));
        movableFigures.add(new Amazon(new Position(2,0),0));
        movableFigures.add(new Amazon(new Position(3,0),1));
        movableFigures.add(new Amazon(new Position(4,0),1));
        movableFigures.add(new Amazon(new Position(5,0),1));
    }

    @Test
    int numberOfFiguresToAssign(){
        return 0;
    }
}
