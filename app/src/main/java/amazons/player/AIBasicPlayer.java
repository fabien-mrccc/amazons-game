package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;

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
        return getRandomElement(new Random(), amazon.getAccessiblePositions(aiBoardRepresentation, getAdjacentPositions(startPosition)));
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        return null;
    }
}
