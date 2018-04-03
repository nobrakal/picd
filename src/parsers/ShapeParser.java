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
import src.utils.Tuple;

public class ShapeParser extends Parser<Instr<Shape>> {

  private ParserExpr expr;

  public ShapeParser () {
    expr = new ParserExpr();
  }

  public Instr<Shape> parse (AST<?> ast) 
      throws Exception, UnexpectedSymbolException {
    if (r.is(Sym.CIRCLE)) {
      r.eat(Sym.CIRCLE);
      r.eat(Sym.LPAR);
      Expr x = expr.parse(ast);
      r.eat(Sym.COMA);
      Expr y = expr.parse(ast);
      r.eat(Sym.COMA);
      Expr rad = expr.parse(ast);
      r.eat(Sym.COMA);
      Color c = r.pop(Color.class).getObject();
      r.eat(Sym.RPAR);
      return new InstrShape(Ellipse2D.Double::new,new ExprOp((a,b)->a-b,"-",x,rad), new ExprOp((a,b) -> a-b,"-",y,rad),new ExprOp((a,b)->a*b,"*", new ExprLeaf(2),rad),new ExprOp((a,b)->a*b,"*",new ExprLeaf(2),rad),c);
    }

    r.eat(Sym.RECT);
    r.eat(Sym.LPAR);
    Expr x = expr.parse(ast);
    r.eat(Sym.COMA);
    Expr y = expr.parse(ast);
    r.eat(Sym.COMA);
    Expr width = expr.parse(ast);
    r.eat(Sym.COMA);
    Expr height = expr.parse(ast);
    r.eat(Sym.COMA);
    Color c = r.pop(Color.class).getObject();
    r.eat(Sym.RPAR);
    return new InstrShape(Rectangle2D.Double::new,x, y, width, height,c);
  }
}
