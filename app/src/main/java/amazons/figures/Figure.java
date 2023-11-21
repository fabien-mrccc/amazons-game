package amazons.figures;

import amazons.IllegalMoveException;
import amazons.board.Board;
import amazons.board.Position;
import amazons.player.PlayerID;

import java.io.Serializable;

public interface  Figure extends Serializable {

    /**
     * Check if this figure can move to {@code position} according to its displacement rules.
     * @param position: a valid position in {@code board}
     * @param board: a board on which this is placed
     * @return {@code true} if this can move to {@code position} given the current state of {@code board}
     * {@code} false otherwise.
     */
    boolean canMoveTo(Position position, Board board);

    /**
     * Move this to the given position. An exception is thrown if the move
     * is illegal according to the rules of the game.
     * @param position: the position to which this should be moved
     */
    void moveTo (Position position, Board board) throws IllegalMoveException;

    /**
     * Set the position of this figure to the given position.
     * @param position: the new position of this figure
     */
    void setPosition(Position position);

    /**
     * Get the ID of the player who owns this figure (maybe NONE if this figure is an arrow or empty)
     * @return the PlayerID of the player who owns this figure
     */
    PlayerID getPlayerID();

}
