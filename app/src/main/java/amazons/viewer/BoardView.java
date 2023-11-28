package amazons.viewer;

import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.controller.GameController;
import amazons.player.Move;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BoardView extends GridPane{
    private final Map<Position, FieldView> fieldMap = new HashMap<>();


    public BoardView(GameController controller) {
        Board board = controller.getBoard();

        for (Iterator<Position> it = board.positionIterator(); it.hasNext(); ) {
            Position position = it.next();
            FieldView field = new FieldView(position,controller);
            add(field, position.getX(), position.getY());
            fieldMap.put(position,field);
        }

    }

    public void updateFields() {
        for (FieldView field: fieldMap.values()) {
            field.setFigureIcon();
        }
    }

    /*
    the move is supposed to be valid
    board has already been updated
     */
    public void showMove(Move move) {
        fieldMap.get(move.getAmazonStartPosition()).setFigureIcon();
        highlightAmazonMove(move.getAmazonStartPosition(),move.getAmazonDstPosition());
        fieldMap.get(move.getAmazonDstPosition()).setFigureIcon();
        highlightArrowShoot(move.getAmazonDstPosition(), move.getArrowDstPosition());
        fieldMap.get(move.getArrowDstPosition()).setFigureIcon();
    }

    private void highlightAmazonMove(Position startPosition, Position destPosition){
        CardinalDirection direction = startPosition.getDirection(destPosition);
        for(Position position = startPosition; !position.equals(destPosition); position = position.next(direction)) {
            fieldMap.get(position).setHighlightBackGroundColorTemp();
        }
        fieldMap.get(destPosition).setHighlightBackGroundColorTemp();
    }

    private void highlightArrowShoot(Position amazonPosition, Position arrowDestPosition){
        CardinalDirection direction  = amazonPosition.getDirection(arrowDestPosition);
        for(Position position = amazonPosition.next(direction); !position.equals(arrowDestPosition); position =  position.next(direction)){
            fieldMap.get(position).setHighlightShootBackGroundColorTemp();
        }
        fieldMap.get(arrowDestPosition).setHighlightShootBackGroundColorTemp();
    }

}
