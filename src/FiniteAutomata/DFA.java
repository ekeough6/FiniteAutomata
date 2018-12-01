package FiniteAutomata;

import java.util.HashSet;

public class DFA {
    private HashSet<Node> nodes, acceptingStates;
    private HashSet<Edge> edges;
    private HashSet<Character> language;
    private Node startingState, currentNode;

    public DFA() {
        nodes = new HashSet<>();
        acceptingStates = new HashSet<>();
        edges = new HashSet<>();
        language = new HashSet<>();
        startingState = null;
        currentNode = null;
    }

    public HashSet<Node> getNodes() {
        return nodes;
    }

    public HashSet<Node> getAcceptingStates() {
        return acceptingStates;
    }

    public HashSet<Edge> getEdges() {
        return edges;
    }

    public HashSet<Character> getLanguage() {
        return language;
    }

    public Node getStartingState() {
        return startingState;
    }

    public Node getCurrentNode() {
        return currentNode;
    }

    public boolean isValidSymbol(char symbol) {
        return language.contains(symbol);
    }

    public Node nextNode(char symbol) {
        var validNodes = edges.stream().filter(e -> e.getFromNode().equals(currentNode) && e.getTransitionSymbol() == symbol).iterator();
        if(validNodes.hasNext()) {
            return validNodes.next().getToNode();
        }
        return null;
    }

    public void reset() {
        currentNode = startingState;
    }

    public boolean hasTransition(char symbol) {
        return edges.stream().filter(e -> e.getFromNode().equals(currentNode) && e.getTransitionSymbol() == symbol).iterator().hasNext();
    }

    public boolean isInAcceptingState() {
        return acceptingStates.contains(currentNode);
    }

    public void addNode(Node node) {
        nodes.add(node);
    }

    public void removeNode(Node node) {
        nodes.remove(node);
        edges.removeIf(e -> (e.getFromNode().equals(node) || e.getToNode().equals(node)));
        removeAcceptingState(node);
    }

    public void addTransition(Edge edge) {
        edges.add(edge);
    }

    public void removeTransition(Edge edge) {
        edges.remove(edge);
    }

    public void setAcceptingState(Node node) {
        if(nodes.contains(node))
            acceptingStates.add(node);
    }

    public void removeAcceptingState(Node node) {
        acceptingStates.remove(node);
    }

    public void toggleAcceptingState(Node node) {
        if(acceptingStates.contains(node)) {
            removeAcceptingState(node);
        } else {
            setAcceptingState(node);
        }
    }

    public void removeNodes() {
        var removeNodes = nodes.stream().filter(Node::shouldRemove).iterator();
        while(removeNodes.hasNext()) {
            removeNode(removeNodes.next());
        }
    }


}
