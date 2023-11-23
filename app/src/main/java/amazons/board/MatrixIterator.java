package amazons.board;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
    private final int numberOfColumns;
    private final int numberOfRows;
    private final Position lastPosition;
    private T[][] matrix;
    private Position currentPosition = new Position(0, 0);

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.lastPosition = new Position(numberOfColumns - 1, numberOfRows - 1);
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        if (currentPosition == lastPosition) {
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (currentPosition.getX() == numberOfColumns - 1) {
            return getT(new Position(0, currentPosition.getY() + 1));
        }
        return getT(new Position(currentPosition.getX() + 1, currentPosition.getY()));
    }

    public T getT(Position position) {
        return matrix[position.getX()][position.getY()];
    }
    /*
    private final int numberOfColumns;
    private final int numberOfRows;
    private T[][] matrix;
    private T currentT;

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        if (currentT.hashCode()== new Position(numberOfColumns,numberOfRows).hashCode()) {
            return false;
        }
        return true;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (currentT.hashCode() == new Position()) {
            return getT(new Position(0, currentPosition.getY() + 1));
        }
        return getT(new Position(currentPosition.getX() + 1, currentPosition.getY()));
    }

    public T getT(Position position) {
        return matrix[position.getX()][position.getY()];
    }*/
}
