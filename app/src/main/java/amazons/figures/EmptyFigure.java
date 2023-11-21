package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

public class EmptyFigure implements Figure {
    public static EmptyFigure EMPTY_FIGURE;
    private EmptyFigure(){
        EMPTY_FIGURE = new EmptyFigure();
    }
    @Override
    public boolean canMoveTo(Position position, Board board) {
        return false;
    }

    @Override
    public void moveTo(Position position, Board board) {
        throw new IllegalArgumentException();
    }

    @Override
    public void setPosition(Position position) {
        throw new PositionException("EmptyFigure can't have a position set.");
    }

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
