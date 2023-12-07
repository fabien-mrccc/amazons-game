package amazons.player;

import amazons.board.Board;
import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.figures.IllegalMoveException;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPlayer implements Player{

    protected PlayerID playerID;
    protected int boardWidth;
    protected int boardHeight;
    protected Board boardRepresentation;
    protected List<Position> initialPositions;
    protected List<Amazon> playerAmazons;

    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.playerID = playerID;
        this.initialPositions = initialPositions[playerID.index];
        instantiateBoard(initialPositions);
        fillPlayerAmazonsList(this.initialPositions);
    }

    @Override
    public abstract Move play(Move opponentMove);

    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public final PlayerID getPlayerID() {
        return playerID;
    }

    public boolean hasPlayableAmazons(){
        return !getMovableAmazons().isEmpty();
    }

    public final void setPlayerID(PlayerID playerID){
        this.playerID = playerID;
    }

    private void fillPlayerAmazonsList(List<Position> initialPositions) {
        playerAmazons = new ArrayList<>();
        Amazon amazonToAdd;
        for(Position position : initialPositions){
            amazonToAdd = (Amazon) boardRepresentation.getFigure(position);
            playerAmazons.add(amazonToAdd);
        }
    }

    private void instantiateBoard(List<Position>[] initialPositions){
        boardRepresentation = new MatrixBoard(boardWidth,boardHeight);
        addStartingAmazons(initialPositions);
    }

    private void addStartingAmazons(List<Position>[] initialPositions){
        for(Position position: initialPositions[0]){
            boardRepresentation.setFigure(position, new Amazon(position, PlayerID.PLAYER_ZERO.index));
        }
        for(Position position: initialPositions[1]){
            boardRepresentation.setFigure(position, new Amazon(position, PlayerID.PLAYER_ONE.index));
        }
    }

    protected List<Amazon> getMovableAmazons(){
        List<Amazon> movableAmazons = new ArrayList<>();
        for(Amazon amazon : playerAmazons){
            if(amazon.getAccessiblePositions(boardRepresentation).size() > 0){
                movableAmazons.add(amazon);
            }
        }
        return movableAmazons;
    }

    public void updateBoardAmazonCase(Position amazonStartPosition, Position amazonDestPosition){
        try{
            boardRepresentation.moveFigure(amazonStartPosition,amazonDestPosition);
        }
        catch(IllegalMoveException e){
        }
    }

    public void updateBoardArrowCase(Position amazonDestPosition, Position arrowDestinationPosition){
        try{
            boardRepresentation.shootArrow(amazonDestPosition, arrowDestinationPosition);
        }
        catch(IllegalMoveException e){
        }
    }

    public Board getBoard(){
        return boardRepresentation;
    }
}
