package amazons.figures;

import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.List;

public class Amazon extends MovableFigure implements Figure{
    private Position position;
    private final PlayerID playerID;

    public Amazon(Position position, int playerIDIndex){
        setPosition(position);
        this.playerID = PlayerID.getPlayerIDFromIndex(playerIDIndex);
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
        return playerID;
    }

    /**
     * Returns the list of positions that the Amazon can reach from its current position and according to the pieces already on the board.
     * @param board
     * @return list of positions that the Amazon can reach from its current position
     */
    @Override
    public List<Position> getAccessiblePositions(Board board) {
        return null;
    }
}
