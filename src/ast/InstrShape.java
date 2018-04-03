package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;

public class InstrShape implements Instr<Shape> {
  public final ShapeBuilder shapeb;
  public final Expr a,b,c,d;
  public final Color color;

  public InstrShape(ShapeBuilder shape, Expr a, Expr b, Expr c, Expr d, Color color){
    this.shapeb=shape;
    this.a=a;
    this.b=b;
    this.c=c;
    this.d=d;
    this.color = color;
  }

  public Shape eval(Graphics2D g){
    g.setPaint(color);
    return shapeb.eval(a.eval(),b.eval(),c.eval(),d.eval());
  }

  @FunctionalInterface
  public interface ShapeBuilder{
    public Shape eval(double a, double b, double c, double d);
  }
}
