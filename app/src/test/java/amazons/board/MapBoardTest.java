package amazons.board;


import amazons.figures.IllegalMoveException;
import amazons.figures.Amazon;
import amazons.figures.Figure;
import amazons.figures.MovableFigure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;

import static amazons.figures.ArrowFigure.ARROW_FIGURE;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;
import static org.assertj.core.api.Assertions.*;

class MapBoardTest {

    private MapBoard testBoard;
    private final Random random = new Random();
    private final int NUMBER_OF_COLUMNS = 4;
    private final int NUMBER_OF_ROWS = 3;
    private Amazon amazon00Player0;
    private final Position position00 = new Position(0,0);
    private Amazon amazon01Player0;
    private final Position position01 = new Position(0,1);

    private Amazon amazon32Player0;
    private final Position position32 = new Position(3,2);

    private Amazon amazon11Player1;
    private final Position position11 = new Position(1,1);

    private Amazon amazon30Player1;
    private final Position position30 = new Position(3,0);

    private Amazon amazon12Player1;
    private final Position position12 = new Position(1,2);



    @BeforeEach
    void setAmazons(){
        amazon00Player0 = new Amazon(position00,0);
        amazon01Player0 = new Amazon(position01, 0);
        amazon32Player0 = new Amazon(position32, 0);
        amazon11Player1 = new Amazon(position11, 1);
        amazon30Player1 = new Amazon(position30, 1);
        amazon12Player1 = new Amazon(position12, 1);
    }

    @BeforeEach
    void setTestBoard(){
        testBoard = new MapBoard(NUMBER_OF_COLUMNS, NUMBER_OF_ROWS);
    }

    @Test
    void testGetSetFigure() {
        testBoard.setFigure(position00,amazon00Player0);
        assertThat(testBoard.getFigure(position00)).isSameAs(amazon00Player0);
        testBoard.setFigure(position00,EMPTY_FIGURE);
        assertThat(testBoard.getFigure(position00)).isSameAs(EMPTY_FIGURE);
        testBoard.setFigure(position12,ARROW_FIGURE);
        assertThat(testBoard.getFigure(position12)).isSameAs(ARROW_FIGURE);
    }

    @Test
    void testGetNumberOfRows() {
        MapBoard board  = new MapBoard(2,3);
        assertThat(board.getNumberOfRows()).isEqualTo(3);
    }

    @Test
    void testGetNumberOfColumns() {
        MapBoard board  = new MapBoard(2,3);
        assertThat(board.getNumberOfColumns()).isEqualTo(2);
    }

    @Test
    void testInitializeEmpty() {
        testBoard.fill(new EmptyFigureGenerator());
        assertThat(testBoard.getFigure(new Position(0,0))).isSameAs(EMPTY_FIGURE);
        assertThat(testBoard.getFigure(new Position(1,1))).isSameAs(EMPTY_FIGURE);
        assertThat(testBoard.getFigure(new Position(3,2))).isSameAs(EMPTY_FIGURE);
    }
    @Test
    void testInitializePreset() {
        setAmazons();
        Map<MovableFigure,Position> positionMap = new HashMap<>();
        positionMap.put(amazon00Player0,position00);
        positionMap.put(amazon01Player0,position01);
        positionMap.put(amazon32Player0,position32);
        positionMap.put(amazon11Player1,position11);
        positionMap.put(amazon30Player1,position30);
        positionMap.put(amazon12Player1,position12);
        testBoard.fill(new PresetFigureGenerator(positionMap));
        assertThat(testBoard.getFigure(position00)).isSameAs(amazon00Player0);
        assertThat(testBoard.getFigure(position01)).isSameAs(amazon01Player0);
        assertThat(amazon32Player0.getPosition()).isEqualTo(position32);
        assertThat(testBoard.getFigure(position11)).isSameAs(amazon11Player1);
        assertThat(testBoard.getFigure(position30)).isSameAs(amazon30Player1);
        assertThat(testBoard.getFigure(new Position(2,1))).isSameAs(EMPTY_FIGURE);
        assertThat(testBoard.getFigure(new Position(3,1))).isSameAs(EMPTY_FIGURE);
    }

    @Test
    void testInitializeRandom() {
        List<MovableFigure> figures  = List.of(amazon11Player1,amazon00Player0,amazon01Player0,amazon12Player1,amazon32Player0,amazon30Player1);
        testBoard.fill(new RandomFigureGenerator(random,figures,testBoard.positionIterator()));
        List<Position> nonEmptyPositions = new ArrayList<>();
        testBoard.positionIterator().forEachRemaining(p -> {if(!testBoard.isEmpty(p)) nonEmptyPositions.add(p);});
        assertThat(nonEmptyPositions.stream().map(p -> testBoard.getFigure(p))).containsExactlyInAnyOrder(
                amazon00Player0, amazon01Player0, amazon32Player0,
                amazon30Player1, amazon12Player1, amazon11Player1);
        assertThat(nonEmptyPositions).contains(amazon00Player0.getPosition());
        assertThat(nonEmptyPositions).contains(amazon01Player0.getPosition());
        assertThat(nonEmptyPositions).contains(amazon32Player0.getPosition());
        assertThat(nonEmptyPositions).contains(amazon30Player1.getPosition());
        assertThat(nonEmptyPositions).contains(amazon12Player1.getPosition());
        assertThat(nonEmptyPositions).contains(amazon11Player1.getPosition());
    }


