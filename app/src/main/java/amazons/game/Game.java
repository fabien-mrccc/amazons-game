package amazons.game;

import amazons.board.*;
import amazons.player.Move;
import amazons.player.Player;
import amazons.player.PlayerID;

public class Game {
    public static final int NUMBER_OF_PLAYERS = 2;
    public static final int DEFAULT_NUMBER_OF_AMAZONS = 4;
    private static final int DEFAULT_NUMBER_OF_COLUMNS = 10;
    private static  final int DEFAULT_NUMBER_OF_ROWS = 10;

    // TODO Complete the code of this class
    /* Default positions of the amazons TODO: UNCOMMENT

    private static final List<Position> DEFAULT_PLAYER0_POSITIONS =
            List.of(new Position(0,6), new Position(9,6), new Position(3,9), new Position(6,9));
    private static final List<Position> DEFAULT_PLAYER1_POSITIONS =
            List.of(new Position(3,0), new Position(6,0), new Position(0,3), new Position(9,3));

     */

    private final Player[] players = new Player[NUMBER_OF_PLAYERS];

    private PlayerID winner;

    private int turn = 0;
    private boolean isThisIsTheEnd = false;
    private Board board;


    // DONE
    public Game() {
        this.board = new MapBoard(DEFAULT_NUMBER_OF_COLUMNS,DEFAULT_NUMBER_OF_ROWS);
    }

    // TODO
    public void initializeGame(Player player0, Player player1){
    }


    // TODO uncomment
    /*
    private List<MovableFigure> createPlayersFiguresWithDefaultPosition(){
        List<MovableFigure> allPlayersFigures = new ArrayList<>();
        for(Position position: DEFAULT_PLAYER0_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ZERO));
        }
        for(Position position: DEFAULT_PLAYER1_POSITIONS){
            allPlayersFigures.add(new Amazon(position, PlayerID.PLAYER_ONE));
        }
        return allPlayersFigures;
    }
   */




    // TODO
    public void updateGame(Move move){

    }

    //TODO
    public void updateGameAmazonMove(Position amazonStartPosition, Position amazonDstPosition){

    }
    // TODO
    public void updateGameArrowShot(Position amazonDstPosition, Position arrowDstPosition) {

    }

    // TODO
    private boolean hasLost(PlayerID playerID) {
        return true;

    }

    // TODO
    public Board getBoard(){
         return null;
    }

    // TODO
    public PlayerID getWinner(){
        return PlayerID.NONE;
    }

    // TODO
    public PlayerID getPlayerID(){
        return PlayerID.PLAYER_ONE;
    }

    // TODO
    public Player getPlayer() {return null; }

    // TODO
    public boolean hasEnded() {
        return true;
    }

    public void incrementTurn(){
        turn++;
    }

    public int getTurn() {return turn; }

    // TODO
    public int getNumberOfColumns(){
        return 0;
    }

    // TODO
    public int getNumberOfRows(){
        return 0;
    }
}
