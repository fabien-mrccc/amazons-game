package amazons.controller;

import amazons.game.Game;
import amazons.player.*;

import java.util.Random;

public class CLGameController {
    private final Game game;
    private Move lastMove;
    private static final Random random = new Random();

    private final Player[] players = new Player[Game.NUMBER_OF_PLAYERS];

    public CLGameController(){
        this.game = new Game();
        setPlayer(new CLPlayer(),0);
        setPlayer(new CLPlayer(),1);
        game.initializeGame(players[0], players[1]);
        lastMove = Move.DUMMY_MOVE;
    }
    private void setPlayer(Player player, int playerIndex){
        players[playerIndex] = player;
    }


    public void run() {
        while (!game.hasEnded()) {
            System.out.println(game.getBoard());
            lastMove = game.getPlayer().play(lastMove);
            game.updateGame(lastMove);
            game.incrementTurn();
        }
        System.out.println(game.getBoard());
        System.out.println(game.getWinner() + " wins!!! ");
    }

}
