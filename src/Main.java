package src;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.parsers.*;

public class Main {

    private static void initAndShow(String filename) {
        JFrame frame = new JFrame("ADS4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
        frame.getContentPane().setPreferredSize(new Dimension(800,600));
        frame.getContentPane().add(new MyCanvas(filename));

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initAndShow(args[0]);
            }
        });
    }
}

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

  public MyCanvas(String fname) {
    try{
      Parser.init(fname);
    }
    catch(Exception e){
      System.out.println(e);
    }
    ASTParser parser = new ASTParser();

  }

  @Override
  public void paintComponent(Graphics g) {
  }
}

