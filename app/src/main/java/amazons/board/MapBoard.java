package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.Figure;

import java.util.Map;

public class MapBoard implements Board{
    private Map<Position,Figure> squares;
    @Override
    public void setFigure(Position position, Figure figure) {
        squares.put(position,figure);
    }

    @Override
    public Figure getFigure(Position position) {
        return squares.get(position);
    }

    @Override
    public boolean isEmpty(Position position) {
        return false;
    }

    @Override
    public boolean isOutOfBoard(Position position) {
        return false;
    }

    @Override
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException {

    }

    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {

    }

    @Override
    public void fill(FigureGenerator generator) {

    }
}
