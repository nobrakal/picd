package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrFill extends InstrShape {

  public InstrFill(Shape shape, Color c){
    super(shape, c);
  }

  public Void eval(Graphics2D g){
    super.eval(g);
    g.fill(shape);
    return null;
  }
}
