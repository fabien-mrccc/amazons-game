package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

public class EmptyFigure implements Figure {
    public static EmptyFigure EMPTY_FIGURE;
    private Position position;
    private EmptyFigure(){
        EMPTY_FIGURE = new EmptyFigure();
    }
    @Override
    public boolean canMoveTo(Position position, Board board) {

        return false;
    }

    @Override
    public void moveTo(Position position, Board board) {

    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public PlayerID getPlayerID() {
        return null;
    }
}
