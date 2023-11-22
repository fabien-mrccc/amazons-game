package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

public abstract class NeutralFigure implements Figure {
    @Override
    public boolean canMoveTo(Position position, Board board) {
        return false;
    }

    @Override
    public void moveTo (Position position, Board board) throws IllegalMoveException {
        throw new IllegalMoveException("A neutral figure can't be moved");
    }

    @Override
    public void setPosition(Position position) {}

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
