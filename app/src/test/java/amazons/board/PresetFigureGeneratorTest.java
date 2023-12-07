package amazons.board;
import static org.assertj.core.api.Assertions.assertThat;

import amazons.figures.MovableFigure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;
import amazons.figures.Amazon;

import java.util.ArrayList;
import java.util.List;

import static amazons.player.PlayerID.PLAYER_ZERO;
public class PresetFigureGeneratorTest {
    private Amazon amazon00;
    private PresetFigureGenerator presetFigureGenerator;
    @BeforeEach
    void setUp(){
        Board board;
        board = new MatrixBoard(3,2);
        amazon00 = new Amazon(new Position(0,0),PLAYER_ZERO.index);
        board.setFigure(new Position(1,1),EMPTY_FIGURE);
        board.setFigure(new Position(0,0),amazon00);
        List<MovableFigure> movableFigures = new ArrayList<>();
        movableFigures.add(amazon00);
        presetFigureGenerator = new PresetFigureGenerator(movableFigures);
    }
    @Test
    void testNextFigure(){
        assertThat(presetFigureGenerator.nextFigure(new Position(0,0))).isEqualTo(amazon00);
        assertThat(presetFigureGenerator.nextFigure(new Position(2,2))).isEqualTo(EMPTY_FIGURE);
    }
}
