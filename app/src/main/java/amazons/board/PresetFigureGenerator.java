package amazons.board;

import amazons.figures.Figure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import amazons.figures.MovableFigure;
import static amazons.figures.EmptyFigure.EMPTY_FIGURE;

public class PresetFigureGenerator implements FigureGenerator{
    private final List<MovableFigure> movableFigures;
    public PresetFigureGenerator(List<MovableFigure> movableFigures){
        this.movableFigures = movableFigures;
    }
    public PresetFigureGenerator(Map<MovableFigure,Position> movableFigures){
        this.movableFigures = new ArrayList<>();
        this.movableFigures.addAll(movableFigures.keySet());
    }
    @Override
    public Figure nextFigure(Position position) {
        for(MovableFigure movableFigure : movableFigures){
            if(movableFigure.getPosition().equals(position)){
                return (Figure) movableFigure;
            }
        }
        return EMPTY_FIGURE;
    }
}
