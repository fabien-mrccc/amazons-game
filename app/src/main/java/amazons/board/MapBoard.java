package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.Amazon;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import java.util.Map;

public class MapBoard implements Board{
    private final int numberOfColumns;
    private final int numberOfRows;
    private Map<Position,Figure> figures;

    public MapBoard(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        fill(new EmptyFigureGenerator());
    }

    @Override
    public void setFigure(Position position, Figure figure) {
        figures.put(position,figure);
    }

    @Override
    public Figure getFigure(Position position) {
        return figures.get(position);
    }

    @Override
    public boolean isEmpty(Position position) {
        if(getFigure(position) == EmptyFigure.EMPTY_FIGURE){
            return true;
        }
        return false;
    }

    @Override
    public boolean isOutOfBoard(Position position) {
        if(position.isOutOfBounds(numberOfColumns,numberOfRows)){
            return true;
        }
        return false;
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }
    public int getNumberOfRows(){
        return numberOfRows;
    }

    @Override
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException {

        if(!figures.get(startPosition).canMoveTo(dstPosition,this)){
            throw new IllegalMoveException("This position is unreachable!");
        }
        if(isEmpty(startPosition)){
            throw new IllegalMoveException("This position is empty!");
        }
        Amazon amazon = (Amazon) figures.get(startPosition);
        if(amazon.getAccessiblePositions(this).contains(dstPosition)){
            amazon.setPosition(dstPosition);
            setFigure(dstPosition,amazon);
            setFigure(startPosition, EmptyFigure.EMPTY_FIGURE);
        }
    }

    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {

    }

    @Override
    public void fill(FigureGenerator generator) {

    }
}
