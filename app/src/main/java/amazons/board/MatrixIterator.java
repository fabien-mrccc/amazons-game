package amazons.board;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private final int numberOfColumns;
    private final int numberOfRows;
    private final Position lastPosition;
    private T[][] matrix;
    private Position currentPosition = new Position(0,0);
    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.lastPosition = new Position(numberOfColumns-1, numberOfRows-1);
        this.matrix = matrix;
    }
    @Override
    public boolean hasNext() {
        if(currentPosition == lastPosition){
            return false;
        }
        return true;
    }

    @Override
    public T next() {

        return  ;
    }

    public T getT(Position position){
        return matrix[position.getX()][position.getY()];
    }
}
