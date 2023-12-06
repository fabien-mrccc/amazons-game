package amazons.player;

import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.board.Board;
import amazons.board.PresetFigureGenerator;
import amazons.game.Game;

import java.util.List;

public abstract class AbstractAIPlayer extends AbstractPlayer{

    protected Board aiBoardRepresentation;

    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        super.initialize(boardWidth, boardHeight, playerID, initialPositions);
        instantiateAIBoard(initialPositions);
    }

    private void instantiateAIBoard(List<Position>[] initialPositions){
        aiBoardRepresentation = new MatrixBoard(boardWidth,boardHeight);
        PresetFigureGenerator generator = new PresetFigureGenerator(Game.createPlayersFiguresWithDefaultPosition(initialPositions));
        aiBoardRepresentation.fill(generator);
    }

    @Override
    public abstract Move play(Move opponentMove);
}
