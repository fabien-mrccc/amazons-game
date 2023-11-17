package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

//TODO complete the code this class and its documentation

public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");

    private int x; // x is considered as the column number of the position;
    private int y; // y is considered as the row number of the position;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public int getX() {return x;}
    public int getY() {return y;}


    /**
     * check if the position of the piece is contained in the chessboard
     * @param numberOfColumns
     * @param numberOfRows
     * @return true if it's not contained in the chessboard
     */
    public boolean isOutOfBounds(int numberOfColumns, int numberOfRows){
        if(x<0 || x>= numberOfColumns || y<0 || y>= numberOfRows){
            return true;
        }
        return false;
    }

    /**
     * return next position of this position in a chosen direction
     * @param direction
     * @return next position
     */
    public Position next(CardinalDirection direction) {
        return new Position(this.x + direction.deltaColumn, this.y + direction.deltaRow);
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
    
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }
    public boolean equals (Object other){
        if(!(other instanceof Position p)){
            return false;
        }
        if (other == null) {
            return false;
        }
        if (this.x == ((Position)other).x && this.y == ((Position) other).y) {
            return true;
        }
        return false;
    }

}