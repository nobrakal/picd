package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.EnvCompiler;

public class ShapeParser extends Parser<Shape> {

  private ParserExpr expr;

  public ShapeParser () {
    expr = new ParserExpr();
  }

  public AST<Shape> parse () 
      throws Exception, UnexpectedSymbolException {
    if (r.is(Sym.CIRCLE)) {
      r.eat(Sym.CIRCLE);
      r.eat(Sym.LPAR);
      AST<Integer> x = expr.parse(Sym.COMA);
      AST<Integer> y = expr.parse(Sym.COMA);
      AST<Integer> rad = expr.parse(Sym.COMA);
      Color c = r.pop(Color.class).getObject();
      r.eat(Sym.RPAR);
      return new ASTShape(new Ellipse2DBuilder(),
                  new ExprOp((a,b)->a-b,"-",x,rad),
                  new ExprOp((a,b)->a-b,"-",y,rad),
                  new ExprOp((a,b)->a*b,"*", new ExprLeaf.Int(2),rad),
                  new ExprOp((a,b)->a*b,"*",new ExprLeaf.Int(2),rad),c);
    }

    r.eat(Sym.RECT);
    r.eat(Sym.LPAR);
    AST<Integer> x = expr.parse(Sym.COMA);
    AST<Integer> y = expr.parse(Sym.COMA);
    AST<Integer> width = expr.parse(Sym.COMA);
    AST<Integer> height = expr.parse(Sym.COMA);
    Color c = r.pop(Color.class).getObject();
    r.eat(Sym.RPAR);
    return new ASTShape(new Rectangle2DBuilder(), x, y, width, height,c);
  }

  private class Ellipse2DBuilder implements ASTShape.ShapeBuilder {

    public Shape eval(double a, double b, double c, double d) {
      return new Ellipse2D.Double(a, b, c, d);
    }

    public void compile (EnvCompiler e, AST<Integer> a, AST<Integer> b, AST<Integer> c, AST<Integer> d) throws Exception {
      e.code += "Oval(";
      a.compile(e);
      e.code += ",";
      b.compile(e);
      e.code += ",";
      c.compile(e);
      e.code += ",";
      d.compile(e);
      e.code += ");";
    }
  }

  private class Rectangle2DBuilder implements ASTShape.ShapeBuilder {

    public Shape eval (double a, double b, double c, double d) {
      return new Rectangle2D.Double(a, b, c, d);
    }

    public void compile (EnvCompiler e, AST<Integer> a, AST<Integer> b, AST<Integer> c, AST<Integer> d) throws Exception {
      e.code += "Rect(";
      a.compile(e);
      e.code += ",";
      b.compile(e);
      e.code += ",";
      c.compile(e);
      e.code += ",";
      d.compile(e);
      e.code += ");";
    }
  }
}
