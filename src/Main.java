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

  String filename;

  public MyCanvas(String fname) {
    filename = fname;
    ShapeParser parser = new ShapeParser(filename);

  }

  @Override
  public void paintComponent(Graphics g) {
    if (g instanceof Graphics2D)
    {
      Graphics2D g2d = (Graphics2D)g;

      // A compléter.
      // Appelez ici votre analyseur et interpréteur, en leur fournissant
      // l'objet g2d de type Graphics2D. Ils pourront ainsi appeler les fonctions
      // g2d.drawCircle, g2d.setColor, etc...
      //
      // Par exemple :
      //
  	  // File input = new File(filename);
      // Reader reader = new FileReader(input);
      // Lexer lexer = new Lexer(reader);
      // LookAhead1 look = new LookAhead1(lexer);
      // AST ast = parser.progNonTerm();
      // ast.exec(g2d); 
    }
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

