package pkg.playground.Node;

import java.util.ArrayList;

public class Layer {
    private ArrayList<Group> layer;

    public Layer(){
        layer = new ArrayList<>();
    }
    public Layer(Node root) {
            layer = new ArrayList<>();
            layer.add(new Group(root));
    }
    void addGroup(Group group) {
        this.layer.add(group);
    }
    public ArrayList<Group> getGroupArray() {
        return layer;
    }

    //Array of Arrays of Nodes

}
