package amazons.controller;

import amazons.board.Board;
import amazons.board.Position;
import amazons.game.Game;
import amazons.game.TurnPhase;
import amazons.player.*;
import amazons.viewer.BoardView;
import amazons.viewer.MenuView;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.util.Duration;

import java.util.Random;


public class GameController {
    private final Game game;
    private static final Random random = new Random();

    private Position lastAmazonStartPosition;
    private Position lastAmazonDstPosition;
    private Position lastArrowDstPosition;
    private Move lastMove = Move.DUMMY_MOVE;

    private final SimpleObjectProperty<TurnPhase> phase = new SimpleObjectProperty<>(TurnPhase.AMAZON_PHASE);
    private static final int PAUSE_MILLISECONDS = 100;
    private final PauseTransition pause = new PauseTransition(Duration.millis(PAUSE_MILLISECONDS));

    private final Player[] players = new Player[Game.NUMBER_OF_PLAYERS];
    private  BoardView view;
    private MenuView menu;


    public GameController(Game game, Player player0, Player player1){
        this.game = game;
        setPlayer(player0, PlayerID.PLAYER_ZERO);
        setPlayer(player1, PlayerID.PLAYER_ONE);
        initialize();
    }

    private void initialize(){
        game.initializeGame(players[0], players[1]);
        lastMove = Move.DUMMY_MOVE;
        resetPhase();
        addEndPhaseListener();
        setPauseAnimation();
    }

    public void resetGame(){
       initialize();
       view.updateFields();
       menu.setStatusText("");
       startGame();
    }
    private void setPauseAnimation(){
        pause.setOnFinished(e -> setPhase(TurnPhase.END_PHASE));
    }

    private void addEndPhaseListener(){
        getPhaseProperty().addListener((observable, oldValue, newValue) ->{
            if (newValue == TurnPhase.END_PHASE) endTurn();
        });
    }

    private void endTurn(){
        if(!game.hasEnded()) {
            game.incrementTurn();
            resetPhase();
            if(!getCurrentPlayer().isGUIControlled()) playComputerTurn();
        }
        else {
            menu.setStatusText(game.getWinner() + " wins!!!");
        }
    }

    public void setPlayer(Player player, PlayerID playerID){
        players[playerID.index] = player;
    }

    public void setView(BoardView view){
        this.view = view;
    }

    public void setMenu(MenuView menu){
        this.menu = menu;
    }
    public void playComputerTurn(){
        AbstractAIPlayer currentPlayer = (AbstractAIPlayer) getCurrentPlayer();
        lastMove = currentPlayer.play(lastMove);
        if(!currentPlayer.hasMovableAmazons){
            game.hasLost(getCurrentPlayerID());
            endTurn();
            return;
        }
        lastAmazonStartPosition = lastMove.getAmazonStartPosition();
        lastAmazonDstPosition = lastMove.getAmazonDstPosition();
        lastArrowDstPosition = lastMove.getArrowDstPosition();
        game.updateGame(lastMove);
        updatePlayersBoardRepresentation();
        view.showMove(lastMove);
        pause.play();
        if(getOpponentPlayer().isGUIControlled()){
            game.incrementTurn();
        }
    }

    public void startGame(){
        if(!getCurrentPlayer().isGUIControlled()) playComputerTurn();
    }

    public Board getBoard(){
        return game.getBoard();
    }

    public PlayerID getCurrentPlayerID(){
        return game.getPlayerID();
    }

    public Player getCurrentPlayer(){
        return game.getPlayer();
    }

    public Player getOpponentPlayer() {
        return players[getCurrentPlayerID().opponent().index];
    }

    public void setPhase(TurnPhase turnPhase){
        this.phase.setValue(turnPhase);
    }

    public TurnPhase getPhase(){
        return phase.getValue();
    }

    public void resetPhase() {
        setPhase(TurnPhase.AMAZON_PHASE);
    }

    public SimpleObjectProperty<TurnPhase> getPhaseProperty(){
        return phase;
    }

    public void nextPhase(){
        setPhase(getPhase().next());
    }

    // call by the view
    public void moveFigure(Position amazonStartPosition, Position amazonDstPosition){
        game.updateGameAmazonMove(amazonStartPosition, amazonDstPosition);
        lastAmazonStartPosition = amazonStartPosition;
        lastAmazonDstPosition = amazonDstPosition;
    }

    // call by the view
    public void shootArrow(Position startPosition, Position arrowDstPosition){
        game.updateGameArrowShot(startPosition, arrowDstPosition);
        lastArrowDstPosition = arrowDstPosition;
        updatePlayersBoardRepresentation();
        PlayerID loser = checkForLosers();
        if(loser != null){
            game.hasLost(loser);
        }
        lastMove = new Move(lastAmazonStartPosition, lastAmazonDstPosition, lastArrowDstPosition);
    }

    private void updatePlayersBoardRepresentation(){
        AbstractPlayer player = (AbstractPlayer) getCurrentPlayer();
        AbstractPlayer opponentPlayer = (AbstractPlayer) getOpponentPlayer();
        player.updateBoardAmazonCase(lastAmazonStartPosition, lastAmazonDstPosition);
        opponentPlayer.updateBoardAmazonCase(lastAmazonStartPosition, lastAmazonDstPosition);
        player.updateBoardArrowCase(lastAmazonDstPosition, lastArrowDstPosition);
        opponentPlayer.updateBoardArrowCase(lastAmazonDstPosition, lastArrowDstPosition);
    }

    private PlayerID checkForLosers(){
        AbstractPlayer player = (AbstractPlayer) getCurrentPlayer();
        AbstractPlayer opponentPlayer = (AbstractPlayer) getOpponentPlayer();
        if(!player.hasPlayableAmazons()){
            return player.getPlayerID();
        }
        if (!opponentPlayer.hasPlayableAmazons()){
            return opponentPlayer.getPlayerID();
        }
        return null;
    }

    public void setPlayerGUI(PlayerID playerID){
        setPlayer(new GUIPLayer(), playerID);
    }

    public void setPlayerBasic(PlayerID playerID){
        setPlayer(new AIBasicPlayer(), playerID);
    }

    public void setPlayerRandom(PlayerID playerID){
        setPlayer(new GUIPLayer(), playerID);
    }
    public void setPlayerGreedy(PlayerID playerID){
        setPlayer(new AIGreedyPlayer(), playerID);
    }

}
