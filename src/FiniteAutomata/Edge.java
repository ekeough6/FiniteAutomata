package FiniteAutomata;

import java.util.Objects;

public class Edge {
    private Node fromNode, toNode;
    private char transitionSymbol;

    public Edge(Node fromNode, Node toNode, char transitionSymbol) {
        this.fromNode = fromNode;
        this.toNode = toNode;
        this.transitionSymbol = transitionSymbol;
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
}
