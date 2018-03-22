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

  Iterable<Shape> shapes;

  public MyCanvas(String fname) {
    shapes = new ShapeParser(filename).parse();
  }

  @Override
  public void paintComponent(Graphics g) {
    drawAll(g, shapes);
  }

  /**
   * Draw list of shapes on a Graphics2D
   * @param g The Graphics2D
   * @param list The Iterable object
   */
  private void drawAll(Graphics2D g, Iterable<Shape> list){
    for(Shape s: list){
      g.draw(s);
    }
  }
}

