package amazons.board;
import java.util.*;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;
public class RandomFigureGenerator implements FigureGenerator{
    private final List<MovableFigure> movableFigures;
    private final Random random;
    private final Iterator<Position> positionIterator;
    private final Set<Figure> usedFigures;

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
            int randomProbability = calculateRandomProbability(random, movableFigures, usedFigures, (MatrixIterator<Position>) positionIterator);

            if(randomProbability == 0){
                int tempNumberOfFiguresToAssign = numberOfFiguresToAssign(movableFigures, usedFigures);
                usedFigures.add( (Figure) movableFigures.get(tempNumberOfFiguresToAssign) );
                return (Figure) movableFigures.get(tempNumberOfFiguresToAssign);
            }
            return EMPTY_FIGURE;
        }
        catch (NoSuchElementException exception){
            return null;
        }
    }

    private int calculateRandomProbability(Random random, List<MovableFigure> movableFigures, Set<Figure> usedFigures, MatrixIterator<Position> matrixIterator){
        int numberOfSquaresBoard = matrixIterator.getNumberOfColumns() * matrixIterator.getNumberOfRows(); // 3x3 for a board of 9 squares

        int numberOfSquaresAssigned = matrixIterator.getCurrentPosition().getY()
                * matrixIterator.getNumberOfColumns()
                + matrixIterator.getCurrentPosition().getX(); // [2][2] (last square) -> 2x3 squares assigned + 2x1  = 8 squares assigned

        int numberOfSquaresToAssign = numberOfSquaresBoard - numberOfSquaresAssigned; // numberOfSquaresToAssign = 9 - 8 = 1

        int numberOfFiguresToAssign = numberOfFiguresToAssign(movableFigures, usedFigures);

        if(numberOfFiguresToAssign == 0){
            return 1; // return 1 to inform that our next figure to assign is empty
        }

        int numberOfSquaresToAssignPerFigure = numberOfSquaresToAssign / numberOfFiguresToAssign(movableFigures, usedFigures);

        return Math.abs(random.nextInt()) % numberOfSquaresToAssignPerFigure; // return value between 0 and numberOfSquaresToAssignPerFigure -1
    }

    private int numberOfFiguresToAssign(List<MovableFigure> movableFigures, Set<Figure> usedFigures){
        return movableFigures.size() - usedFigures.size();
    }
}
