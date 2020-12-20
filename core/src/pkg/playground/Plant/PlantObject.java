package pkg.playground.Plant;

import com.badlogic.gdx.math.Vector2;
import pkg.playground.Node.*;
import pkg.playground.Renderable;


import java.util.ArrayList;

public class PlantObject implements Renderable {
    NodeManager nodeManager;
    public PlantObject (Vector2 vector) {
        nodeManager = new NodeManager();
        Layer layer = nodeManager.newLayer();
        Group group = nodeManager.newGroup(layer);
        Node root   = nodeManager.newNode(vector, group);
        root.setParent(root); //it's its own parent!

        System.out.println("Plant Created");

    }

    //-----------------//
    // Create branches //
    //-----------------//


    private Node growBranch(Node root, int length) { //--todo: make this work, usable for fork.
        Node node = nodeManager.newNode(root);
        node.getPos().add(0, length);
        return node;

    }
    public void growBranchGroup(Group group, int length) {

    }
    public void growBranchLayer(Layer layer, int length) {

    }
    public void growLastLayer(int length){ //--todo: make cleaner, separate concerns.
        ArrayList<Group> lastLayer = nodeManager.getLastLayer().getGroupArray();
        Layer layer = nodeManager.newLayer();
        for (Group indexGroup : lastLayer) {
            Node node = nodeManager.newNode(indexGroup.getRoot());
            node.setParent(indexGroup.getRoot());

            node.getPos().add(0, length);

            Group group = nodeManager.newGroup(layer);
            Node root = nodeManager.newNode(node.getPos(), group);
            root.setParent(node);
        }
    }

    public void growAllBranches(int length) {
        Layer layer = nodeManager.getLastLayer();
        Layer newLayer = nodeManager.newLayer();
        for (Group group : layer.getGroupArray()) {
            for (Node node : group.getNodeArray()) {
                growBranch(node, length);
            }
        }
    }
    public void growAllBranchesRandom(int lowerRange, int upperRange) {
        //grow all nodes @ last layer
    }
    //---------------------//
    // Manipulate branches //
    //---------------------//
    public void rotatePlant(float degrees) {
        //rotate whole plant, based on a vector2 (ideally a node.)
    }
    public Node rotateBranch(Node node, float degrees) {
        Node parent = node.getParent();
        node.getPos().rotateAroundDeg(parent.getPos(), degrees);
        return node;
    }
    public void rotateLastLayer(float degrees){ //not for dynamic rotations
        ArrayList<Group> lastLayer = nodeManager.getLayer(nodeManager.getNumOfLayers()-1).getGroupArray();
        for (Group indexGroup : lastLayer) {
            for (Node node : indexGroup.getNodeArray()) { // --todo: for loop only for one node, make this better.
                Node parent = node.getParent();
                parent.getPos().rotateAroundDeg(parent.getParent().getPos(), degrees);
                node.setPos(parent.getPos());
            }
        }

    }
    //--------------------//
    // Composite Commands //
    //--------------------//
    public void fork (Node node, int length, int amount) {
        fork(node, length, amount, 45f, 0);
    }
    public void fork (Node node, int length, int amount, float spread) {
       fork(node, length, amount, spread, 0);
    }
    public void fork (Node node, int length, int amount, float spread, float angleOffset) {
        //grow
        //rotate
        //loop
        //set back to vertical pointing, modified by angleOffset
    }
    public void forkRandom (Node node, int length, int amount, float lowerRange, float upperRange, float angleOffset) {
        //grow
        //random rotation based on range
        //loop
        //point the branches center point towards angleOffset
    }

    public void forkLastLayer (int length, int forks, float spread) {
        ArrayList<Group> lastLayerArray = nodeManager.getLayer(nodeManager.getNumOfLayers()-1).getGroupArray();
        nodeManager.newLayer();
        for (Group indexGroup : lastLayerArray) {
            Node root = indexGroup.getRoot();
            for (int i = 0; i < forks; i++) {
                Node node = growBranch(root, length); //clone root, grow, return rootClone
                node = rotateBranch(node, spread * i);

                Node newRoot = nodeManager.newNode(node);
                Group group = nodeManager.newGroup(nodeManager.getLastLayer());
                newRoot.setGroup(group);
                group.addNode(newRoot);

            }
        }


    }


    @Override
    public ArrayList<Vector2[]> getRenderData() {
        return nodeManager.getRenderPairs();
    }

}
