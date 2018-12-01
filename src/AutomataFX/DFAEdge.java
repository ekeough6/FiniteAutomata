package AutomataFX;

import FiniteAutomata.Edge;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.QuadCurve;

import java.awt.event.MouseEvent;

public class DFAEdge extends QuadCurve {

    private Edge edge;

    public DFAEdge(Edge edge) {
        this.edge = edge;

        updateLocation();

        setFill(Color.TRANSPARENT);
        setStroke(Color.BLACK);
        setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                edge.setRemove(true);
                setVisible(false);
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
    }

    public void remove() {
        setVisible(false);
    }

    public boolean shouldRemove() {
        return edge.shouldRemove();
    }
}
