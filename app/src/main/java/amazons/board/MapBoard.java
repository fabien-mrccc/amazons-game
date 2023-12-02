package amazons.board;

import amazons.figures.Figure;

import java.util.HashMap;
import java.util.Map;

public class MapBoard extends AbstractBoard {

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
    public Figure[][] getFigureMatrix(){
        return mapToMatrix().getFigureMatrix();
    }

    /**
     * return the matrix version of the map 
     * @return MatrixBoard
     */
    public MatrixBoard mapToMatrix(){
        MatrixBoard mapToMatrix = new MatrixBoard(getNumberOfColumns(),getNumberOfRows());
        for(int x=0; x< mapToMatrix.getNumberOfColumns();x++){
            for(int y=0; y< mapToMatrix.getNumberOfRows();y++){
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
