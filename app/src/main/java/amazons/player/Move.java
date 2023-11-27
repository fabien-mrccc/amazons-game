package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

    public static final Move DUMMY_MOVE = new Move();


    // TODO complete the code of this class

    public Position getAmazonStartPosition() {
        return null;
        // TODO
    }

    public Position getAmazonDstPosition() {
        return null;
        // TODO
    }

    public Position getArrowDstPosition() {
        return null;
        // TODO
    }


    private Move() {}


    // TODO
    public Move(Position amazonStartPosition, Position amazonDstPosition, Position arrowDstPosition) {

    }


    // TODO method equals
    // DONE 
    public String toString(){
        return getAmazonStartPosition().toString()+":"+getAmazonDstPosition().toString()
                +"->"+getArrowDstPosition().toString();
    }
    // TODO method hashCode
}
