package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.List;

public abstract class MovableFigure {

    abstract public List<Position> getAccessiblePositions(Board board);
    /**
     * get the position of the amazon
     * @return position
     */
    abstract public Position getPosition();
}
