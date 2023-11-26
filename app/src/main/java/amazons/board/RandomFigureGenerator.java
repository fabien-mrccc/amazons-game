package amazons.board;
import java.util.Random;

import amazons.figures.Figure;
import amazons.figures.MovableFigure;
import java.util.List;
import java.util.Iterator;
public class RandomFigureGenerator implements FigureGenerator{
    private List<MovableFigure> figures;
    public RandomFigureGenerator(Random random, List<MovableFigure> figures, Iterator<Position> positionIterator){
        this.figures = figures;
        for(MovableFigure movableFigure : figures){

        }
    }

    @Override
    public Figure nextFigure(Position position) {
        return null;
    }
}
