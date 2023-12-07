package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAIPlayer extends AbstractPlayer {

    protected List<Amazon> opponentAmazons;

    @Override
    public final void initialize(int boardWidth, int boardHeight, PlayerID playerID, List<Position>[] initialPositions) {
        super.initialize(boardWidth, boardHeight, playerID, initialPositions);
        fillOpponentAmazonsList(initialPositions[playerID.opponent().index]);
    }

    @Override
    public final Move play(Move opponentMove) {
        Position amazonStartPosition = startPositionOfAmazonToMove();
        Position amazonDestPosition = destPositionOfAmazonToMove(amazonStartPosition);
        updateBoardAmazonCase(amazonStartPosition,amazonDestPosition);
        Position arrowDestinationPosition = destPositionOfArrowToShoot(amazonDestPosition);
        updateBoardArrowCase(amazonDestPosition, arrowDestinationPosition);
        return new Move(amazonStartPosition, amazonDestPosition, arrowDestinationPosition);
    }

    private void fillOpponentAmazonsList(List<Position> initialPositions){
        opponentAmazons = new ArrayList<>();
        Amazon amazonToAdd;
        for(Position position : initialPositions){
            amazonToAdd = (Amazon) boardRepresentation.getFigure(position);
            opponentAmazons.add(amazonToAdd);
        }
    }
    protected List<Position> getAdjacentPositions(Position position){
        List<Position> adjacentPositions = new ArrayList<>();
        for(int i= position.columnIndex()-1; i< position.columnIndex()+2;i++){
            for(int j= position.rowIndex()-1; j< position.rowIndex()+2;j++){
                if(new Position(i,j).isOutOfBounds(boardWidth,boardHeight)){continue;}
                adjacentPositions.add(new Position(i,j));
            }
        }
        adjacentPositions.remove(position);
        return adjacentPositions;
    }

    protected abstract Position startPositionOfAmazonToMove();
    protected abstract Position destPositionOfAmazonToMove(Position startPosition);
    protected abstract Position destPositionOfArrowToShoot(Position startPosition);
}