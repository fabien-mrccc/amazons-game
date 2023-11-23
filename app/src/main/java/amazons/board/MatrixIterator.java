package amazons.board;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;

public class MatrixIterator<T> implements Iterator<T> {
    private int numberOfColumns;
    private int numberOfRows;
    private T[][] matrix;
    private Position currentPosition = new Position(0,0);
    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.matrix = matrix;
    }
    @Override
    public boolean hasNext() {
        if(matrix[])
        return false;
    }

    @Override
    public T next() {
        return  ;
    }

    public T[][] getMatrix(){
        return matrix;
    }
}
