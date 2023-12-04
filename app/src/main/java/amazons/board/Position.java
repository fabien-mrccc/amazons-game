package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;

public record Position(int columnIndex, int rowIndex) implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");


    /**
     * Check if the position of the piece is contained in the chessboard
     *
     * @param numberOfColumns numberOfColumns of the board
     * @param numberOfRows    numberOfRows of the board
     * @return true if it's not contained in the chessboard
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows) {
        return columnIndex < 0 || columnIndex >= numberOfColumns || rowIndex < 0 || rowIndex >= numberOfRows;
    }

    /**
     * Return the next position of this position in a chosen direction
     * @param direction chosen direction
     * @return next position
     */
    public Position next(CardinalDirection direction) {
        return new Position(this.columnIndex + direction.deltaColumn, this.rowIndex + direction.deltaRow);
    }

    @Override
    public String toString() {
        return "(" + columnIndex + "," + rowIndex + ")";
    }

    public CardinalDirection getDirection(Position destPosition) {
        return CardinalDirection.getDirection(columnIndex, rowIndex, destPosition.columnIndex, destPosition.rowIndex);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Position)) {
            return false;
        }
        return this.columnIndex == ((Position) other).columnIndex && this.rowIndex == ((Position) other).rowIndex;
    }

}