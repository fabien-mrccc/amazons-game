package amazons.player;

import amazons.board.Position;
import amazons.figures.Amazon;
import amazons.figures.MovableFigure;
import amazons.game.Game;

import java.util.List;

public class Greedy implements Player {
    private PlayerID playerID;
    private int boardWidth;
    private int boardHeight;
    private List<Position>[] initialPositions;
    private Game game;
    @Override
    public Move play(Move opponentMove) {
        return null;
    }

    @Override
    public void initialize(int boardHeight, int boardWidth, PlayerID playerID, List<Position>[] initialPositions) {
        game = new Game();
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.playerID = playerID;
        this.initialPositions = initialPositions;
    }

    @Override
    public boolean isGUIControlled() {
        return false;
    }

    @Override
    public PlayerID getPlayerID() {
        return playerID;
    }
    /* maximiser la possibilité de gagner: si l'une de mes amazones est ent
    entourer par une ou plusieurs flèche ou amazones adverses la faire bouger à
    une case vide et entourer de cases vide, sinon aller dans la case la moins entourer.
    minimiser les choix de l'adversaire: chercher l'amazone du joueur adverse avec
    le plus de cases accessible et envoyer la flèche à l'une de ses cases adjacentes..
     */

    /**
     * return the number of available positions in the board of the game for an amazon given in parameters
     * @param amazon
     * @return numberOfAvailablePositions
     */
    public int getScore(Amazon amazon, Game game){
        int score = 0;
        for(Position position:amazon.getAccessiblePositions(game.getBoard())){
            score = score + 1;
        }
        return score;
    }
    /**
     * return the best position to move at depending on the bestShootPosition and the number of arrows arround the position
     * @return bestMovePosition
     */
    public Position bestMovePosition(){

        return null;
    }

    /**
     * return the best shoot position to reduce the opponent playing choices
     * @return bestShootPosition
     */
    public Position bestShootPosition(){
        return null;
    }
    public List<Amazon> getPlayerAmazons(PlayerID playerID, Game game){
        return null;
    }
}
