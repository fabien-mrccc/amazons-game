package amazons.player;

import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.board.Board;
import amazons.board.PresetFigureGenerator;
import amazons.game.Game;
import amazons.figures.Amazon;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAIPlayer extends AbstractPlayer {

    protected Board aiBoardRepresentation;
    protected List<Amazon> aiPlayerAmazons;

    @Override
    public void initialize(int boardWidth, int boardHeight, PlayerID playerID, List<Position>[] initialPositions) {
        super.initialize(boardWidth, boardHeight, playerID, initialPositions);
        instantiateAIBoard(initialPositions);
        fillAIPlayerAmazonsList(this.initialPositions);
    }

    private void instantiateAIBoard(List<Position>[] initialPositions) {
        aiBoardRepresentation = new MatrixBoard(boardWidth, boardHeight);
        PresetFigureGenerator generator = new PresetFigureGenerator(Game.createPlayersFiguresWithDefaultPosition(initialPositions));
        aiBoardRepresentation.fill(generator);
    }

    protected void fillAIPlayerAmazonsList(List<Position> initialPositions) {
        aiPlayerAmazons = new ArrayList<>();
        Amazon amazonToAdd;
        for (Position position : initialPositions) {
            amazonToAdd = (Amazon) aiBoardRepresentation.getFigure(position);
            aiPlayerAmazons.add(amazonToAdd);
        }
    }

    @Override
    public abstract Move play(Move opponentMove);
    protected abstract Amazon getAmazonToMove();
}