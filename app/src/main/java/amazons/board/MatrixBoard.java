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
    protected Figure[][] getMatrixOfFiguresOnBoard() {
        return figures;
    }

    @Override
    protected void instantiateBoard() {
        figures = new Figure[NUMBER_OF_COLUMNS][NUMBER_OF_ROWS];
    }

    @Override
    public String toString(){
        StringBuilder displayableMatrix = new StringBuilder();

        for(int row = 0; row < NUMBER_OF_ROWS; row++){
            concatALine(displayableMatrix);
            carriageReturn(displayableMatrix);
            concatPipe(displayableMatrix);

            for(int column = 0; column < NUMBER_OF_COLUMNS;column++){
                concatSpace(displayableMatrix);
                concatFigure(displayableMatrix, column, row);
                concatSpace(displayableMatrix);
                concatPipe(displayableMatrix);
            }
            concatSpace(displayableMatrix);
            concatRowNumber(displayableMatrix, row);
            carriageReturn(displayableMatrix);
        }

        concatALine(displayableMatrix);
        carriageReturn(displayableMatrix);

        for(int column = 0; column < NUMBER_OF_COLUMNS; column++){
            concatSpace(displayableMatrix);
            concatSpace(displayableMatrix);
            concatColumnNumber(displayableMatrix, column);
            concatSpace(displayableMatrix);
            concatSpace(displayableMatrix);
        }

        return displayableMatrix.toString();
    }

    private void concatALine(StringBuilder displayableMatrix){
        displayableMatrix.append("+");
        displayableMatrix.append("----+".repeat(NUMBER_OF_COLUMNS));
    }

    private void concatFigure(StringBuilder displayableMatrix, int column, int row){
        if(getFigure(new Position(column,row))== EMPTY_FIGURE){
            displayableMatrix.append("  ");
        }
        else if (getFigure(new Position(column,row))== ARROW_FIGURE) {
            displayableMatrix.append(ARROW_FIGURE);
        }
        else{
            displayableMatrix.append(getFigure(new Position(column, row)));
        }
    }

    private void concatPipe(StringBuilder displayableMatrix){
        displayableMatrix.append("|");
    }

    private void concatSpace(StringBuilder displayableMatrix){
        displayableMatrix.append(" ");
    }

    private void concatRowNumber(StringBuilder displayableMatrix, int row){
        displayableMatrix.append(row);
    }

    private void concatColumnNumber(StringBuilder displayableMatrix, int column){
        displayableMatrix.append(column);
    }

    private void carriageReturn(StringBuilder displayableMatrix){
        displayableMatrix.append("\n");
    }
}
