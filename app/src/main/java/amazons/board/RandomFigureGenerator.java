package amazons.board;
import java.util.*;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;

public class RandomFigureGenerator implements FigureGenerator{
    private Set<MovableFigure> movableFiguresSet;
    private Random random;
    private Iterator<Position> positionIterator;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFiguresList, Iterator<Position> positionIterator){
        this.movableFiguresSet = new HashSet<>();
        for (MovableFigure movableFigure : movableFiguresList){
            this.movableFiguresSet.add(movableFigure);
        }
        this.random = random;
        this.positionIterator = positionIterator;
    }

    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
