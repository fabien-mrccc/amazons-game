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
        return board.isEmpty(position);
    }

    @Override
    public void moveTo(Position position, Board board) {

    }

    @Override
    public void setPosition(Position position) {

    }

    @Override
    public PlayerID getPlayerID() {
        return null;
    }
}
