package AutomataFX;

import FiniteAutomata.Node;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DFANode extends Circle {

    private Node node;

    public DFANode(Node node) {
        super();
        this.node = node;
        setOnMouseDragged(e -> {
            node.setCoords(new Point2D(e.getX(), e.getY()));
            refreshLocation();
        });
        setOnMouseClicked(e -> {
            if(e.getButton().equals(MouseButton.PRIMARY)){
                if(e.isControlDown()) {
                    node.makeStart();
                }

                if(e.isShiftDown()) {
                    node.select();
                }

                if(e.getClickCount() == 2){
                    toggleAccepting();
                }

            }
            if(e.getButton().equals(MouseButton.SECONDARY)) {
                node.setRemove(true);
                setVisible(false);
            }
        });
        setRadius(25);
        setFill(Color.LIGHTBLUE);
        setStroke(Color.TRANSPARENT);
        refreshLocation();

    }

    private void refreshLocation() {
        Point2D center = node.getCoords();
        setCenterX(center.getX());
        setCenterY(center.getY());
    }

    private void toggleAccepting() {
        node.toggleAccepting();
        if(node.isAccepting()) {
            setFill(Color.GREEN);
        } else {
            setFill(Color.LIGHTBLUE);
        }
    }

    public boolean representsNode(Node n) {
        return n.equals(node);
    }

    public void toggleOutLine() {
        setStroke((getStroke().equals(Color.BLACK)) ? Color.TRANSPARENT : Color.BLACK);
    }

    public void removeOutLine() {
        setStroke(Color.TRANSPARENT);
    }

    public void redOutLine() {
        setStroke(Color.RED);
    }

}
