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
        return getRandomElement(new Random(), bestAmazonDestinations(bestAmazonToMove()));
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        bestAmazonToMove().setPosition(destPositionOfAmazonToMove(bestAmazonToMove().getPosition()));
        return getRandomElement(new Random(), bestShootPositions(bestAmazonToMove()));
    }

    /**
     * return the number of available positions in the board of the game for an amazon given in parameters
     * @param amazon
     * @return numberOfAvailablePositions
     */
    public int getScore(Amazon amazon){
        int score = 0;
        for(Position position:amazon.getAccessiblePositions(aiBoardRepresentation)){
            score = score + 1;
        }
        return score;
    }
    /**
     * return the best position to move at depending on the bestShootPosition and the number of arrows arround the position
     * @return bestMovePosition
     */
    public Amazon bestAmazonToMove() {
        int smallerPlayerScore = boardHeight*boardWidth;
        Amazon amazonToMove = null;
        for (Amazon playerAmazon : aiPlayerAmazons) {
            if (smallerPlayerScore > getScore(playerAmazon)) {
                if (playerAmazon.getAccessiblePositions(aiBoardRepresentation).size() != 0) {
                    smallerPlayerScore = getScore(playerAmazon);
                    amazonToMove = playerAmazon;
                }
            }
        }

        return amazonToMove;
    }
    public List<Position> bestAmazonDestinations(Amazon playerAmazon){

        List<Position> bestPositionsToMoveIn = new ArrayList<>();
        int biggerAccessibleOpponentAdjacentPositionsNum = 0;

        for(Position position: playerAmazon.getAccessiblePositions(aiBoardRepresentation)){
            for(Amazon opponentAmazon: opponentAmazons) {
                Amazon amazon = new Amazon(position, playerID.index);
                List<Position> opponentAdjacentPositions = getAdjacentPositions(opponentAmazon.getPosition());
                int accessibleOpponentAdjacentPositionsNum = getAccessibleOpponentAdjacentPositionsNum(amazon,opponentAdjacentPositions);
                if(biggerAccessibleOpponentAdjacentPositionsNum < accessibleOpponentAdjacentPositionsNum){
                    biggerAccessibleOpponentAdjacentPositionsNum = accessibleOpponentAdjacentPositionsNum;
                    bestPositionsToMoveIn = amazon.getAccessiblePositions(aiBoardRepresentation, opponentAdjacentPositions);
                }
            }
        }
        if(bestPositionsToMoveIn.isEmpty()){
            bestPositionsToMoveIn = playerAmazon.getAccessiblePositions(aiBoardRepresentation);
        }
        return bestPositionsToMoveIn;
    }
    private int getAccessibleOpponentAdjacentPositionsNum(Amazon amazon,List<Position> opponentAdjacentPositions){
        int accessibleOpponentAdjacentPositionsNum =0;
        for(Position adjacentPosition: opponentAdjacentPositions) {
            if (amazon.getAccessiblePositions(aiBoardRepresentation).contains(adjacentPosition)) {
                accessibleOpponentAdjacentPositionsNum = accessibleOpponentAdjacentPositionsNum +1;
            }
        }
        return accessibleOpponentAdjacentPositionsNum;
    }

    /**
     * return the best shoot position to reduce the opponent playing choices
     * @return bestShootPosition
     */
    public List<Position> bestShootPositions(Amazon playerAmazon){
        int biggerOpponentScore = 0;
        List<Position> positionsToChooseFrom = new ArrayList<>();
        for(Amazon opponentAmazon: opponentAmazons){
            if (biggerOpponentScore < getScore(opponentAmazon)) {
                if (playerAmazon.getAccessiblePositions(aiBoardRepresentation,getAdjacentPositions(opponentAmazon.getPosition())).size() != 0) {
                    biggerOpponentScore = getScore(opponentAmazon);
                    positionsToChooseFrom = playerAmazon.getAccessiblePositions(aiBoardRepresentation,getAdjacentPositions(opponentAmazon.getPosition()));
                }
            }
        }
        if(positionsToChooseFrom.isEmpty()){
            positionsToChooseFrom = playerAmazon.getAccessiblePositions(aiBoardRepresentation);
        }
        return positionsToChooseFrom;
    }

}
