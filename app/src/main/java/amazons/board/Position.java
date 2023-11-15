package amazons.board;

import javafx.scene.input.DataFormat;

import java.io.Serializable;
import java.util.Objects;

//TODO complete the code this class and its documentation

public class Position implements Serializable {
    public static final DataFormat POSITION_FORMAT = new DataFormat("amazons.position");

    private int x; // x is considered as the column number of the position;
    private int y; // y is considered as the row number of the position;

    //TODO
    public int getX() {return 0;}
    public int getY() {return 0;}


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

    // TODO
    public Position next(CardinalDirection direction) {
        return new Position();
    }
    public CardinalDirection getDirection(Position destPosition){
        return CardinalDirection.getDirection(x,y, destPosition.x, destPosition.y);
    }

}