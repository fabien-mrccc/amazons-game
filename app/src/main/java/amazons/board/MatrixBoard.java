package amazons.board;

import amazons.figures.Figure;
import amazons.figures.Amazon;

public class MatrixBoard extends AbstractBoard {

    private Figure[][] figures ;

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
    public String toString(){
        String matrix = "";
        for(int r = 0; r < super.getNumberOfRows();r++){
            matrix ="+";
            for(int c = 0; c < super.getNumberOfColumns();c++){
                matrix = matrix + "----+";
            }
            System.out.println("");
            System.out.print('|');
            for(int c = 0; c < super.getNumberOfColumns();c++){
                matrix = matrix +"    |";
                if((Amazon)getFigure(new Position(c,r))){

                }
            }
            System.out.println("");

        }

        return matrix;
    }
}
