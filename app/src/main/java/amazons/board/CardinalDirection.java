package amazons.board;

import java.util.Arrays;

public enum CardinalDirection {
    NORTH(0, -1),
    NORTH_EAST(1,-1),
    EAST(1, 0),
    SOUTH_EAST(1,1),
    SOUTH(0, 1),
    SOUTH_WEST(-1,1),
    WEST(-1, 0),
    NORTH_WEST(-1,-1);

    public final int deltaColumn;
    public final int deltaRow;


    CardinalDirection(int deltaColumn, int deltaRow) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }


    public static CardinalDirection getDirection(int sourceX, int sourceY, int destX, int destY){
        int deltaX = getCoordinateDirection(sourceX, destX);
        int deltaY = getCoordinateDirection(sourceY, destY);
        return Arrays.stream(values())
                .filter(d -> d.deltaColumn == deltaX && d.deltaRow == deltaY)
                .findFirst().orElseThrow();
    }

    private static int getCoordinateDirection(int source, int dest){
        int delta = 0;
        if(dest - source != 0) delta = (dest - source) > 0 ? 1 : -1;
        return delta;
    }
}
