package AutomataFX;

import FiniteAutomata.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class DFAStarting extends Polygon {

    public DFAStarting() {
        super();
        setFill(Color.GOLD);
    }

    public void move(Node n) {
        getPoints().clear();
        getPoints().addAll(n.getCoords().getX() - 25, n.getCoords().getY(), n.getCoords().getX() - 45, n.getCoords().getY() - 10, n.getCoords().getX() - 45, n.getCoords().getY() + 10);
    }

    public void remove() {
        getPoints().clear();
    }
}
