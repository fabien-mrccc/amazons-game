package amazons.board;

import amazons.figures.ArrowFigure;
import amazons.figures.EmptyFigure;
import amazons.figures.Figure;

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
        figures = new Figure[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
    }
    @Override
    public String toString(){
        StringBuilder matrix = new StringBuilder();
        for(int r = 0; r < NUMBER_OF_ROWS;r++){
            matrix.append("+");
            matrix.append("----+".repeat(NUMBER_OF_COLUMNS));
            matrix.append("\n").append("|");
            for(int c = 0; c < NUMBER_OF_COLUMNS;c++){
                if(getFigure(new Position(c,r))== EmptyFigure.EMPTY_FIGURE){
                    matrix.append("    |");
                } else if (getFigure(new Position(c,r))== ArrowFigure.ARROW_FIGURE) {
                    matrix.append(" XX |");
                }
                else{
                    matrix.append(" A").append(getFigure(new Position(c, r)).getPlayerID().index).append(" |");
                }
            }
            matrix.append(" ").append(r).append("\n");
        }
        matrix.append("+");
        matrix.append("----+".repeat(NUMBER_OF_COLUMNS));
        matrix.append("\n");
        for(int c = 0; c < NUMBER_OF_COLUMNS; c++){
            matrix.append("  ").append(c).append("  ");
        }
        return matrix.toString();
    }
}
