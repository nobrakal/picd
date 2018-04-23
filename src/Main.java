package src;

import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import src.parsers.*;
import src.ast.AST;

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
        if(args.length != 1){
            System.out.println("Please provide a single file to parse");
            System.exit(-1);
        }

        javax.swing.SwingUtilities.invokeLater(() -> initAndShow(args[0]));
    }
}

@SuppressWarnings("serial")
class MyCanvas extends JComponent {

  private AST<?> ast;

  public MyCanvas(String fname) {
    ast = null;

    try{
      Parser.init(fname);
      ASTParser parser = new ASTParser();
      ast = parser.parse();
    } catch(Exception e) {
      System.out.println(e);
      // e.printStackTrace();
      System.exit(1);
    }
  }

  @Override
  public void paintComponent(Graphics g) {
    try {
      ast.eval(new Env((Graphics2D)g));
    } catch (Exception e) {
      System.err.println("Error on execution: " + e);
      System.exit(1);
    }
  }
}

