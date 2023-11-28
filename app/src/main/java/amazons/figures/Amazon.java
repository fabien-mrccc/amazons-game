package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.CardinalDirection;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.util.ArrayList;
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
        return getAccessiblePositions(board).contains(position);
    }

    @Override
    public void moveTo(Position position, Board board) throws IllegalMoveException {
        if(canMoveTo(position, board)){
            setPosition(position);
        }
        else{
            throw new IllegalMoveException("Amazon can't be moved");
        }
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

   @Override
    public Position getPosition(){
        return position;
    }

    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }

    /**
     * Returns the list of positions that the Amazon can reach from its current position and according to the pieces already on the board.
     * An Amazon moves at least one square in a straight line horizontally, vertically or diagonally through any number of squares.
     * They can only move through unoccupied squares.
     * This means that no square can be occupied (by an Amazon of any color or by an arrow) between the starting square and the Amazon's destination.
     * @param board the board used in the game
     * @return list of positions that the Amazon can reach from its current position
     */
    @Override
    public List<Position> getAccessiblePositions(Board board) {
        List<Position> reachablePositions = new ArrayList<>();

        for(CardinalDirection direction : CardinalDirection.values()){
            Position positionCheck = this.position.next(direction);

            while(!board.isOutOfBoard(positionCheck)){
                if(!board.isEmpty(positionCheck)){ break;}
                reachablePositions.add(positionCheck);
                positionCheck = positionCheck.next(direction);
            }
        }

        return reachablePositions;
    }
}
