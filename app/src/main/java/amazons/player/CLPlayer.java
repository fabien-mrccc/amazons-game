package amazons.player;

import amazons.board.Position;

import java.util.Scanner;

public class CLPlayer extends AbstractPlayer {

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
        setPlayerID(getPlayerID().opponent());
        return new Move(amazonStartPosition,amazonDstPosition,arrowDstPosition);
    }
}
