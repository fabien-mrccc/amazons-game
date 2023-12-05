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
    public Figure[][] getMatrixOfFiguresOnBoard() {
        return figures;
    }

    @Override
    public void instantiateBoard() {
        figures = new Figure[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
    }

    @Override
    public String toString(){
        StringBuilder displayableMatrix = new StringBuilder();

        for(int row = 0; row < NUMBER_OF_ROWS; row++){
            getALineToPrint(displayableMatrix);
            getAPipeToPrint(displayableMatrix);

            for(int column = 0; column < NUMBER_OF_COLUMNS;column++){
                getASpaceToPrint(displayableMatrix);
                getAFigureToPrint(displayableMatrix, column, row);
                getASpaceToPrint(displayableMatrix);
                getAPipeToPrint(displayableMatrix);
            }
            getASpaceToPrint(displayableMatrix);
            getRowNumberToPrint(displayableMatrix, row);
        }

        displayableMatrix.append("+");
        displayableMatrix.append("----+".repeat(NUMBER_OF_COLUMNS));
        displayableMatrix.append("\n");

        for(int column = 0; column < NUMBER_OF_COLUMNS; column++){
            displayableMatrix.append("  ").append(column).append("  ");
        }

        return displayableMatrix.toString();
    }

    private void getALineToPrint(StringBuilder displayableMatrix){
        displayableMatrix.append("+");
        displayableMatrix.append("----+".repeat(NUMBER_OF_COLUMNS));
        displayableMatrix.append("\n");
    }

    private void getAFigureToPrint(StringBuilder displayableMatrix, int column, int row){
        if(getFigure(new Position(column,row))== EMPTY_FIGURE){
            displayableMatrix.append("  ");
        } else if (getFigure(new Position(column,row))== ARROW_FIGURE) {
            displayableMatrix.append(ARROW_FIGURE);
        }
        else{
            displayableMatrix.append(getFigure(new Position(column, row)));
        }
    }

    private void getAPipeToPrint(StringBuilder displayableMatrix){
        displayableMatrix.append("|");
    }

    private void getASpaceToPrint(StringBuilder displayableMatrix){
        displayableMatrix.append(" ");
    }

    private void getRowNumberToPrint(StringBuilder displayableMatrix, int row){
        displayableMatrix.append(row).append("\n");
    }
}
