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
    protected List<Amazon> opponentAmazons;

    @Override
    public final void initialize(int boardWidth, int boardHeight, PlayerID playerID, List<Position>[] initialPositions) {
        super.initialize(boardWidth, boardHeight, playerID, initialPositions);
        instantiateAIBoard(initialPositions);
        fillAIPlayerAmazonsList(this.initialPositions);
        fillOpponentAmazonsList(initialPositions[playerID.opponent().index]);
    }

    @Override
    public final Move play(Move opponentMove) {
        Position startPosition = startPositionOfAmazonToMove();
        return new Move(startPosition, destPositionOfAmazonToMove(startPosition), destPositionOfArrowToShoot(startPosition));
    }

    private void instantiateAIBoard(List<Position>[] initialPositions){
        aiBoardRepresentation = new MatrixBoard(boardWidth,boardHeight);
        PresetFigureGenerator generator = new PresetFigureGenerator(Game.createPlayersFiguresWithDefaultPosition(initialPositions));
        aiBoardRepresentation.fill(generator);
    }

    private void fillAIPlayerAmazonsList(List<Position> initialPositions) {
        aiPlayerAmazons = new ArrayList<>();
        Amazon amazonToAdd;
        for(Position position : initialPositions){
            amazonToAdd = (Amazon) aiBoardRepresentation.getFigure(position);
            aiPlayerAmazons.add(amazonToAdd);
        }
    }

    private void fillOpponentAmazonsList(List<Position> initialPositions){
        opponentAmazons = new ArrayList<>();
        Amazon amazonToAdd;
        for(Position position : initialPositions){
            amazonToAdd = (Amazon) aiBoardRepresentation.getFigure(position);
            opponentAmazons.add(amazonToAdd);
        }
    }
    protected List<Position> getAdjacentPositions(Position position){
        List<Position> adjacentPositions = new ArrayList<>();
        for(int i= position.columnIndex()-1; i< position.columnIndex()+2;i++){
            for(int j= position.rowIndex()-1; j< position.rowIndex()+2;j++){
                adjacentPositions.add(new Position(i,j));
            }
        }
        adjacentPositions.remove(position);
        return adjacentPositions;
    }

    protected List<Amazon> getMovableAmazons(){
        List<Amazon> movableAmazons = new ArrayList<>();
        for(Amazon amazon : aiPlayerAmazons){
            if(amazon.getAccessiblePositions(aiBoardRepresentation).size() > 0){
                movableAmazons.add(amazon);
            }
        }
        return movableAmazons;
    }

    protected abstract Position startPositionOfAmazonToMove();
    protected abstract Position destPositionOfAmazonToMove(Position startPosition);
    protected abstract Position destPositionOfArrowToShoot(Position startPosition);
}