package src.ast;

import java.awt.Shape;
import java.awt.Graphics2D;
import java.awt.Color;
import src.Env;
import src.EnvCompiler;

public class ASTShape extends AST<Shape> {
  public final ShapeBuilder shapeb;
  public final AST<Integer> a,b,c,d;
  public final Color color;

  public ASTShape(ShapeBuilder shape, AST<Integer> a, AST<Integer> b, AST<Integer> c, AST<Integer> d, Color color){
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

  public interface ShapeBuilder{
    public Shape eval(double a, double b, double c, double d);

    public void compile (EnvCompiler e, AST<Integer> a, AST<Integer> b, AST<Integer> c, AST<Integer> d) throws Exception;
  }

  public void compile (EnvCompiler e) throws Exception {
    shapeb.compile(e, a, b, c, d);
  }

}
