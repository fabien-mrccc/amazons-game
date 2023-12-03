package amazons.player;

import amazons.board.Position;

import java.util.Objects;

public class Move {

    public static final Move DUMMY_MOVE = new Move();
    private Position amazonStartPosition;
    private Position amazonDstPosition;
    private Position arrowDstPosition;



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


    public Move(Position amazonStartPosition, Position amazonDstPosition, Position arrowDstPosition) {
        this.amazonStartPosition = amazonStartPosition;
        this.amazonDstPosition = amazonDstPosition;
        this.arrowDstPosition = arrowDstPosition;
    }


    public boolean equals(Move move){
        return this.getAmazonStartPosition().equals(move.getAmazonStartPosition())
                && this.getAmazonDstPosition().equals(move.getAmazonDstPosition())
                && this.getArrowDstPosition().equals(move.getArrowDstPosition());
    }
    public String toString(){
        return getAmazonStartPosition().toString()+":"+getAmazonDstPosition().toString()
                +"->"+getArrowDstPosition().toString();
    }
    public int hashCode(){
        return Objects.hash(amazonStartPosition,amazonDstPosition,arrowDstPosition);
    }
}
