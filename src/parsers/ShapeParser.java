package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Shape;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class ShapeParser extends Parser<Shape> {

  private ParserExpr expr;

  public ShapeParser () {
    expr = new ParserExpr();
  }

  public Shape parse () 
      throws Exception, UnexpectedSymbolException {
    if (r.is(Sym.CIRCLE)) {
      r.pop(Sym.CIRCLE);
      r.pop(Sym.LPAR);
      int x = expr.parse();
      r.pop(Sym.COMA);
      int y = expr.parse();
      r.pop(Sym.COMA);
      int rad = expr.parse();
      r.pop(Sym.COMA);
      r.pop(Sym.COLOR);
      r.pop(Sym.RPAR);
      return new Ellipse2D.Double(x, y, rad, rad);
    }

    r.pop(Sym.RECT);
    r.pop(Sym.LPAR);
    int x = expr.parse();
    r.pop(Sym.COMA);
    int y = expr.parse();
    r.pop(Sym.COMA);
    int width = expr.parse();
    r.pop(Sym.COMA);
    int height = expr.parse();
    r.pop(Sym.COMA);
    r.pop(Sym.COLOR);
    r.pop(Sym.RPAR);
    return new Rectangle2D.Double(x, y, width, height);
  }

}
