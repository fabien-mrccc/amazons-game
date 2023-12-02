package amazons.board;

import amazons.figures.ArrowFigure;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;
import amazons.figures.Amazon;

public class MatrixBoard extends AbstractBoard {

    private Figure[][] figures;

    public MatrixBoard(int numberOfColumns, int numberOfRows){
        super(numberOfColumns, numberOfRows);
    }

    @Override
    public void setFigure(Position position, Figure figure) {
        figures[position.getX()][position.getY()] = figure;
    }

    @Override
    public Figure getFigure(Position position) {
        return figures[position.getX()][position.getY()];
    }

    @Override
    public Figure[][] getFigureMatrix() {
        return figures;
    }

    @Override
    public void instantiateBoard() {
        figures = new Figure[super.getNumberOfColumns()][super.getNumberOfRows()];
    }
    @Override
    public String toString(){
        String matrix = "";
        for(int r = 0; r < super.getNumberOfRows();r++){
            matrix =matrix + "+";
            for(int c = 0; c < super.getNumberOfColumns();c++){
                matrix = matrix + "----+";
            }
            matrix = matrix + "\n" +"|";
            for(int c = 0; c < super.getNumberOfColumns();c++){
                if(getFigure(new Position(c,r))== EmptyFigure.EMPTY_FIGURE){
                    matrix = matrix +"    |";
                } else if (getFigure(new Position(c,r))== ArrowFigure.ARROW_FIGURE) {
                    matrix = matrix +" XX |";
                }
                else{
                    matrix = matrix +" A"+ getFigure(new Position(c,r)).getPlayerID().index + " |";
                }
            }
            matrix = matrix +" " +r +"\n";
        }
        matrix = matrix + "+";
        for(int c = 0; c < super.getNumberOfColumns(); c++){
            matrix = matrix +"----+";
        }
        matrix = matrix +"\n";
        for(int c = 0; c < super.getNumberOfColumns(); c++){
            matrix = matrix + "  "+ c+ "  ";
        }
        return matrix;
    }
}
