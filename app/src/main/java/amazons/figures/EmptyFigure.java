package amazons.figures;

public class EmptyFigure extends NeutralFigure {
    public static EmptyFigure EMPTY_FIGURE;
    private EmptyFigure(){
        EMPTY_FIGURE = new EmptyFigure();
    }
}
