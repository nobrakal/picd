package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrDraw implements Instr<Void> {

  public final Instr<Shape> is;

  public InstrDraw(Instr<Shape> is){
    this.is=is;
  }

  public Void eval(Graphics2D g){
    g.draw(is.eval(g));
    return null;
  }
}
