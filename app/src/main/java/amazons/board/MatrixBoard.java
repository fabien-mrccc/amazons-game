package amazons.board;

import amazons.figures.Figure;

import java.util.Iterator;

public class MatrixBoard extends AbstractBoard {

    private Figure[][] figures ;

    public MatrixBoard(int numberOfColumns, int numberOfRows){
        super(numberOfColumns, numberOfRows);
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
    public Iterator<Figure> iterator() {
        return new MatrixIterator<Figure>(getNumberOfColumns(),getNumberOfRows(),figures);
    }
}
