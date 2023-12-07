package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import static amazons.util.RandomUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIGreedyPlayer extends AbstractAIPlayer {

    @Override
    protected Position startPositionOfAmazonToMove() {
        return bestAmazonToMove().getPosition();
    }

    @Override
    protected Position destPositionOfAmazonToMove(Position startPosition) {
        return bestAmazonDestination(bestAmazonToMove());
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        return bestShootPosition(new Amazon(startPosition, playerID.index));
    }

    /**
     * return the number of available positions in the board of the game for an amazon given in parameters
     * @param amazon for which the score is calculated
     * @return numberOfAvailablePositions
     */
    public int getScore(Amazon amazon){
        int score;
        score = amazon.getAccessiblePositions(boardRepresentation).size();
        return score;
    }
    /**
     * return the best position to move at depending on the bestShootPosition and the number of arrows around the position
     * @return bestMovePosition
     */
    public Amazon bestAmazonToMove() {
        int smallerPlayerScore = boardHeight*boardWidth;
        Amazon amazonToMove = getRandomElement(new Random(), getMovableAmazons());
        for (Amazon playerAmazon : getMovableAmazons()) {
            if (smallerPlayerScore > getScore(playerAmazon)) {
                if (playerAmazon.getAccessiblePositions(boardRepresentation).size() != 0) {
                    smallerPlayerScore = getScore(playerAmazon);
                    amazonToMove = playerAmazon;
                }
            }
        }

        return amazonToMove;
    }
    public Position bestAmazonDestination(Amazon playerAmazon){

        List<Position> bestPositionsToMoveIn = new ArrayList<>();
        int biggerAccessibleOpponentAdjacentPositionsNum = 0;

        for(Position position: playerAmazon.getAccessiblePositions(boardRepresentation)){
            for(Amazon opponentAmazon: opponentAmazons) {
                Amazon amazon = new Amazon(position, playerID.index);
                List<Position> opponentAdjacentPositions = getAdjacentPositions(opponentAmazon.getPosition());
                int accessibleOpponentAdjacentPositionsNum = getAccessibleOpponentAdjacentPositionsNum(amazon,opponentAdjacentPositions);
                if(biggerAccessibleOpponentAdjacentPositionsNum < accessibleOpponentAdjacentPositionsNum){
                    biggerAccessibleOpponentAdjacentPositionsNum = accessibleOpponentAdjacentPositionsNum;
                    bestPositionsToMoveIn.add(amazon.getPosition());
                }
            }
        }
        if(bestPositionsToMoveIn.isEmpty()){
            bestPositionsToMoveIn = playerAmazon.getAccessiblePositions(boardRepresentation);
        }
        return getRandomElement(new Random(), bestPositionsToMoveIn);
    }
    private int getAccessibleOpponentAdjacentPositionsNum(Amazon amazon,List<Position> opponentAdjacentPositions){
        int accessibleOpponentAdjacentPositionsNum =0;
        for(Position adjacentPosition: opponentAdjacentPositions) {
            if (amazon.getAccessiblePositions(boardRepresentation).contains(adjacentPosition)) {
                accessibleOpponentAdjacentPositionsNum = accessibleOpponentAdjacentPositionsNum +1;
            }
        }
        return accessibleOpponentAdjacentPositionsNum;
    }

    /**
     * return the best shoot position to reduce the opponent playing choices
     * @return bestShootPosition
     */
    public Position bestShootPosition(Amazon playerAmazon){
        int biggerOpponentScore = 0;
        List<Position> positionsToChooseFrom = playerAmazon.getAccessiblePositions(boardRepresentation);
        for(Amazon opponentAmazon: opponentAmazons){
            if (biggerOpponentScore < getScore(opponentAmazon)) {
                if (playerAmazon.getAccessiblePositions(boardRepresentation,getAdjacentPositions(opponentAmazon.getPosition())).size() != 0) {
                    biggerOpponentScore = getScore(opponentAmazon);
                    positionsToChooseFrom = playerAmazon.getAccessiblePositions(boardRepresentation,getAdjacentPositions(opponentAmazon.getPosition()));
                }
            }
        }
        return getRandomElement(new Random(), positionsToChooseFrom);
    }

}
