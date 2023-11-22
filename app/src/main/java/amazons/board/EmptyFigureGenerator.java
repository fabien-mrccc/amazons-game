package amazons.board;

import amazons.board.FigureGenerator;
import amazons.board.Position;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public class EmptyFigureGenerator implements FigureGenerator {
    @Override
    public Figure nextFigure(Position position) {
        return EmptyFigure.EMPTY_FIGURE;
    }
}
