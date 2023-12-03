package amazons.player;

import amazons.board.Position;

import java.util.List;
import java.util.Scanner;

public class CLPlayer implements Player {
    private PlayerID playerID;
    private int boardWidth;
    private int boardHeight;
    private List<Position> initialPositions;
    private final static Scanner inputScanner = new Scanner(System.in);

    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public Move play(Move opponentMove) {
        System.out.println("Previous move of your opponent: " + opponentMove);
        System.out.println(playerID + " select amazon? (enter X Y coordinate)");
        Position amazonStartPosition = new Position(inputScanner.nextInt(),inputScanner.nextInt());
        System.out.println(playerID + " select destination? (enter X Y coordinate)");
        Position amazonDstPosition = new Position(inputScanner.nextInt(),inputScanner.nextInt());
        System.out.println(playerID + " where to shoot arrow? (enter X Y coordinate)");
        Position arrowDstPosition = new Position(inputScanner.nextInt(),inputScanner.nextInt());
        return new Move(amazonStartPosition,amazonDstPosition,arrowDstPosition);
    }

    @Override
    public void initialize(int boardWidth, int boardHeight,  PlayerID playerID, List<Position>[] initialPositions) {
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
        this.playerID = playerID;
        this.initialPositions = initialPositions[playerID.index];
    }

    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }

    public List<Position> getInitialPositions() {
        return initialPositions;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public int getBoardWidth() {
        return boardWidth;
    }
}
