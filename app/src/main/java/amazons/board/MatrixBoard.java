package amazons.board;

import amazons.IllegalMoveException;
import amazons.figures.ArrowFigure;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.Amazon;

public class MatrixBoard implements Board {
    private final int numberOfColumns;
    private final int numberOfRows;
    private Figure[][] figures ;
    public MatrixBoard(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        EmptyFigureGenerator generator = new EmptyFigureGenerator();
        fill(generator);
    }
    @Override
    public void setFigure(Position position, Figure figure) {
        figures[position.getX()][position.getY()] = figure;
    }

    @Override
    public Figure getFigure(Position position) {
        return figures[position.getX()][position.getY()];
    }

    @Override
    public boolean isEmpty(Position position) {
        if (getFigure(position) == EmptyFigure.EMPTY_FIGURE) {
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
    public void moveFigure(Position startPosition, Position dstPosition) throws IllegalMoveException{
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
        }
    }

    @Override
    public void shootArrow(Position startPosition, Position arrowDstPosition) throws IllegalMoveException {
        Amazon shootingAmazon = (Amazon) getFigure(startPosition);
        if(!ArrowFigure.ARROW_FIGURE.canMoveTo(arrowDstPosition,this)){
            throw new IllegalMoveException("This position is unreachable!");
        }
        if(shootingAmazon.getAccessiblePositions(this).contains(arrowDstPosition)){
            setFigure(arrowDstPosition,ArrowFigure.ARROW_FIGURE);
        }
    }

    @Override
    public void fill(FigureGenerator generator){
        for(int x=0; x< numberOfColumns; x++){
            for(int y=0; y< numberOfRows; y++){
                figures[x][y] = generator.nextFigure(new Position(x,y));
            }
        }
    }
}
