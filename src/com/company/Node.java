package com.company;

public class Node {
    private int value;
    private boolean color;
    private Node left;
    private Node right;
    private Node parent;

    public Node() {
    }

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, boolean color) {
        this.value = value;
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getGrandParent() {
        return parent.getParent();
    }

    public Node getSibling() {
        if (this == this.getParent().getLeft()) {
            return this.getParent().getRight();
        } else {
            return this.getParent().getLeft();
        }
    }

    public Node getUncle() {
        return this.getParent().getSibling();
    }

    public void insert(Node node) {
        if (node.getValue() < this.getValue()) {
            if (this.getLeft() == null) {
                node.setParent(this);
                this.setLeft(node);
            } else {
                this.getLeft().insert(node);
            }
        } else if (node.getValue() > this.getValue()) {
            if (this.getRight() == null) {
                node.setParent(this);
                this.setRight(node);
            } else {
                this.getRight().insert(node);
            }
        }
        insertCaseX(node);
    }

    public Node search(int value) {
        if (value == this.getValue()) {
            return this;
        }

        if (value < this.getValue()) {
            if (this.getLeft() != null) {
                return this.getLeft().search(value);
            }
        }

        if (value > this.getValue()) {
            if (this.getRight() != null) {
                return this.getRight().search(value);
            }
        }

        return null;
    }

    public void remove(int value) {

    }

    private void insertCaseX (Node node) {
        if(node.getParent() == null) {
            insertCaseOne(node);
        }
        else if(!node.getParent().isColor()) {
            insertCaseTwo(node);
        }
        else if(node.getUncle() != null && node.getUncle().isColor()) {
            insertCaseThree(node);
        } else {
            insertCaseFour1(node);
        }
    }

    private void insertCaseOne(Node node) {
        node.setColor(false);
    }

    private void insertCaseTwo(Node node) {
        return;
    }

    private void insertCaseThree(Node node) {
        node.getParent().setColor(false);
        node.getUncle().setColor(false);
        node.getGrandParent().setColor(true);
        insertCaseX(node.getGrandParent());
    }

    private void insertCaseFour1(Node node) {
        Node p = node.getParent();
        Node g = node.getGrandParent();

        if(node == p.getRight() && p == g.getLeft()) {
            rotateLeft(p);
            node = node.getLeft();
        }
        else if(node == p.getLeft() && p == g.getRight()) {
            rotateRight(p);
            node = node.getRight();
        }

        insertCaseFour2(node);
    }

    private void insertCaseFour2(Node node) {
        Node p = node.getParent();
        Node g = node.getGrandParent();

        if(node == p.getLeft()) {
            rotateRight(g);
        } else {
            rotateLeft(g);
        }

        p.setColor(false);
        g.setColor(true);
    }

    private void rotateLeft(Node node) {
        Node novo = node.getRight();
        Node p = node.getParent();
        assert novo != null;
        node.setRight(node.getLeft());
        novo.setLeft(node);
        node.setParent(novo);

        if(node.getRight() != null) {
            node.getRight().setParent(node);
        }

        if(p != null) {
            if(node == p.getLeft()) {
                p.setLeft(novo);
            }
            else if(node == p.getRight()) {
                p.setRight(novo);
            }
        }
        novo.setParent(p);
    }

    private void rotateRight(Node node) {
        Node novo = node.getLeft();
        Node p = node.getParent();
        assert novo != null;
        node.setLeft(novo.getRight());
        novo.setRight(node);
        node.setParent(novo);

        if(node.getLeft() != null) {
            node.getLeft().setParent(node);
        }

        if(p != null) {
            if(node == p.getLeft()) {
                p.setLeft(novo);
            }
            else if(node == p.getRight()) {
                p.setRight(novo);
            }
        }
        novo.setParent(p);
    }
}

/*    void ReplaceNode(Node* n, Node* child) {
        child->parent = n->parent;
        if (n == n->parent->left) {
            n->parent->left = child;
        } else {
            n->parent->right = child;
        }
    }

    void DeleteOneChild(Node* n) {
        // Precondition: n has at most one non-leaf child.
        Node* child = (n->right == nullptr) ? n->left : n->right;
        assert(child);

        ReplaceNode(n, child);
        if (n->color == BLACK) {
            if (child->color == RED) {
                child->color = BLACK;
            } else {
                DeleteCase1(child);
            }
        }
        free(n);
    }

    void DeleteCase1(Node* n) {
        if (n->parent != nullptr) {
            DeleteCase2(n);
        }
    }

    void DeleteCase2(Node* n) {
        Node* s = GetSibling(n);

        if (s->color == RED) {
            n->parent->color = RED;
            s->color = BLACK;
            if (n == n->parent->left) {
                RotateLeft(n->parent);
            } else {
                RotateRight(n->parent);
            }
        }
        DeleteCase3(n);
    }

    void DeleteCase3(Node* n) {
        Node* s = GetSibling(n);

        if ((n->parent->color == BLACK) && (s->color == BLACK) &&
                (s->left->color == BLACK) && (s->right->color == BLACK)) {
            s->color = RED;
            DeleteCase1(n->parent);
        } else {
            DeleteCase4(n);
        }
    }

    void DeleteCase4(Node* n) {
        Node* s = GetSibling(n);

        if ((n->parent->color == RED) && (s->color == BLACK) &&
                (s->left->color == BLACK) && (s->right->color == BLACK)) {
            s->color = RED;
            n->parent->color = BLACK;
        } else {
            DeleteCase5(n);
        }
    }

    void DeleteCase5(Node* n) {
        Node* s = GetSibling(n);

        // This if statement is trivial, due to case 2 (even though case 2 changed
        // the sibling to a sibling's child, the sibling's child can't be red, since
        // no red parent can have a red child).
        if (s->color == BLACK) {
            // The following statements just force the red to be on the left of the
            // left of the parent, or right of the right, so case six will rotate
            // correctly.
            if ((n == n->parent->left) && (s->right->color == BLACK) &&
                    (s->left->color == RED)) {
                // This last test is trivial too due to cases 2-4.
                s->color = RED;
                s->left->color = BLACK;
                RotateRight(s);
            } else if ((n == n->parent->right) && (s->left->color == BLACK) &&
                    (s->right->color == RED)) {
                // This last test is trivial too due to cases 2-4.
                s->color = RED;
                s->right->color = BLACK;
                RotateLeft(s);
            }
        }
        DeleteCase6(n);
    }

    void DeleteCase6(Node* n) {
        Node* s = GetSibling(n);

        s->color = n->parent->color;
        n->parent->color = BLACK;

        if (n == n->parent->left) {
            s->right->color = BLACK;
            RotateLeft(n->parent);
        } else {
            s->left->color = BLACK;
            RotateRight(n->parent);
        }
    }
*/