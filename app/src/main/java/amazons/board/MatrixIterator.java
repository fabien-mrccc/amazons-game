package amazons.board;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
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
        return getCurrentPosition().getY() <= getLastPosition().getY();
    }

    //TODO Demander à notre chargée de TP si c'est normal que next() doive mettre à jour currentPosition alors que le commentaire dans l'interface demande un simple retour
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        Position returnPosition = currentPosition;

        if (getCurrentPosition().getX() == getLastPosition().getX()) {
            currentPosition = new Position(0, getCurrentPosition().getY() + 1);
        }
        else{
            currentPosition = new Position(getCurrentPosition().getX() + 1, getCurrentPosition().getY());
        }
        return getTInMatrix(returnPosition);
    }

    public T getTInMatrix(Position position) {
        return matrix[position.getX()][position.getY()];
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public int getNumberOfColumns(){return NUMBER_OF_COLUMNS;}
    public int getNumberOfRows(){return NUMBER_OF_ROWS;}

    public Position getLastPosition(){
        return lastPosition;
    }
}
