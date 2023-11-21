package amazons.figures;

import amazons.board.Board;
// import amazons.board.EmptyFigureGenerator;
// import amazons.board.MatrixBoard;
import amazons.board.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

//import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static org.assertj.core.api.Assertions.assertThat;

class AmazonTest {
    /*
    private final Position[][] allPositions = new Position[4][3];
    private final List<Position> accessiblePositions = new ArrayList<>();
    private final int NUMBER_OF_COLUMNS = 4;
    private final int NUMBER_OF_ROWS = 3;
    private final Board board = new MatrixBoard(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
    private Amazon amazon11;
    @BeforeEach
    */

    /*
                +----+----+----+----+
                |    |    | A1 |    | 0
                +----+----+----+----+
                |    | A0 |    |    | 1
                +----+----+----+----+
                |    | A0 | AR |    | 2
                +----+----+----+----+
                  0    1    2    3"""
    */

    /*
     void setUp(){
         for(int x=0; x<NUMBER_OF_COLUMNS; x++){
             for(int y=0; y<NUMBER_OF_ROWS; y++){
                 allPositions[x][y] = new Position(x,y);
             }
         }
         amazon11 = new Amazon(allPositions[1][1],0);
         board.fill(new EmptyFigureGenerator());
         board.setFigure(allPositions[1][1],amazon11);
         board.setFigure(allPositions[1][2], new Amazon(allPositions[1][2],0));
         board.setFigure(allPositions[2][0], new Amazon(allPositions[2][0],1));
         board.setFigure(allPositions[2][2], ARROW_FIGURE);
         accessiblePositions.add(allPositions[0][0]);
         accessiblePositions.add(allPositions[0][1]);
         accessiblePositions.add(allPositions[0][2]);
         accessiblePositions.add(allPositions[1][0]);
         accessiblePositions.add(allPositions[2][1]);
         accessiblePositions.add(allPositions[3][1]);
     }
*/

/*
    @Test
    void testCanMoveTo() {
        for (int x = 0; x < NUMBER_OF_COLUMNS; x++) {
            for (int y = 0; y < NUMBER_OF_ROWS; y++) {
                assertThat(amazon11.canMoveTo(allPositions[x][y], board))
                        .isEqualTo(accessiblePositions.contains(allPositions[x][y]));
            }
        }
    }
 */

    /*
    @Test
    void testGetAccessiblePositions() {
         assertThat(amazon11.getAccessiblePositions(board))
                 .hasSameElementsAs(accessiblePositions)
                 .hasSize(accessiblePositions.size());
    }

     */
}