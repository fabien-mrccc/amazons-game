package amazons.board;

import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;


public class RandomFigureGeneratorTest {

    private final Random random = new Random();
    private final List<MovableFigure> movableFigures = new ArrayList<>();
    private final MatrixBoard matrixBoard = new MatrixBoard(10,10);
    private Iterator<Position> iterator = matrixBoard.positionIterator();
    private final Set<Figure> usedFigures = new HashSet<>();

    private RandomFigureGenerator randomFigureGenerator;

    @BeforeEach
    void setUp(){
        movableFigures.add(new Amazon(new Position(0,0),0));
        movableFigures.add(new Amazon(new Position(1,0),0));
        movableFigures.add(new Amazon(new Position(2,0),0));
        movableFigures.add(new Amazon(new Position(3,0),1));
        movableFigures.add(new Amazon(new Position(4,0),1));
        movableFigures.add(new Amazon(new Position(5,0),1));

        randomFigureGenerator = new RandomFigureGenerator(random, movableFigures, iterator);
    }

    @Test
    void nextFigure(){
    }

    // test private methods
    /*
    @Test
    void numberOfFiguresToAssign(){
        assertThat(randomFigureGenerator.numberOfFiguresToAssign(movableFigures, usedFigures))
                .isEqualTo(movableFigures.size());

        usedFigures.add((Figure) movableFigures.get(0));
        usedFigures.add((Figure) movableFigures.get(1));

        assertThat(randomFigureGenerator.numberOfFiguresToAssign(movableFigures, usedFigures))
                .isEqualTo(movableFigures.size() -2);

    }
     */

    /*
    @Test
    void calculateRandomProbability(){
        int randomProbability = randomFigureGenerator.calculateRandomProbability(random, movableFigures, usedFigures, iterator);
        assertThat(randomProbability).isBetween(0, 15);

        usedFigures.add((Figure)movableFigures.get(0));
        usedFigures.add((Figure)movableFigures.get(1));

        randomProbability = randomFigureGenerator.calculateRandomProbability(random, movableFigures, usedFigures, iterator);
        assertThat(randomProbability).isBetween(0, 25);

        final MatrixBoard matrixBoardTwoByThree = new MatrixBoard(2,3);
        iterator = matrixBoardTwoByThree.positionIterator();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();
        iterator.next();

        usedFigures.add((Figure)movableFigures.get(2));
        usedFigures.add((Figure)movableFigures.get(3));
        usedFigures.add((Figure)movableFigures.get(4));

        randomProbability = randomFigureGenerator.calculateRandomProbability(random, movableFigures, usedFigures, iterator);
        assertThat(randomProbability).isEqualTo(0);

        usedFigures.add((Figure)movableFigures.get(5));

        randomProbability = randomFigureGenerator.calculateRandomProbability(random, movableFigures, usedFigures, iterator);
        assertThat(randomProbability).isEqualTo(1);
    }
     */

}
