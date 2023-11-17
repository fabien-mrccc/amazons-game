package amazons.board;

import amazons.figures.Figure;

public class MatrixBoard implements Board {
    //private Figure[][] = new EmptyFigure();
    @Override
    public void setFigure(Position position, Figure figure) {

    }

    @Override
    public Figure getFigure(Position position) {
        return null;
    }

    @Override
    public boolean isEmpty(Position position) {
        return false;
    }

    @Override
    public boolean isOutOfBoard(Position position) {
        return false;
    }
}
