package amazons.player;

import amazons.figures.Amazon;
import amazons.board.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static amazons.util.RandomUtil.*;

public class AIBasicPlayer extends AbstractAIPlayer{

    @Override
    public Move play(Move opponentMove) {
        Position startPosition = getAmazonToMove().getPosition();
        Position destinationPosition = getRandomElement(new Random(), getAdjacentPositions(startPosition));
        return new Move(startPosition, destinationPosition, null);
    }

    @Override
    protected Amazon getAmazonToMove() {
        return getRandomElement(new Random(), getMovableAmazons());
    }

    private List<Amazon> getMovableAmazons(){
        List<Amazon> movableAmazons = new ArrayList<>();
        for(Amazon amazon : aiPlayerAmazons){
            if(amazon.getAccessiblePositions(aiBoardRepresentation).size() > 0){
                movableAmazons.add(amazon);
            }
        }
        return movableAmazons;
    }
}
