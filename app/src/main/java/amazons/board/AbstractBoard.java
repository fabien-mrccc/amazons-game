package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.ArrowFigure;
import amazons.figures.Amazon;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public abstract class AbstractBoard implements Board{
    private final int numberOfColumns;
    private final int numberOfRows;

    public AbstractBoard(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        fill(new EmptyFigureGenerator());
    }

    public boolean isEmpty(Position position) {
        if(getFigure(position) == EmptyFigure.EMPTY_FIGURE){
            return true;
        }
        return false;
    }

    public boolean isOutOfBoard(Position position) {
        if(position.isOutOfBounds(numberOfColumns,numberOfRows)){
            return true;
        }
        return false;
    }

    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException {
        if(!getFigure(startPosition).canMoveTo(dstPosition,this)){
            throw new IllegalMoveException("This position is unreachable!");
        }
        if(isEmpty(startPosition)){
            throw new IllegalMoveException("This position is empty!");
        }
        Amazon playedAmazon = (Amazon) getFigure(startPosition);
        if(playedAmazon.getAccessiblePositions(this).contains(dstPosition)){
            playedAmazon.setPosition(dstPosition);
            setFigure(dstPosition,playedAmazon);
            setFigure(startPosition, EmptyFigure.EMPTY_FIGURE);
        }
    }

    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {
        Amazon shootingAmazon = (Amazon) getFigure(startPosition);
        if(!ArrowFigure.ARROW_FIGURE.canMoveTo(arrowDstPosition,this)){
            throw new IllegalMoveException("This position is unreachable!");
        }
        if(shootingAmazon.getAccessiblePositions(this).contains(arrowDstPosition)){
            setFigure(arrowDstPosition,ArrowFigure.ARROW_FIGURE);
        }
    }

    public void fill(FigureGenerator generator){
        for(int x=0; x< getNumberOfColumns(); x++){
            for(int y=0; y< getNumberOfRows(); y++){
                setFigure(new Position(x,y), generator.nextFigure(new Position(x,y)));
            }
        }
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }
    public int getNumberOfRows(){
        return numberOfRows;
    }

    public abstract void setFigure(Position position, Figure figure);

    public abstract Figure getFigure(Position position);

}
