package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    RedBlackTree rbt = new RedBlackTree();

	    for(int i = 1; i < 21; ++i) {
	        rbt.insert(i);
        }

        JFrame frame = new JFrame("Visualizador de Red Black Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        RedBlackTreeView view = new RedBlackTreeView(rbt);
        frame.add(view);
        frame.setVisible(true);
    }
}
