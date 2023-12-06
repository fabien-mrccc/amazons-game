package amazons.player;

import amazons.figures.Amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIBasicPlayer extends AbstractAIPlayer{

    @Override
    public Move play(Move opponentMove) {
        Amazon amazonToMove = getAmazonToMove();
        return null;
    }

    @Override
    protected Amazon getAmazonToMove() {
        return getRandomAmazon(getMovableAmazons());
    }

    private Amazon getRandomAmazon(List<Amazon> amazons){
        Random random = new Random();
        int randomAmazonIndex = random.nextInt() % amazons.size();
        return amazons.get(randomAmazonIndex);
    }

    private List<Amazon> getMovableAmazons(){
        List<Amazon> movableAmazons = new ArrayList<>();
        for(Amazon amazon : aiPLayerAmazons){
            if(amazon.getAccessiblePositions(aiBoardRepresentation).size() > 0){
                movableAmazons.add(amazon);
            }
        }
        return movableAmazons;
    }
}
