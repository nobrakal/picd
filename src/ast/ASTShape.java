package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;
import src.Env;

public class ASTShape extends AST<Shape> {
  public final ShapeBuilder shapeb;
  public final Expr a,b,c,d;
  public final Color color;

  public ASTShape(ShapeBuilder shape, Expr a, Expr b, Expr c, Expr d, Color color){
    this.shapeb = shape;
    this.a      = a;
    this.b      = b;
    this.c      = c;
    this.d      = d;
    this.color  = color;
  }

  public Shape eval(Env e) throws Exception {
    e.g.setPaint(color);
    return shapeb.eval(a.eval(e), b.eval(e), c.eval(e), d.eval(e));
  }

  @FunctionalInterface
  public interface ShapeBuilder{
    public Shape eval(double a, double b, double c, double d);
  }
}
