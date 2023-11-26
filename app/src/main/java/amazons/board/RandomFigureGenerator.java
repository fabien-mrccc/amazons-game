package amazons.board;
import java.util.Random;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import java.util.List;
import java.util.Iterator;
public class RandomFigureGenerator implements FigureGenerator{
    private List<MovableFigure> movableFigures;
    private Random random;
    private Iterator<Position> positionIterator;

    public RandomFigureGenerator(Random random, List<MovableFigure> movableFigures, Iterator<Position> positionIterator){
        this.movableFigures = movableFigures;
        this.random = random;
        this.positionIterator = positionIterator;
    }

    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
