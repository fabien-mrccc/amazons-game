package amazons.board;

import amazons.figures.Figure;

import java.util.Iterator;
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
    public Figure[][] getMatrix(){
        MatrixBoard mapToMatrix = new MatrixBoard(getNumberOfColumns(),getNumberOfRows());
        for(int x=0; x< mapToMatrix.getNumberOfColumns();x++){
            for(int y=0; y< mapToMatrix.getNumberOfRows();y++){
                mapToMatrix.setFigure(new Position(x,y), figures.get(new Position(x,y)));
            }
        }
        return mapToMatrix.getMatrix();
    }

}
