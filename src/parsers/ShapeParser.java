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
      AST<Integer> x = expr.parse();
      r.eat(Sym.COMA);
      AST<Integer> y = expr.parse();
      r.eat(Sym.COMA);
      AST<Integer> rad = expr.parse();
      r.eat(Sym.COMA);
      Color c = r.pop(Color.class).getObject();
      r.eat(Sym.RPAR);
      return new ASTShape(Ellipse2D.Double::new,
                  new ExprOp((a,b)->a-b,"-",x,rad),
                  new ExprOp((a,b) -> a-b,"-",y,rad),
                  new ExprOp((a,b)->a*b,"*", new ExprLeaf.Int(2),rad),
                  new ExprOp((a,b)->a*b,"*",new ExprLeaf.Int(2),rad),c);
    }

    r.eat(Sym.RECT);
    r.eat(Sym.LPAR);
    AST<Integer> x = expr.parse();
    r.eat(Sym.COMA);
    AST<Integer> y = expr.parse();
    r.eat(Sym.COMA);
    AST<Integer> width = expr.parse();
    r.eat(Sym.COMA);
    AST<Integer> height = expr.parse();
    r.eat(Sym.COMA);
    Color c = r.pop(Color.class).getObject();
    r.eat(Sym.RPAR);
    return new ASTShape(Rectangle2D.Double::new,x, y, width, height,c);
  }
}
