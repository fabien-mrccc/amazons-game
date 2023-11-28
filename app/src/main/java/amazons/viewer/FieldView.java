package amazons.viewer;

import amazons.board.Board;
import amazons.board.Position;
import amazons.controller.GameController;
import amazons.figures.Figure;
import amazons.game.TurnPhase;
import amazons.util.ImageUtil;
import javafx.animation.PauseTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.util.Duration;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

 import static amazons.figures.ArrowFigure.ARROW_FIGURE;
 import static amazons.figures.EmptyFigure.EMPTY_FIGURE;

public class FieldView extends Label {
    private static final String defaultStyleBlack = "-fx-background-color: gray;";
    private static final String defaultStyleWhite = "-fx-background-color: white;";
    private static final String highlightStyleBlack = "-fx-background-color: forestgreen;";
    private static final String highlightStyleWhite = "-fx-background-color: palegreen;";
    private static final String highlightShootStyleBlack = "-fx-background-color: darkred;";
    private static final String highlightShootStyleWhite = "-fx-background-color: #ff5555;";
    private final static Map<String, Image> figureImages = new HashMap<>();
    private final Position position;
    private final GameController controller;
    private final Board board;

    private boolean isAmazonDst = false;

    private final PauseTransition pauseTransition = new PauseTransition(Duration.millis(1000));

    public FieldView(Position position, GameController controller) {
        this.position = position;
        this.controller = controller;
        this.board = controller.getBoard();
        setAlignment(Pos.CENTER);
        setBackGroundColor();
        setFigureIcon();

        setOnMouseEntered(e -> onMouseEntered());
        setOnMouseExited(e -> onMouseExited());

        setOnDragDetected(this::onDragDetected);
        setOnDragOver(this::onDragOver);
        setOnDragExited(this::onDragExited);
        setOnDragDropped(this::onDragDropped);
        setOnDragDone(this::onDragDone);

        setMinSize(50, 50);
        setMaxSize(50, 50);
    }

    public Position getPosition(){
        return position;
    }
    private Color getColor() {
        return ((position.getX() + position.getY()) % 2) == 0 ? Color.WHITE : Color.BLACK;
    }

    private void setBackGroundColor(){
        setStyle(getColor() == Color.BLACK ? defaultStyleBlack : defaultStyleWhite);
    }


    private void setHighlightBackGroundColor(TurnPhase phase){
        switch (phase){
            case AMAZON_PHASE -> setHighlightBackGroundColor();
            case ARROW_PHASE  -> setHighlightShootBackGroundColor();
            default -> setBackGroundColor();
        }
    }
    private void setHighlightBackGroundColor(){
        setStyle(getColor() == Color.BLACK ? highlightStyleBlack : highlightStyleWhite);
    }

    void setHighlightBackGroundColorTemp(){
        pauseTransition.setOnFinished(e -> setBackGroundColor());
        setHighlightBackGroundColor();
        pauseTransition.playFromStart();
    }
     void setHighlightShootBackGroundColor(){
        setStyle(getColor() == Color.BLACK ? highlightShootStyleBlack : highlightShootStyleWhite);
    }

    void setHighlightShootBackGroundColorTemp(){
        pauseTransition.setOnFinished(e -> setBackGroundColor());
        setHighlightShootBackGroundColor();
        pauseTransition.playFromStart();
    }

    private ImageView getImageView() {
        if(board.getFigure(position) == EMPTY_FIGURE) return null;
        return new ImageView(getImage());
    }

    private Image getImage() {
        Figure figure = board.getFigure(position);
        if(figure == EMPTY_FIGURE) return null;
        String imageName = getFigureImageName(figure);
        if (!figureImages.containsKey(imageName))
            figureImages.put(imageName,ImageUtil.loadImage(imageName,50,50));
        return figureImages.get(imageName);
    }

    private Image getArrowImage() {
        String imageName = "images/" + controller.getCurrentPlayerID().color + "_arrow.png";
        if (!figureImages.containsKey(imageName))
            figureImages.put(imageName,ImageUtil.loadImage(imageName,50,50));
        return figureImages.get(imageName);
    }

    private String getFigureImageName(Figure figure){
        StringBuilder builder = new StringBuilder("images/");
        if (figure == EMPTY_FIGURE) throw new IllegalArgumentException("Empty figure has no image");
        if (figure == ARROW_FIGURE) return builder.append("cross.png").toString();
        builder.append(figure.getPlayerID().color);
        builder.append("_queen.png");
        return builder.toString();
    }
    void setFigureIcon(){
        setGraphic(getImageView());
    }

    private void onMouseEntered() {
        Figure figure = board.getFigure(position);
        if(figure.getPlayerID() == controller.getCurrentPlayerID()
            && controller.getPhase() == TurnPhase.AMAZON_PHASE){
            setHighlightBackGroundColor();
        }
        if(isAmazonDst && controller.getPhase() == TurnPhase.ARROW_PHASE){
            setHighlightShootBackGroundColor();
        }
    }

    private void onMouseExited() {
        setBackGroundColor();
    }

    private void onDragDetected(MouseEvent e) {
        if(!isDraggable()) return;
        Dragboard dragBoard = startDragAndDrop(TransferMode.MOVE);
        if(controller.getPhase() == TurnPhase.AMAZON_PHASE)
            dragBoard.setDragView(getImage());
        if(controller.getPhase() == TurnPhase.ARROW_PHASE)
            dragBoard.setDragView(getArrowImage());
        ClipboardContent content = new ClipboardContent();
        content.put(Position.POSITION_FORMAT,position);
        dragBoard.setContent(content);
        e.consume();
    }

    private void onDragOver(DragEvent e) {
        if (e.getDragboard().hasContent(Position.POSITION_FORMAT)) {
            FieldView sourceField = (FieldView) e.getGestureSource();
            if (board.getFigure(sourceField.getPosition()).canMoveTo(position,board)) {
                e.acceptTransferModes(TransferMode.MOVE);
                setHighlightBackGroundColor(controller.getPhase());
            }
        }
        e.consume();
    }

    private void onDragExited(DragEvent e) {
        setBackGroundColor();
    }
    private void onDragDone(DragEvent e) {
        if(e.getTransferMode() == TransferMode.MOVE) {
            setFigureIcon();
            isAmazonDst = false;
            controller.nextPhase();
        }
        e.consume();
    }

    private void onDragDropped(DragEvent e) {
        Dragboard db = e.getDragboard();
        boolean success = false;
        if (db.hasContent(Position.POSITION_FORMAT)) {
            Position startPosition = ((FieldView) e.getGestureSource()).position;
                switch (controller.getPhase()) {
                    case AMAZON_PHASE -> {
                        controller.moveFigure(startPosition, position);
                        setFigureIcon();
                        isAmazonDst = true;
                        success = true;
                    }
                    case ARROW_PHASE -> {
                        controller.shootArrow(startPosition, position);
                        setFigureIcon();
                        success = true;
                    }
                    default -> {
                    }
                }
        }
        e.setDropCompleted(success);
        e.consume();
    }


    private boolean isDraggable(){
        return (controller.getPhase() == TurnPhase.ARROW_PHASE && isAmazonDst)
                || (controller.getPhase() == TurnPhase.AMAZON_PHASE && board.getFigure(position).getPlayerID() == controller.getCurrentPlayerID());
    }

}
