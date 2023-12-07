package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import static amazons.util.RandomUtil.*;
import java.util.Random;
public class AIRandomPlayer extends AbstractAIPlayer {
    @Override
    protected Position startPositionOfAmazonToMove() {
        Amazon amazonToMove = getRandomElement(new Random(),getMovableAmazons());
        return amazonToMove.getPosition();
    }

    @Override
    protected Position destPositionOfAmazonToMove(Position startPosition) {
        return null;
    }

    @Override
    protected Position destPositionOfArrowToShoot(Position startPosition) {
        return null;
    }
}
