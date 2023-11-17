package amazons.board;

import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

public class MatrixBoard implements Board {
    private Figure[][] figures ;
    public MatrixBoard(int numberOfColumns, int numberOfRows){
        for(int x=0; x< numberOfColumns; x++){
            for(int y=0; y< numberOfRows; y++){
                figures[x][y] = EmptyFigure.EMPTY_FIGURE;
            }
        }
    }
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
