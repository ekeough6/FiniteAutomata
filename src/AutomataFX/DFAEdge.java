package AutomataFX;

import FiniteAutomata.Edge;
import GUI.Controller;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;

public class DFAEdge extends QuadCurve {

    private Edge edge;
    private Text transition;
    private boolean transitionSelected;


    public DFAEdge(Edge edge) {
        this.edge = edge;


        transition = new Text(edge.getTransitionSymbol() + "");
        transitionSelected = false;
        //setting the transition value for the edge
        transition.setOnMouseClicked(e -> {
            transition.setText(Controller.getTransition() + "");
        });

        updateLocation();
        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                edge.setRemove(true);
                remove();
            }
        });
    }

    public void updateLocation() {
        Point2D from = edge.getFromNode().getCoords();
        Point2D to = edge.getToNode().getCoords();
        if(edge.getToNode().equals(edge.getFromNode())) {
            setControlX(from.getX());
            setControlY(from.getY() + 100);
            setEndX(to.getX() + 25/Math.sqrt(2));
            setEndY(to.getY() + 25/Math.sqrt(2));
            setStartX(from.getX() - 25/Math.sqrt(2));
            setStartY(from.getY() + 25/Math.sqrt(2));
        } else {
            setControlX(from.midpoint(to).getX());
            setControlY(from.midpoint(to).getY() + 40);
            setEndX(to.getX());
            setEndY(to.getY());
            setStartX(from.getX());
            setStartY(from.getY());
        }
        transition.setLayoutX(getControlX());
        transition.setLayoutY(getControlY());
    }

    public void remove() {
        setVisible(false);
        transition.setVisible(false);
    }

    public Text getTransition() {
        return transition;
    }

    public boolean shouldRemove() {
        return edge.shouldRemove();
    }

    public Point2D getTransitionCoords() {
        return new Point2D(transition.getLayoutX(), transition.getLayoutY());
    }
}
