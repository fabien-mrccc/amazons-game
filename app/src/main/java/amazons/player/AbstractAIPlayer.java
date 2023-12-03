package amazons.player;

public abstract class AbstractAIPlayer extends AbstractPlayer{
    @Override
    public abstract Move play(Move opponentMove);
}
