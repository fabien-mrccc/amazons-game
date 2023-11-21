package amazons.figures;

import amazons.IllegalMoveException;
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
    public void moveTo(Position position, Board board) throws IllegalMoveException {
        throw new IllegalMoveException("EmptyFigure can't be moved");
    }

    @Override
    public void setPosition(Position position){}

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
