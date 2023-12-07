package amazons.game;

import amazons.figures.IllegalMoveException;
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
    public static final int DEFAULT_NUMBER_OF_AMAZONS_PER_PLAYER = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;

    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

    private final int numberOfColumns;
    private final int numberOfRows;

    private final Player[] players = new Player[NUMBER_OF_PLAYERS];
    private PlayerID winner = null;
    private int turn;
    private boolean isThisIsTheEnd;
    private final Board board;
    private PlayerID currentPlayerID;
    private final int numberOfAmazonsPerPlayer;

    public Game() {
        this(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS);
    }

    public Game(int numberOfColumns, int numberOfRows){
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.board = new MapBoard(numberOfColumns,numberOfRows);
        this.numberOfAmazonsPerPlayer = DEFAULT_NUMBER_OF_AMAZONS_PER_PLAYER;
    }

    public void initializeGame(Player player0, Player player1){
        isThisIsTheEnd = false;
        turn = 0;
        List<Position>[] initialPositions = new List[]{DEFAULT_PLAYER0_POSITIONS, DEFAULT_PLAYER1_POSITIONS};

        PresetFigureGenerator figureGenerator = new PresetFigureGenerator(createPlayersFiguresWithDefaultPosition(initialPositions));
        board.fill(figureGenerator);

        player0.initialize(numberOfColumns,numberOfRows,PlayerID.PLAYER_ZERO, initialPositions);
        players[0] = player0;
        currentPlayerID = getPlayerID();

        player1.initialize(numberOfColumns,numberOfRows,PlayerID.PLAYER_ONE, initialPositions);
        players[1] = player1;
    }

    public static List<MovableFigure> createPlayersFiguresWithDefaultPosition(List<Position>[] initialPositions){
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: initialPositions[0]){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO.index));
        }
        for(Position position: initialPositions[1]){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE.index));
        }
        return allPlayersFigures;
    }

    public void updateGame(Move move){
        updateGameAmazonMove(move.getAmazonStartPosition(), move.getAmazonDstPosition());
        updateGameArrowShot(move.getAmazonDstPosition(), move.getArrowDstPosition());
        currentPlayerID = getPlayerID();
    }

    public void updateGameAmazonMove(Position amazonStartPosition, Position amazonDstPosition){
        try{board.moveFigure(amazonStartPosition, amazonDstPosition);}
        catch (IllegalMoveException exception) {
            hasLost(currentPlayerID);
        }
    }

    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {
        try{board.shootArrow(amazonDstPosition, arrowDstPosition);}
        catch (IllegalMoveException exception){
            hasLost(currentPlayerID);
        }
    }

    public void hasLost(PlayerID playerID) {
        winner = playerID.opponent();
        isThisIsTheEnd = true;
    }

    public Board getBoard(){
         return board;
    }

    public PlayerID getWinner(){
        return winner;
    }

    public PlayerID getPlayerID(){
        return getPlayer().getPlayerID();
    }

    public Player getPlayer() {return players[getTurn()];}

    public boolean hasEnded() {
        return isThisIsTheEnd;
    }

    public void incrementTurn(){
        if(getTurn() == 1){
            turn--;
        }
        else{
            turn++;
        }
    }

    public int getTurn() {return turn; }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }

    public int getNumberOfRows(){
        return numberOfRows;
    }
}
