package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public class MatrixBoard implements Board {
    private final int numberOfColumns;
    private final int numberOfRows;
    private Figure[][] figures ;
    public MatrixBoard(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        for(int x=0; x< numberOfColumns; x++){
            for(int y=0; y< numberOfRows; y++){
                figures[x][y] = EmptyFigure.EMPTY_FIGURE;
            }
        }
    }
    @Override
    public void setFigure(Position position, Figure figure) {
        if(!isOutOfBoard(position)) {
            figures[position.getX()][position.getY()] = figure;
        }
    }

    @Override
    public Figure getFigure(Position position) {
        return figures[position.getX()][position.getY()];
    }

    @Override
    public boolean isEmpty(Position position) {
        if(!isOutOfBoard(position)) {
            if (figures[position.getX()][position.getY()] == EmptyFigure.EMPTY_FIGURE) {
                return true;
            }
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
}
