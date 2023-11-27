package amazons.board;
import java.util.*;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

public class RandomFigureGenerator implements FigureGenerator{
    private final List<MovableFigure> movableFigures;
    private final Random random;
    private final Iterator<Position> positionIterator;
    private Set<Figure> usedFigure;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFigures, Iterator<Position> positionIterator){
        this.movableFigures = movableFigures;
        this.random = random;
        this.positionIterator = positionIterator;
        this.usedFigure = new HashSet<>();
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
