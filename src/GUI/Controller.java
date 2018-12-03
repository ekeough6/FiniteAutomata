package GUI;

import AutomataFX.DFAEdge;
import AutomataFX.DFANode;
import AutomataFX.DFAStarting;
import FiniteAutomata.DFA;
import FiniteAutomata.Edge;
import FiniteAutomata.Node;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.stream.Collectors;

public class Controller {
    private DFA automata = new DFA();
    private int count = 0;
    private HashSet<DFAEdge> myEdges = new HashSet<>();
    private static char transition = '1';
    private DFAStarting starting = new DFAStarting();

    class UpdateThread extends Thread {
        public void run() {
            try {
                while(true) {
                    Platform.runLater(Controller.this::updateScreen);
                    Thread.sleep(100);
                    if(Thread.activeCount() == 1)
                        break;
                    }
            } catch (InterruptedException ie) {
                System.err.println("Interupted");
            }
        }
    }


    @FXML
    private VBox mainContainer;

    @FXML
    private MenuItem about;

    @FXML
    private Button testButton;

    @FXML
    private Pane DFAPane;

    UpdateThread updater = new UpdateThread();

    public void startUpdater() {
        updater.start();
    }

    private void updateScreen() {
        automata.getNodes().stream().filter(Node::shouldStart).forEach(e -> {
            starting.move(e);
            e.unStart();
            automata.setStartingState(e);
        });
        if(automata.getStartingState() != null)
            starting.move(automata.getStartingState());
        else
            starting.remove();
        try {
            removeEdge();
        } catch (ConcurrentModificationException ex) {

        }
    }


    @FXML
    private void openHelpMenu(ActionEvent event) {
        System.out.println("Hello");
    }

    @FXML
    private void runAutomata() {

    }

    @FXML
    private void clearScreen() {
        automata = new DFA();
        DFAPane.getChildren().clear();
    }

    @FXML
    private void addNode(MouseEvent event) {
        if(updater.getState().equals(Thread.State.NEW)) {
            updater.start();
        }
        if(!DFAPane.getChildren().contains(starting)) {
            DFAPane.getChildren().add(starting);
        }
        if(event.getButton().equals(MouseButton.PRIMARY) && automata.getNodes().stream().noneMatch(p -> p.getCoords().distance(event.getX(), event.getY()) <= 25)
                && myEdges.stream().noneMatch(p -> p.getTransitionCoords().distance(event.getX(), event.getY()) <= 15)) {
            Node n = new Node(event.getX(), event.getY(), (int) (Math.random() * 1000));
            System.out.println(event.getX());
            while (automata.getNodes().contains(n)) {
                n = new Node(event.getX(), event.getY(), (int) (Math.random() * 1000));
            }

            automata.addNode(n);
            if(automata.getNodes().size() == 1) {
                automata.setStartingState(n);
                starting.move(n);
            }
            DFANode newNode = new DFANode(n);
            DFAPane.getChildren().add(newNode);
        } else {
            updateEdges();
        }
        try {
            automata.removeNodes();
        } catch (Exception e) {
            System.err.println("Could not remove Nodes now");
        }
        removeEdge();
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
                    var edge = new Edge(node, node, transition);
                    node.deselect();
                    automata.addTransition(edge);
                    DFAEdge dEdge = new DFAEdge(edge);
                    DFAPane.getChildren().add(dEdge);
                    myEdges.add(dEdge);
                    DFAPane.getChildren().add(dEdge.getTransition());
                } else {
                    var iter = selectedNodes.iterator();
                    var node = iter.next();
                    node.deselect();
                    var node1 = iter.next();
                    node1.deselect();
                    var edge = new Edge(node, node1, transition);
                    automata.addTransition(edge);
                    DFAEdge dEdge = new DFAEdge(edge);
                    DFAPane.getChildren().add(dEdge);
                    myEdges.add(dEdge);
                    DFAPane.getChildren().add(dEdge.getTransition());
                }
            }
        }
    }

    @FXML
    private void removeEdge() {
        myEdges.stream().filter(DFAEdge::shouldRemove).forEach(DFAEdge::remove);
        automata.removeEdges();
    }

    @FXML
    private void updateEdges() {
        myEdges.forEach(DFAEdge::updateLocation);

    }

    @FXML
    private void selectNode(ActionEvent event) {

    }

    @FXML
    private void toggleAccepting(ActionEvent event) {

    }

    @FXML
    private void setTransitionToOne(){
        transition = '1';
    }

    @FXML
    private void setTransitionToZero() {
        transition = '0';
    }

    public static char getTransition() {
        return transition;
    }

}
