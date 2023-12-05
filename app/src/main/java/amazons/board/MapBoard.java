package amazons.board;

import amazons.figures.Figure;

import java.util.HashMap;
import java.util.Map;

public final class MapBoard extends AbstractBoard {

    private Map<Position,Figure> figures;

    public MapBoard(int numberOfColumns, int numberOfRows){
        super(numberOfColumns, numberOfRows);
    }

    @Override
    public void setFigure(Position position, Figure figure) {
        figures.put(position,figure);
    }

    @Override
    public Figure getFigure(Position position) {
        return figures.get(position);
    }

    @Override
    public Figure[][] getMatrixOfFiguresOnBoard(){
        return mapToMatrix().getMatrixOfFiguresOnBoard();
    }

    /**
     * return the matrix version of the map
     * @return MatrixBoard
     */
    private MatrixBoard mapToMatrix(){
        final MatrixBoard mapToMatrix = new MatrixBoard(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
        for(int x=0; x< mapToMatrix.NUMBER_OF_COLUMNS; x++){
            for(int y=0; y< mapToMatrix.NUMBER_OF_ROWS; y++){
                mapToMatrix.setFigure(new Position(x,y), getFigure(new Position(x,y)));
            }
        }
        return mapToMatrix;
    }

    @Override
    public void instantiateBoard() {
        figures = new HashMap<>();
    }

    @Override
    public String toString(){
        return mapToMatrix().toString();
    }
}
