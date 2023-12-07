package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;

import java.util.List;
import java.util.Random;
import static amazons.util.RandomUtil.*;

public class AIBasicPlayer extends AbstractAIPlayer{

    @Override
    protected Position startPositionOfAmazonToMove() {
        return getRandomElement(new Random(), getMovableAmazons()).getPosition();
    }

    @Override
    protected Position destPositionOfAmazonToMove(Position startPosition) {
        Amazon amazon = (Amazon) aiBoardRepresentation.getFigure(startPosition);
        List<Position> accessibleDestinationsPositions = amazon.getAccessiblePositions(aiBoardRepresentation, getAdjacentPositions(startPosition));
        if(accessibleDestinationsPositions.size() == 0){
            return startPosition;
        }
        return getRandomElement(new Random(), accessibleDestinationsPositions);
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position arrowStartPosition) {
        return destPositionOfAmazonToMove(arrowStartPosition);
    }
}
