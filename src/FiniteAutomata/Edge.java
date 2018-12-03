package FiniteAutomata;

import java.util.Objects;

public class Edge {
    private Node fromNode, toNode;
    private boolean remove;
    private char transitionSymbol;

    public Edge(Node fromNode, Node toNode, char transitionSymbol) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.transitionSymbol = transitionSymbol;
        remove = false;
    }

    public Node getFromNode() {
        return fromNode;
    }

    public Node getToNode() {
        return toNode;
    }

    public char getTransitionSymbol() {
        return transitionSymbol;
    }

    public boolean shouldRemove() {
        return remove || fromNode.shouldRemove() || toNode.shouldRemove();
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return transitionSymbol == edge.transitionSymbol &&
                Objects.equals(fromNode, edge.fromNode) &&
                Objects.equals(toNode, edge.toNode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromNode, toNode, transitionSymbol);
    }

    public void setTransitionSymbol(char c) {
        transitionSymbol = c;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "fromNode=" + fromNode +
                ", toNode=" + toNode +
                ", transitionSymbol=" + transitionSymbol +
                '}';
    }
}
