package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;

import java.util.List;

public abstract class MovableFigure {

    public abstract List<Position> getAccessiblePositions(Board board);
    public abstract Position getPosition();
}
