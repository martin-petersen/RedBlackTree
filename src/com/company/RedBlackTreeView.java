package com.company;

import javax.swing.*;
import java.awt.*;

public class RedBlackTreeView extends JComponent {
    
    private RedBlackTree arvore = null;
    private int nodeSize = 30;
    private int offset = 60;

    public RedBlackTreeView(RedBlackTree arvore) {
        this.arvore = arvore;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        if(this.arvore == null) {
            return;
        }
        drawNode(
                graphics,
                this.arvore.getRoot(),
                getWidth()/2,
                0,
                0
        );
    }

    private void drawNode(Graphics graphics, Node node, int x, int y, int level) {
        graphics.drawOval(x, y, nodeSize, nodeSize);
        graphics.drawString(
                String.valueOf(node.getValue())  +  String.valueOf(node.isColor()),
                x + 8,
                y + 20
        );

        if (node.getLeft() != null) {
            int childX = x - offset;
            int childY = y + offset;

            graphics.drawLine(
                    x + nodeSize / 2,
                    y + nodeSize,
                    childX + nodeSize / 2,
                    childY
            );
            drawNode(graphics, node.getLeft(), childX, childY, level + 1);
        }

        if (node.getRight() != null) {
            int childX = x + offset;
            int childY = y + offset;

            graphics.drawLine(
                    x + nodeSize / 2,
                    y + nodeSize,
                    childX + nodeSize / 2,
                    childY
            );
            drawNode(graphics, node.getRight(), childX, childY, level + 1);
        }
    }
}
