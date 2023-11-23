package amazons.board;

import javax.swing.text.html.HTMLDocument;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
    private final int numberOfColumns;
    private final int numberOfRows;
    private final Position lastPosition;
    private final T[][] matrix;
    private Position currentPosition = new Position(0, 0);

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.lastPosition = new Position(numberOfColumns - 1, numberOfRows - 1);
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return !currentPosition.equals(lastPosition);
    }

    //TODO Demander à notre chargée de TP si c'est normal que next() doive mettre à jour currentPosition alors que le commentaire dans l'interface demande un simple retour
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (currentPosition.getX() == numberOfColumns - 1) {
            currentPosition = new Position(0, currentPosition.getY() + 1);
        }
        else{
            currentPosition = new Position(currentPosition.getX() + 1, currentPosition.getY());
        }
        return getTInMatrix(currentPosition);
    }

    public T getTInMatrix(Position position) {
        return matrix[position.getX()][position.getY()];
    }
}
