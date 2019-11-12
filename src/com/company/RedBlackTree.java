package com.company;

public class RedBlackTree {
    private Node root = null;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(Node node) {
        if(isEmpty()) {
            node.setColor(false);
            this.root = node;
            return;
        }
        root.insert(node);

        setRoot(node);
        while (root.getParent() != null) {
            setRoot(root.getParent());
        }
    }

    public void insert(int value) {
        Node node = new Node(value,true);
        insert(node);
    }

    public Node search(int value) {
        if (this.root == null) {
            return null;
        }
        return root.search(value);
    }

    public void remove(int value) {
        root.Delete(search(value));
    }
}
