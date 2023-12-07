package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.util.RandomUtil.*;

import java.util.ArrayList;
import java.util.List;

public class AIGreedyPlayer extends AbstractAIPlayer {

    @Override
    protected Position startPositionOfAmazonToMove() {
        return bestAmazonToMove().getPosition();
    }

    @Override
    protected Position destPositionOfAmazonToMove(Position startPosition) {
        return null;
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        return null;
    }

    /* maximiser la possibilité de gagner: si l'une de mes amazones est ent
    entourer par une ou plusieurs flèche ou amazones adverses la faire bouger à
    une case vide et entourer de cases vide, sinon aller dans la case la moins entourer.
    minimiser les choix de l'adversaire: chercher l'amazone du joueur adverse avec
    le plus de cases accessible et envoyer la flèche à l'une de ses cases adjacentes..
     */

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
    public Amazon bestAmazonToMove(){
        int smallerPlayerScore = 0;
        Amazon amazonToMove = null;
        for(Amazon playerAmazon: aiPlayerAmazons){
            if(smallerPlayerScore> getScore(playerAmazon)){
                if(playerAmazon.getAccessiblePositions(aiBoardRepresentation).size() != 0) {
                    smallerPlayerScore = getScore(playerAmazon);
                    amazonToMove = playerAmazon;
                }
            }
        }
        return amazonToMove;
    }
    public List<Position> bestAmazonDestinations(Amazon playerAmazon){
        List<Position> bestPositions = new ArrayList<>();
        int biggerAccessibleOpponentAdjacentPositionsNum = 0;
        for(Position position: playerAmazon.getAccessiblePositions(aiBoardRepresentation)){
            for(Amazon opponentAmazon: opponentAmazons) {
                int accessibleOpponentAdjacentPositionsNum = getAccessibleOpponentAdjacentPositionsNum(new Amazon(position, playerID.index), getAdjacentPositions(opponentAmazon.getPosition()));

                if(biggerAccessibleOpponentAdjacentPositionsNum < accessibleOpponentAdjacentPositionsNum){
                    biggerAccessibleOpponentAdjacentPositionsNum = accessibleOpponentAdjacentPositionsNum;
                    bestPositions = new Amazon(position, playerID.index).getAccessiblePositions(aiBoardRepresentation, getAdjacentPositions(opponentAmazon.getPosition()));
                }

            }
        }
        return bestPositions;
    }
    public int getAccessibleOpponentAdjacentPositionsNum(Amazon amazon,List<Position> opponentAdjacentPositions){
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
        return positionsToChooseFrom;
    }

}
