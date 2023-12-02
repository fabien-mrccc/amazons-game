package amazons.board;

import amazons.figures.Amazon;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.*;


public class RandomFigureGeneratorTest {

    private final Random random = new Random();
    private final List<MovableFigure> movableFigures = new ArrayList<>();
    private final MatrixBoard matrixBoard = new MatrixBoard(2,3);
    private final Iterator<Position> iterator = matrixBoard.positionIterator();

    //private final Set<Figure> usedFigures = new HashSet<>();

    @BeforeEach
    void setUp(){
        movableFigures.add(new Amazon(new Position(0,0),0));
        movableFigures.add(new Amazon(new Position(1,0),0));
        movableFigures.add(new Amazon(new Position(0,1),0));
        movableFigures.add(new Amazon(new Position(1,1),1));
        movableFigures.add(new Amazon(new Position(0,2),1));
        movableFigures.add(new Amazon(new Position(1,2),1));
    }

    @Test
    void nextFigure(){
        RandomFigureGenerator randomFigureGenerator = new RandomFigureGenerator(random, movableFigures, iterator);

        for(int i=5; i>=0; i--){
            Figure figure = (Figure) randomFigureGenerator.getMovableFigures().get(i);

            assertThat(randomFigureGenerator.nextFigure(new Position(3,9))).satisfiesAnyOf(
                    value -> Assertions.assertThat(value).isEqualTo(EMPTY_FIGURE),
                    value -> Assertions.assertThat(value).isEqualTo(figure));
        }


        final MatrixBoard matrixBoard100x100 = new MatrixBoard(100,100);
        final Iterator<Position> iterator100x100 = matrixBoard100x100.positionIterator();
        Figure figure;

        movableFigures.add(new Amazon(new Position(0,3),0));
        movableFigures.add(new Amazon(new Position(1,3),1));


        randomFigureGenerator = new RandomFigureGenerator(random, movableFigures, iterator100x100);
        int emptyFigureCount = 0;
        int amazonFigureCount = 0;
        int strangeFigureCount = 0;

        for(int i=0; i <10000; i++){
            figure = randomFigureGenerator.nextFigure(new Position(0,0));
            if(figure instanceof EmptyFigure){
                emptyFigureCount++;
            }
            else if (figure instanceof Amazon){
                amazonFigureCount++;
            }
            else{
                strangeFigureCount++;
            }
        }
        assertThat(emptyFigureCount).isEqualTo(9992);
        assertThat(amazonFigureCount).isEqualTo(8);
        assertThat(strangeFigureCount).isEqualTo(0);
    }

    /* Testing private methods
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
