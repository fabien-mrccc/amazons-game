package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.Amazon;
import amazons.figures.Figure;

import java.util.Iterator;

import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;

public abstract class AbstractBoard implements Board{
    private final int numberOfColumns;
    private final int numberOfRows;

    public AbstractBoard(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        instantiateBoard();
        fill(new EmptyFigureGenerator());
    }

    @Override
    public boolean isEmpty(Position position) {
        return getFigure(position).equals(EMPTY_FIGURE);
    }

    @Override
    public boolean isOutOfBoard(Position position) {
        return position.isOutOfBounds(getNumberOfColumns(), getNumberOfRows());
    }

    @Override
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException {
        moveCheck(startPosition, dstPosition);

        Amazon playedAmazon = (Amazon) getFigure(startPosition);
        playedAmazon.moveTo(dstPosition, this);
        setFigure(dstPosition,playedAmazon);
        setFigure(startPosition, EMPTY_FIGURE);
    }

    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {
        moveCheck(startPosition, arrowDstPosition);

        setFigure(arrowDstPosition,ARROW_FIGURE);
    }

    /**
     * Common method for checking the validity of a move/shoot operation
     * (factor moveFigure() and shootArrow() methods).
     * @param startPosition The starting position of the figure.
     * @param dstPosition The destination position of the figure.
     * @throws IllegalMoveException Thrown if the move is invalid or the destination is unreachable.
     */
    private void moveCheck(Position startPosition, Position dstPosition) throws IllegalMoveException{
        if(!getFigure(startPosition).canMoveTo(dstPosition,this)){
            throw new IllegalMoveException("Your move/shoot is forbidden or your moving/shooting figure is neutral!");
        }

        Amazon amazonOrArrow = (Amazon) getFigure(startPosition);
        if(!amazonOrArrow.getAccessiblePositions(this).contains(dstPosition)){
            throw new IllegalMoveException("This position is unreachable!");
        }
    }

    @Override
    public void fill(FigureGenerator generator){
        for(int x=0; x< getNumberOfColumns(); x++){
            for(int y=0; y< getNumberOfRows(); y++){
                setFigure(new Position(x,y), generator.nextFigure(new Position(x,y)));
            }
        }
    }

    @Override
    public Iterator<Figure> iterator(){
        return new MatrixIterator<>(getNumberOfColumns(),getNumberOfRows(),getFigureMatrix());
    }

    @Override
    public Iterator<Position> positionIterator(){
        return new MatrixIterator<>(getNumberOfColumns(),getNumberOfRows(),getPositionMatrix());
    }

    /**
     * Make a matrix that contains all the positions on the board depending on number of columns and rows
     * @return a matrix that contains all positions on the board
     */
    public Position[][] getPositionMatrix() {
        Position[][] boardPositions = new Position[getNumberOfColumns()][getNumberOfRows()];
        for(int x=0; x< boardPositions.length; x++){
            for(int y=0; y< boardPositions[0].length; y++){
                boardPositions[x][y] = new Position(x, y);
            }
        }
        return boardPositions;
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }

    public abstract void setFigure(Position position, Figure figure);

    public abstract Figure getFigure(Position position);

    /**
     * Make an optional conversion to return a matrix that contains all figures on the board
     * @return a matrix that contains all figures on the board
     */
    public abstract Figure[][] getFigureMatrix();

    public abstract void instantiateBoard();
}
