package amazons.board;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIterator<T> implements Iterator<T> {
    private final Position lastPosition;
    private final T[][] matrix;
    private Position currentPosition = new Position(0, 0);

    public MatrixIterator(int numberOfColumns, int numberOfRows, T[][] matrix) {
        this.lastPosition = new Position(numberOfColumns - 1, numberOfRows - 1);
        this.matrix = matrix;
    }

    @Override
    public boolean hasNext() {
        return !getCurrentPosition().equals(getLastPosition());
    }

    //TODO Demander à notre chargée de TP si c'est normal que next() doive mettre à jour currentPosition alors que le commentaire dans l'interface demande un simple retour
    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (getCurrentPosition().getX() == getLastPosition().getX()) {
            currentPosition = new Position(0, getCurrentPosition().getY() + 1);
        }
        else{
            currentPosition = new Position(getCurrentPosition().getX() + 1, getCurrentPosition().getY());
        }
        return getTInMatrix(getCurrentPosition());
    }

    public T getTInMatrix(Position position) {
        return matrix[position.getX()][position.getY()];
    }

    public Position getCurrentPosition(){
        return currentPosition;
    }

    public Position getLastPosition(){
        return lastPosition;
    }
}
