package amazons.board;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class MatrixIterator<T> implements Iterator<T> {
    private final int NUMBER_OF_COLUMNS;
    private final int NUMBER_OF_ROWS;
    private final Position lastPosition;
    private final T[][] matrix;
    private Position currentPosition = new Position(0, 0);

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.NUMBER_OF_COLUMNS = numberOfColumns;
        this.NUMBER_OF_ROWS = numberOfRows;
        this.lastPosition = new Position(NUMBER_OF_COLUMNS - 1, NUMBER_OF_ROWS - 1);
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return currentPosition.rowIndex() <= lastPosition.rowIndex();
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        final Position returnPosition = currentPosition;
        updateCurrentPosition();
        return getObjectInMatrix(returnPosition);
    }

    private void updateCurrentPosition(){
        if (currentPosition.columnIndex() == lastPosition.columnIndex()) {
            currentPosition = new Position(0, currentPosition.rowIndex() + 1);
        }
        else{
            currentPosition = new Position(currentPosition.columnIndex() + 1, currentPosition.rowIndex());
        }
    }

    private T getObjectInMatrix(Position position) {
        return matrix[position.columnIndex()][position.rowIndex()];
    }

    public int getNumberOfColumns(){return NUMBER_OF_COLUMNS;}
    public int getNumberOfRows(){return NUMBER_OF_ROWS;}
}
