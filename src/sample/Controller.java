package sample;

import AutomataFX.DFAEdge;
import AutomataFX.DFANode;
import FiniteAutomata.DFA;
import FiniteAutomata.Edge;
import FiniteAutomata.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.HashSet;
import java.util.stream.Collectors;

public class Controller {
    private DFA automata = new DFA();
    private int count = 0;
    private HashSet<DFAEdge> myEdges = new HashSet<>();

    @FXML
    private VBox mainContainer;

    @FXML
    private MenuItem about;

    @FXML
    private Button testButton;

    @FXML
    private Pane DFAPane;


    @FXML
    private void openHelpMenu(ActionEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private void runAutomata() {

    }

    @FXML
    private void addNode(MouseEvent event) {
        if(automata.getNodes().stream().noneMatch(p -> p.getCoords().distance(event.getX(), event.getY()) <= 25)) {
            Node n = new Node(event.getX(), event.getY(), (int) (Math.random() * 1000));
            System.out.println(event.getX());
            while (automata.getNodes().contains(n)) {
                n = new Node(event.getX(), event.getY(), (int) (Math.random() * 1000));
            }
            automata.addNode(n);
            DFANode newNode = new DFANode(n);
            DFAPane.getChildren().add(newNode);
        } else {
            myEdges.forEach(DFAEdge::updateLocation);
        }
        try {
            automata.removeNodes();
        } catch (Exception e) {
            System.err.println("Could not remove edges now");
        }
    }

    @FXML
    private void removeNode(ActionEvent event) {

    }

    @FXML
    private void addEdge(MouseEvent event) {
        if(event.isShiftDown()) {
            count++;
            if(count == 2) {
                count = 0;
                var selectedNodes = automata.getNodes().stream().filter(Node::isSelected).collect(Collectors.toSet());
                if(selectedNodes.size() == 1) {
                    var iter = selectedNodes.iterator();
                    var node = iter.next();
                    var edge = new Edge(node, node, '1');
                    node.deselect();
                    automata.addTransition(edge);
                    DFAEdge dEdge = new DFAEdge(edge);
                    DFAPane.getChildren().add(dEdge);
                    myEdges.add(dEdge);
                } else {
                    var iter = selectedNodes.iterator();
                    var node = iter.next();
                    node.deselect();
                    var node1 = iter.next();
                    node1.deselect();
                    var edge = new Edge(node, node1, '1');
                    automata.addTransition(edge);
                    DFAEdge dEdge = new DFAEdge(edge);
                    DFAPane.getChildren().add(dEdge);
                    myEdges.add(dEdge);
                }
            }
        }
    }

    @FXML
    private void removeEdge(ActionEvent event) {

    }

    @FXML
    private void selectNode(ActionEvent event) {

    }

    @FXML
    private void toggleAccepting(ActionEvent event) {

    }



}
