package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public final class EmptyFigureGenerator implements FigureGenerator {
    @Override
    public Figure nextFigure(Position position) {
        return EmptyFigure.EMPTY_FIGURE;
    }
}
