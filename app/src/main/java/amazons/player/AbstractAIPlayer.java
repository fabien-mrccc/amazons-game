package amazons.player;

import amazons.board.Position;
import amazons.game.Game;

import java.util.List;

public abstract class AbstractAIPlayer extends AbstractPlayer{

    private Game aiGameRepresentation;

    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        super.initialize(boardWidth, boardHeight, playerID, initialPositions);
        aiGameRepresentation = new Game(boardWidth, boardHeight);
    }
    @Override
    public abstract Move play(Move opponentMove);
}
