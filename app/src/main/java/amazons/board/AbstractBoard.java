package amazons.board;

import amazons.figures.IllegalMoveException;
import amazons.figures.Amazon;
import amazons.figures.Figure;

import java.util.Iterator;

import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;

public abstract class AbstractBoard implements Board{
    public final int NUMBER_OF_COLUMNS;
    public final int NUMBER_OF_ROWS;

    public AbstractBoard(int numberOfColumns, int numberOfRows){
        this.NUMBER_OF_COLUMNS = numberOfColumns;
        this.NUMBER_OF_ROWS = numberOfRows;
        instantiateBoard();
        fill(new EmptyFigureGenerator());
    }

    @Override
    public boolean isEmpty(Position position) {
        return getFigure(position).equals(EMPTY_FIGURE);
    }

    @Override
    public boolean isOutOfBoard(Position position) {
        return position.isOutOfBounds(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
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
     * Common method for checking the validity of a move/shoot operation.
     * @param startPosition: the starting position of the figure
     * @param dstPosition: the destination position of the figure
     * @throws IllegalMoveException: thrown if the move is invalid or the destination is unreachable
     */
    private void moveCheck(Position startPosition, Position dstPosition) throws IllegalMoveException{
        if(!getFigure(startPosition).canMoveTo(dstPosition,this)){
            throw new IllegalMoveException("Your move/shoot is forbidden! You have chosen the wrong figure to move OR the destination position is occupied!");
        }

        Amazon amazonOrArrow = (Amazon) getFigure(startPosition);
        if(!amazonOrArrow.getAccessiblePositions(this).contains(dstPosition)){
            throw new IllegalMoveException("This position is unreachable!");
        }
    }

    @Override
    public void fill(FigureGenerator generator){
        for(int x=0; x< NUMBER_OF_COLUMNS; x++){
            for(int y=0; y< NUMBER_OF_ROWS; y++){
                setFigure(new Position(x,y), generator.nextFigure(new Position(x,y)));
            }
        }
    }

    @Override
    public Iterator<Figure> iterator(){
        return new MatrixIterator<>(NUMBER_OF_COLUMNS,NUMBER_OF_ROWS, getMatrixOfFigures());
    }

    @Override
    public Iterator<Position> positionIterator(){
        return new MatrixIterator<>(NUMBER_OF_COLUMNS,NUMBER_OF_ROWS,getPositionMatrix());
    }

    /**
     * Make a matrix that contains all the positions on the board depending on number of columns and rows.
     * @return a matrix that contains all positions on the board
     */
    public Position[][] getPositionMatrix() {
        Position[][] boardPositions = new Position[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
        for(int x=0; x< boardPositions.length; x++){
            for(int y=0; y< boardPositions[0].length; y++){
                boardPositions[x][y] = new Position(x, y);
            }
        }
        return boardPositions;
    }

    public abstract void setFigure(Position position, Figure figure);

    public abstract Figure getFigure(Position position);

    public abstract Figure[][] getMatrixOfFigures();
    public abstract void instantiateBoard();
    public abstract String toString();

    public int getNumberOfColumns(){
        return NUMBER_OF_COLUMNS;
    }

    public int getNumberOfRows(){
        return NUMBER_OF_ROWS;
    }
}
