package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");
    private final int X;
    private final int Y;

    public Position(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public int getX() {return X;}
    public int getY() {return Y;}


    /**
     * check if the position of the piece is contained in the chessboard
     * @param numberOfColumns numberOfColumns of the board
     * @param numberOfRows numberOfRows of the board
     * @return true if it's not contained in the chessboard
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        return X < 0 || X >= numberOfColumns || Y < 0 || Y >= numberOfRows;
    }

    /**
     * return next position of this position in a chosen direction
     * @param direction chosen direction
     * @return next position
     */
    public Position next(CardinalDirection direction) {
        return new Position(this.X + direction.deltaColumn, this.Y + direction.deltaRow);
    }

    public String toString(){
        return "(" + X + "," + Y + ")";
    }

    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(X, Y, destPosition.X, destPosition.Y);
    }
    public boolean equals (Object other){
        if(!(other instanceof Position)){
            return false;
        }
        return this.X == ((Position) other).X && this.Y == ((Position) other).Y;
    }
    @Override
    public int hashCode(){
        return Objects.hash(X, Y);
    }

}