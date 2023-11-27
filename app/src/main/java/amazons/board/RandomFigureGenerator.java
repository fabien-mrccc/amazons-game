package amazons.board;
import java.util.*;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

public class RandomFigureGenerator implements FigureGenerator{
    private final List<MovableFigure> movableFigures;
    private final Random random;
    private final Iterator<Position> positionIterator;
    private Set<Figure> usedFigures;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFigures, Iterator<Position> positionIterator){
        this.movableFigures = movableFigures;
        Collections.shuffle(this.movableFigures);

        this.random = random;
        this.positionIterator = positionIterator;
        usedFigures = new HashSet<>();
    }

    @Override
    public Figure nextFigure(Position position) {
        try {
            positionIterator.next();
            int randomProbability = calculateRandomProbability(movableFigures, usedFigures, positionIterator);

            if(random.nextInt() % randomProbability == 0){
                int tempNumberOfFiguresToAssign = numberOfFiguresToAssign(movableFigures, usedFigures);
                usedFigures.add( (Figure) movableFigures.get(tempNumberOfFiguresToAssign) );
                return (Figure) movableFigures.get(tempNumberOfFiguresToAssign);
            }
            return EmptyFigure.EMPTY_FIGURE;
        }
        catch (NoSuchElementException exception){
            return null;
        }
    }

    private int calculateRandomProbability(List<MovableFigure> movableFigures, Set<Figure> usedFigures, Iterator<Position> positionIterator){
        MatrixIterator matrixIterator = (MatrixIterator) positionIterator;

        // 3x3 for a board of 9 squares
        // [2][2] (last square) -> 2x3 squares assigned + 2x1  = 8 squares assigned
        // numberOfSquaresToAssign = 9 - 8 = 1

        int numberOfSquaresToAssign =
                matrixIterator.getNumberOfColumns() * matrixIterator.getNumberOfRows()
                - (
                        matrixIterator.getCurrentPosition().getX() * matrixIterator.getNumberOfColumns()
                        + matrixIterator.getCurrentPosition().getY()
                );

        return numberOfSquaresToAssign / numberOfFiguresToAssign(movableFigures, usedFigures);
    }

    private int numberOfFiguresToAssign(List<MovableFigure> movableFigures, Set<Figure> usedFigures){
        return movableFigures.size() - usedFigures.size();
    }
}
