package src.parsers;

import java.util.LinkedList;
import java.awt.Shape;

import src.token.*;

public class ShapeParser extends Parser<LinkedList<Shape>> {

  private LinkedList<Shape> shapes;

  public ShapeParser (String filename)
      throws Exception, IOException, LexerException {
      shapes = new LinkedList<>();
  }

  public LinkedList<Shape> parse () {
    while (!r.isEmpty()) instruction();
    return shapes;
  }

  private void instruction () {
    if (r.is(Sym.BEGIN)) while (!r.is(Sym.END)) instruction();   
    else if (r.is(Sym.DRAWC) || r.is(Sym.FILLC)) {
      if (r.is(Sym.DRAWC)) r.pop(Sym.DRAWC);
      else r.pop(Sym.FILLC);
      r.pop(Sym.LPAR);
      Integer x = new ParserExpr().parse();
      r.pop(Sym.COMA);
      Integer y = new ParserExpr().parse();
      r.pop(Sym.COMA);
      Integer r = new ParserExpr().parse();
      r.pop(Sym.COMA);
      Color color = ((TokenColor)r.pop(Sym.Color)).color;
      r.pop(Sym.RPAR);
      shapes.add(new Circle(x, y, r));
    } else if (r.is(Sym.DRAWR)) {
      
    }
  }

}
