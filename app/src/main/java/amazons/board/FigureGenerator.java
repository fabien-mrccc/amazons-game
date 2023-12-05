package amazons.board;

import amazons.figures.Figure;

public interface FigureGenerator {
    /**
     * Return the next figure to generate based on the position in parameter.
     * @param position the position from which the generator needs to choose the next figure
     *                 to return
     * @return the next figure to generate
     */
    Figure nextFigure(Position position);
}