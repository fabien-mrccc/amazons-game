package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

    public static final Move DUMMY_MOVE = new Move();
    private Position amazonStartPosition;
    private Position amazonDstPosition;
    private Position arrowDstPosition;


    // TODO complete the code of this class

    public Position getAmazonStartPosition() {
        return amazonStartPosition;
    }

    public Position getAmazonDstPosition() {
        return amazonDstPosition;
    }

    public Position getArrowDstPosition() {
        return arrowDstPosition;
    }


    private Move() {}


    // DONE
    public Move(Position amazonStartPosition, Position amazonDstPosition, Position arrowDstPosition) {
        this.amazonStartPosition = amazonStartPosition;
        this.amazonDstPosition = amazonDstPosition;
        this.arrowDstPosition = arrowDstPosition;
    }


    // DONE
    public boolean equals(Move move){
        if(this.getAmazonStartPosition().equals(move.getAmazonStartPosition())
                && this.getAmazonDstPosition().equals(move.getAmazonDstPosition())
                && this.getArrowDstPosition().equals(move.getArrowDstPosition())){
            return true;
        }
        return false;
    }
    // DONE
    public String toString(){
        return getAmazonStartPosition().toString()+":"+getAmazonDstPosition().toString()
                +"->"+getArrowDstPosition().toString();
    }
    // TODO method hashCode
}
