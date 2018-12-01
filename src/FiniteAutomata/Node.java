package FiniteAutomata;

import javafx.geometry.Point2D;

import java.util.Objects;

public class Node {
    private double xCoord, yCoord;
    private int id;
    private boolean accepting, remove, selected;

    public Node(double xCoord, double yCoord, int id) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.id = id;
        accepting = false;
        remove = false;
        selected = false;
    }

    public Point2D getCoords() {
        return new Point2D(xCoord, yCoord);
    }

    public void setCoords(Point2D center) {
        xCoord = center.getX();
        yCoord = center.getY();
    }

    public boolean isAccepting() {
        return accepting;
    }

    public void toggleAccepting() {
        accepting = !accepting;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public boolean shouldRemove() {
        return remove;
    }

    public void select() {
        selected = true;
    }

    public void deselect() {
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;
        Node node = (Node) o;
        return Double.compare(node.xCoord, xCoord) == 0 &&
                Double.compare(node.yCoord, yCoord) == 0 &&
                id == node.id;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
