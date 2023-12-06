package amazons.player;

import amazons.board.Position;
import java.util.Random;
import static amazons.util.RandomUtil.*;

public class AIBasicPlayer extends AbstractAIPlayer{

    @Override
    protected Position startPositionOfAmazonToMove() {
        return getRandomElement(new Random(), getMovableAmazons()).getPosition();
    }

    @Override
    protected Position destPositionOfAmazonToMove(Position startPosition) {
        return getRandomElement(new Random(), getAdjacentPositions(startPosition));
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        return null;
    }
}
