package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;

import java.util.List;

public abstract class MovableFigure {

    protected abstract List<Position> getAccessiblePositions(Board board);
    public abstract Position getPosition();
}
