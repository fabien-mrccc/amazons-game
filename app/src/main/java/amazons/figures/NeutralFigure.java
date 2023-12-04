package amazons.figures;

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
        try{
            throw new IllegalSetPositionException("A neutral figure can't set a position");
        }
        catch(IllegalSetPositionException exception){
            System.out.println(exception.getMessage());
        }
    }

    @Override
    public PlayerID getPlayerID() {
        return PlayerID.NONE;
    }
}
