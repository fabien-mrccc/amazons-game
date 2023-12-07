package amazons.figures;

public final class EmptyFigure extends NeutralFigure {
    public static final EmptyFigure EMPTY_FIGURE = new EmptyFigure();
    private EmptyFigure(){}

    @Override
    public String toString(){
        return "  ";
    }
}
