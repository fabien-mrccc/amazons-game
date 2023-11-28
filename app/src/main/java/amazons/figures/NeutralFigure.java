package amazons.figures;

import amazons.IllegalMoveException;
import amazons.IllegalSetPositionException;
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
    public void setPosition(Position position){
        throw new IllegalSetPositionException("A neutral figure can't set a position");
    }

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
