package pkg.playground.Node;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NodeManager {
    private HashMap<Integer, Layer> nodeLayerMap;
    private boolean isCashed;
    private ArrayList<Vector2[]> renderCashe;
    public NodeManager () {
        nodeLayerMap = new HashMap<>();
    }
    //---------- New ----------// --todo: Make separate objects for New, Get, Set. Use the manager as a clean interface.
    public Layer newLayer () {
        Layer layer = new Layer();
        nodeLayerMap.put(nodeLayerMap.size(), layer);
        isCashed = false;
        return layer;
    }
    public Group newGroup (Layer layer) {
        Group group = new Group(layer);
        layer.getGroupArray().add(group);
        isCashed = false;
        return group;
    }
    public Node newNode (Vector2 vector, Group group) {
        Node node = new Node(new Vector2(vector), group);
        group.addNode(node);


        isCashed = false;
        return node;
    }
    public Node newNode (Node root) {
        Node node = new Node(root);
        root.getGroup().addNode(node);


        isCashed = false;
        return node;
    }
    //---------- Get ----------//
    public Layer getLayer (int index) {
        return nodeLayerMap.get(index);
    }
    public Group getGroup (int layerIndex, int groupIndex) {
        return nodeLayerMap.get(layerIndex).getGroupArray().get(groupIndex);
    }
    public Node getNode (int layerIndex, int groupIndex, int nodeIndex) {
        return nodeLayerMap.get(layerIndex).getGroupArray().get(groupIndex).getNode(nodeIndex);
    }
    //Get node from UID. --todo: Fix this to be faster.
    public Node getNode (String UID) {
        for (Map.Entry<Integer, Layer> entry : nodeLayerMap.entrySet()) {
            for (Group group : entry.getValue().getGroupArray()) {
                for (Node node : group.getNodeArray()){
                    if (node.getUniqueID().equals(UID)) {
                        return node;
                    }
                }
            }
        }
        System.out.println("getNode in NodeManager returned NULL. Panic!");
        return null;
    }
    //---Specific Utility---//
    public int getNumOfLayers(){
        return nodeLayerMap.size();
    }
    public int getLastLayerIndex() {
        return nodeLayerMap.size() -1;
    }
    public Layer getLastLayer() {
        return getLayer(nodeLayerMap.size() -1);
    }
    public ArrayList<Node> getAllNodes() {
        ArrayList<Node> nodeList = new ArrayList<>();

        //add Logic

        return nodeList;
    }
    public ArrayList<Node> getAllLayerNodes(int index) {
        ArrayList<Node> nodeList = new ArrayList<>();

        //add Logic

        return nodeList;
    }

    public ArrayList<Node> getLastLayerNodes () {
        ArrayList<Node> nodeList = new ArrayList<>();
        for (Group group : getLastLayer().getGroupArray()) {
            nodeList.addAll(group.getNodeArray());
        }
        return nodeList;
    }
    //Best way to get hashtable for UI? --todo: Fix this to be faster.
    public HashMap<String, Node> getHashMapData () {
        HashMap<String, Node> nodeHashMap = new HashMap<>();
        ArrayList<Node> nodeArrayList = getAllNodes();
        for (Node node : nodeArrayList) {
            nodeHashMap.put(node.getUniqueID(), node);
        }
        return nodeHashMap;
    }

    //Get each group, [Root, Node] for each node --todo: Fix this to be faster.
    public ArrayList<Vector2[]> getRenderPairs() {
        if (isCashed) return renderCashe;
        ArrayList<Vector2[]> renderPairs = new ArrayList<>();

        for (Map.Entry<Integer, Layer> entry : nodeLayerMap.entrySet()) {
            for (Group group : entry.getValue().getGroupArray()) {
                for (int i = 1; i < group.size(); i++) {
                    Vector2[] pair = {group.getRoot().getPos(), group.getNode(i).getPos()};
                    renderPairs.add(pair);
                }
            }
        }
        renderCashe = renderPairs;
        isCashed = true;
        return renderPairs;
    }
}
