package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

public class ArrowFigure implements Figure{
    public static ArrowFigure ARROW_FIGURE;
    private ArrowFigure(){
        ARROW_FIGURE = new ArrowFigure();
    }
    @Override
    public boolean canMoveTo(Position position, Board board) {
        return false;
    }

    @Override
    public void moveTo (Position position, Board board) throws IllegalMoveException {
        throw new IllegalMoveException("ArrowFigure can't be moved");
    }

    @Override
    public void setPosition(Position position) {
        throw new PositionException("ArrowFigure can't have a position set.");
    }

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
