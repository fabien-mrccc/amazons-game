package amazons.figures;

import amazons.board.FigureGenerator;
import amazons.board.Position;

public class EmptyFigureGenerator implements FigureGenerator {
    @Override
    public Figure nextFigure(Position position) {
        return EmptyFigure.EMPTY_FIGURE;
    }
}
