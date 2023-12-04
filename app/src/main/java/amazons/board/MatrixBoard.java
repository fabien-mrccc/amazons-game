package amazons.board;

import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;
import amazons.figures.Figure;

public class MatrixBoard extends AbstractBoard {

    private Figure[][] figures;

    public MatrixBoard(int numberOfColumns, int numberOfRows){
        super(numberOfColumns, numberOfRows);
    }

    @Override
    public void setFigure(Position position, Figure figure) {
        figures[position.columnIndex()][position.rowIndex()] = figure;
    }

    @Override
    public Figure getFigure(Position position) {
        return figures[position.columnIndex()][position.rowIndex()];
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
        for(int row = 0; row < NUMBER_OF_ROWS;row++){
            matrix.append("+");
            matrix.append("----+".repeat(NUMBER_OF_COLUMNS));
            matrix.append("\n").append("|");
            for(int column = 0; column < NUMBER_OF_COLUMNS;column++){
                if(getFigure(new Position(column,row))== EMPTY_FIGURE){
                    matrix.append("    |");
                } else if (getFigure(new Position(column,row))== ARROW_FIGURE) {
                    matrix.append(" ").append(ARROW_FIGURE).append(" |");
                }
                else{
                    matrix.append(" ").append(getFigure(new Position(column, row))).append(" |");
                }
            }
            matrix.append(" ").append(row).append("\n");
        }
        matrix.append("+");
        matrix.append("----+".repeat(NUMBER_OF_COLUMNS));
        matrix.append("\n");
        for(int column = 0; column < NUMBER_OF_COLUMNS; column++){
            matrix.append("  ").append(column).append("  ");
        }
        return matrix.toString();
    }
}
