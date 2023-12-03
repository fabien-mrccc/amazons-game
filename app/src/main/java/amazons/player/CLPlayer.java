package amazons.player;

import amazons.board.MatrixBoard;
import amazons.board.Position;
import amazons.figures.Amazon;

import java.util.List;
import java.util.Scanner;

public class CLPlayer implements Player {
    private PlayerID playerID;
    private MatrixBoard board;
    private final static Scanner inputScanner = new Scanner(System.in);

    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public Move play(Move opponentMove) {
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
        board = new MatrixBoard(boardWidth,boardHeight);
        for(Position position : initialPositions[0]) {
            board.setFigure(position, new Amazon(position, PlayerID.PLAYER_ZERO.index));
        }
        for(Position position : initialPositions[1]) {
            board.setFigure(position, new Amazon(position, PlayerID.PLAYER_ONE.index));
        }
        this.playerID = playerID;
    }

    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }

    public MatrixBoard getBoard(){
        return board;
    }
}