    @Test
    void testMoveEmptyFigure() {
        testBoard.setFigure(position12,EMPTY_FIGURE);
        assertThatThrownBy(() -> testBoard.moveFigure(position12,position00))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    void testMoveArrowFigure() {
        testBoard.setFigure(position12,ARROW_FIGURE);
        assertThatThrownBy(() -> testBoard.moveFigure(position12,position00)).isInstanceOf(IllegalMoveException.class);
    }

    @Test
    void testMoveAmazonFigure() {
        testBoard.setFigure(position12,amazon12Player1);
        assertThatThrownBy(() -> testBoard.moveFigure(position12,position00)).isInstanceOf(IllegalMoveException.class);
        testBoard.setFigure(position00,amazon12Player1);
        try {
            testBoard.moveFigure(position00, position11);
        } catch(IllegalMoveException e) {
            assertThat(false).withFailMessage("move("+position00+","+position11+") should not throw exception").isTrue();
        }
        assertThat(testBoard.getFigure(position00)).isSameAs(EMPTY_FIGURE);
        assertThat(testBoard.getFigure(position11)).isSameAs(amazon12Player1);
    }

    @Test
    void testToString() {
        assertThat(testBoard.toString()).isEqualTo(
                """
                +----+----+----+----+
                |    |    |    |    | 0
                +----+----+----+----+
                |    |    |    |    | 1
                +----+----+----+----+
                |    |    |    |    | 2
                +----+----+----+----+
                  0    1    2    3\040\040"""
        );
        testBoard.setFigure(position30,amazon30Player1);
        testBoard.setFigure(position00,amazon00Player0);
        testBoard.setFigure(position11,ARROW_FIGURE);
        assertThat(testBoard.toString()).isEqualTo(
                """
                +----+----+----+----+
                | A0 |    |    | A1 | 0
                +----+----+----+----+
                |    | XX |    |    | 1
                +----+----+----+----+
                |    |    |    |    | 2
                +----+----+----+----+
                  0    1    2    3\040\040"""
        );
    }

    @Test
    void testPositionIterator() {
        int rowIndex = 0;
        int columnIndex = 0;
        Iterator<Position> positionIterator = testBoard.positionIterator();
        while(positionIterator.hasNext()){
            assertThat(columnIndex).withFailMessage("iterator should not have a next element")
                    .isLessThan(NUMBER_OF_COLUMNS);
            assertThat(rowIndex).withFailMessage("iterator should not have a next element")
                    .isLessThan(NUMBER_OF_ROWS);
            Position position = positionIterator.next();
            Position expectedPosition = new Position(columnIndex, rowIndex);
            assertThat(position).isEqualTo(expectedPosition);
            if(columnIndex < NUMBER_OF_COLUMNS - 1)
                columnIndex ++;
            else{
                columnIndex = 0;
                rowIndex++;
            }
        }
    }

    @Test
    void testIterator() {
        testBoard.setFigure(position00, amazon00Player0);
        testBoard.setFigure(position01, amazon01Player0);
        testBoard.setFigure(position32, amazon32Player0);
        testBoard.setFigure(position11,ARROW_FIGURE);
        testBoard.setFigure(position30, ARROW_FIGURE);
        Iterator<Figure> iterator = testBoard.iterator();
        System.out.println(testBoard.toString());
        List<Figure> figures = new ArrayList<>();
        int emptyFigureCount = 0;
        int arrowFigureCount = 0;
        int amazonFigureCount = 0;

        while(iterator.hasNext()){
            assertThat(emptyFigureCount + arrowFigureCount + amazonFigureCount)
                    .isLessThanOrEqualTo(NUMBER_OF_COLUMNS * NUMBER_OF_ROWS);
            Figure figure = iterator.next();
            if(figure == EMPTY_FIGURE) emptyFigureCount++;
            else if (figure == ARROW_FIGURE) arrowFigureCount++;
            else {
                amazonFigureCount++;
                figures.add(figure);
            }
        }
        System.out.println(emptyFigureCount);
        System.out.println(arrowFigureCount);
        System.out.println(amazonFigureCount);
        assertThat(emptyFigureCount).isEqualTo(NUMBER_OF_COLUMNS*NUMBER_OF_ROWS - 5);
        assertThat(arrowFigureCount).isEqualTo(2);
        assertThat(amazonFigureCount).isEqualTo(3);
        assertThat(figures).hasSize(3)
                .doesNotHaveDuplicates()
                .containsExactlyInAnyOrder(amazon00Player0, amazon01Player0, amazon32Player0);
    }
    @Test
    void testIsEmpty() {
        testBoard.setFigure(position32,EMPTY_FIGURE);
        assertThat(testBoard.isEmpty(position32)).isTrue();
    }
}
