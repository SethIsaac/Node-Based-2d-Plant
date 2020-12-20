package pkg.playground.Node;

import com.badlogic.gdx.math.Vector2;

import java.util.UUID;

public class Node {
    private Vector2 pos;
    private Group group;
    private Node parent;
    final private String uniqueID = UUID.randomUUID().toString();

    //group
    public Node(Node node) {
        this.pos = new Vector2(node.getPos());
        this.group = node.getGroup();
        this.parent = node;
    }
    public Node(float x, float y, Group group) {
        this.pos = new Vector2(x, y);
        this.group = group;
    }
    public Node(Vector2 nodeVector, Group group) {
        this.pos = nodeVector;
        this.group = group;
    }


    //-----------getters--------------//
    public Vector2 getPos() {
        return pos;
    }
    public String getUniqueID() {
        return uniqueID;
    }

    public Node getParent() {
        return parent;
    }
    public Group getGroup() {
        return group;
    }

    public Layer getLayer() {
        return group.getLayer();
    }

    //------------setters--------------//
    public void setPos(float x, float y) {
        this.pos.x = x;
        this.pos.y = y;
    }
    public void setPos(Vector2 vector) {
        this.pos.set(vector);
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
