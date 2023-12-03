package amazons.game;

import amazons.IllegalMoveException;
import amazons.board.*;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;
import java.util.List;
import java.util.ArrayList;
import amazons.figures.Amazon;
import amazons.figures.MovableFigure;
public class Game {
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_NUMBER_OF_AMAZONS = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;

    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

    private final Player[] players = new Player[NUMBER_OF_PLAYERS];
    private PlayerID winner = null;
    private int turn = 0;
    private boolean isThisIsTheEnd = false;
    private Board board;
    private PlayerID currentPlayerID;
    private final int numberOfAmazons;

    public Game() {
        this.board = new MapBoard(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS);
        this.numberOfAmazons = DEFAULT_NUMBER_OF_AMAZONS;
    }

    //@SuppressWarnings("unchecked")
    public void initializeGame(Player player0, Player player1){
        List<Position>[] initialPositions = new ArrayList[NUMBER_OF_PLAYERS];
        initialPositions[0] = DEFAULT_PLAYER0_POSITIONS;
        initialPositions[1] = DEFAULT_PLAYER1_POSITIONS;

        player0.initialize(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS,PlayerID.PLAYER_ZERO, initialPositions);
        players[0] = player0;
        player1.initialize(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS,PlayerID.PLAYER_ONE, initialPositions);
        players[1] = player1;
        createPlayersFiguresWithDefaultPosition();
    }

    private List<MovableFigure> createPlayersFiguresWithDefaultPosition(){
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER0_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO.index));
        }
        for(Position position: DEFAULT_PLAYER1_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE.index));
        }
        return allPlayersFigures;
    }

    public void updateGame(Move move){
        currentPlayerID = board.getFigure(move.getAmazonStartPosition()).getPlayerID();
        updateGameAmazonMove(move.getAmazonStartPosition(),move.getAmazonDstPosition());
        updateGameArrowShot(move.getAmazonDstPosition(), move.getArrowDstPosition());
    }

    public void updateGameAmazonMove(Position amazonStartPosition, Position amazonDstPosition){
        try{board.moveFigure(amazonStartPosition, amazonDstPosition);}
        catch (IllegalMoveException exception) { winner = board.getFigure(amazonStartPosition).getPlayerID().opponent();}
    }

    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {
        try{board.shootArrow(amazonDstPosition, arrowDstPosition);}
        catch (IllegalMoveException exception){ winner = board.getFigure(amazonDstPosition).getPlayerID().opponent();}
    }

    private boolean hasLost(PlayerID playerID) {
        if(playerID.equals(winner)){
            return false;
        }
        return true;
    }

    public Board getBoard(){
         return board;
    }

    public PlayerID getWinner(){
        return winner;
    }

    public PlayerID getPlayerID(){
        return currentPlayerID;
    }

    public Player getPlayer() {return players[currentPlayerID.index];}

    public boolean hasEnded() {
        if(getWinner().equals(null)){
            return isThisIsTheEnd;
        }
        return true;
    }

    public void incrementTurn(){
        turn++;
    }

    public int getTurn() {return turn; }

    public int getNumberOfColumns(){
        return DEFAULT_NUMBER_OF_COLUMNS;
    }

    public int getNumberOfRows(){
        return DEFAULT_NUMBER_OF_ROWS;
    }
}
