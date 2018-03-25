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

public class ShapeParser extends Parser<Tuple<Shape,Color>> {

  private ParserExpr expr;

  public ShapeParser () {
    expr = new ParserExpr();
  }

  public Tuple<Shape,Color> parse () 
      throws Exception, UnexpectedSymbolException {
    if (r.is(Sym.CIRCLE)) {
      r.eat(Sym.CIRCLE);
      r.eat(Sym.LPAR);
      int x = expr.parse();
      r.eat(Sym.COMA);
      int y = expr.parse();
      r.eat(Sym.COMA);
      int rad = expr.parse();
      r.eat(Sym.COMA);
      Color c = r.pop(Color.class).getObject();
      r.eat(Sym.RPAR);
      return new Tuple<Shape,Color>(new Ellipse2D.Double(x, y, rad, rad),c);
    }

    r.eat(Sym.RECT);
    r.eat(Sym.LPAR);
    int x = expr.parse();
    r.eat(Sym.COMA);
    int y = expr.parse();
    r.eat(Sym.COMA);
    int width = expr.parse();
    r.eat(Sym.COMA);
    int height = expr.parse();
    r.eat(Sym.COMA);
    Color c = r.pop(Color.class).getObject();
    r.eat(Sym.RPAR);
    return new Tuple<Shape,Color>(new Rectangle2D.Double(x, y, width, height),c);
  }
}
