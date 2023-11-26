package amazons.board;
import java.util.*;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;

public class RandomFigureGenerator implements FigureGenerator{
    private final Set<MovableFigure> movableFiguresSet;
    private final Random random;
    private final Iterator<Position> positionIterator;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFiguresList, Iterator<Position> positionIterator){
        this.movableFiguresSet = new HashSet<>();
        this.movableFiguresSet.addAll(movableFiguresList);
        this.random = random;
        this.positionIterator = positionIterator;
    }

    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
