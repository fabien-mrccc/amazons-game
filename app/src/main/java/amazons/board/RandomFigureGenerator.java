package amazons.board;
import java.util.*;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

public class RandomFigureGenerator implements FigureGenerator{
    private final List<MovableFigure> movableFigures;
    private final Random random;
    private final double randomProbability; // 1/randomProbability to generate an amazon on the board
    private final Iterator<Position> positionIterator;
    private Set<Figure> usedFigure;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFigures, Iterator<Position> positionIterator){
        this.movableFigures = movableFigures;
        this.random = random;
        this.positionIterator = positionIterator;
        usedFigure = new HashSet<>();
        randomProbability = calculateRandomProbability(movableFigures, positionIterator);
    }

    private double calculateRandomProbability(List<MovableFigure> movableFigures, Iterator<Position> positionIterator){
        MatrixIterator matrixIterator = (MatrixIterator) positionIterator;
        return (matrixIterator.getLastPosition().getX() * matrixIterator.getLastPosition().getY()) / (double) movableFigures.size();
    }

    @Override
    public Figure nextFigure(Position position) {
        try {
            positionIterator.next();

            if(movableFigures. || random.nextBoolean()){
                return EmptyFigure.EMPTY_FIGURE;
            }
            return movableFigures.;
        }
        catch (NoSuchElementException exception){
            return null;
        }
    }
}
