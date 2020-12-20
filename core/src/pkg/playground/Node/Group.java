package pkg.playground.Node;

import java.util.ArrayList;

public class Group {
    private ArrayList<Node> group;
    private Layer layer;
    public Group(){
        group = new ArrayList<>();
    }
    public Group (Node root) {
        group = new ArrayList<>();
        group.add(root);
    }
    public Group(Layer layer) {
        group = new ArrayList<>();
        setLayer(layer);
    }

    public void addNode(Node node) {
        group.add(node);
    }
    public ArrayList<Node> getNodeArray() {
        return group;
    }
    public Node getRoot() { // --todo: remove all uses of root, use parent->child system
        return group.get(0);
    }
    public Node getNode(int index) {
        return group.get(index);
    }
    public Node getLast() {
        return group.get(group.size()-1);
    }
    public int size() {
        return group.size();
    }

    public Layer getLayer() {
        return layer;
    }

    public void setLayer(Layer layer) {
        this.layer = layer;
    }
    //Array of Nodes

}
