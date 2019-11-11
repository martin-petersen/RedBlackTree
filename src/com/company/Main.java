package com.company;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    RedBlackTree rbt = new RedBlackTree();

//	    for(int i=1; i< 20; ++i) {
//	        rbt.insert(i);
//        }

        rbt.insert(10);
        rbt.insert(20);
        rbt.insert(30);
//        rbt.insert(15);



        JFrame frame = new JFrame("Visualizador de Red Black Tree");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        RedBlackTreeView view = new RedBlackTreeView(rbt);
        frame.add(view);
        frame.setVisible(true);
    }
}
