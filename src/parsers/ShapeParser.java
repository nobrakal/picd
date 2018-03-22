package src.parsers;

import java.util.LinkedList;
import java.awt.Shape;

import src.token.*;

public class ShapeParser extends Parser<LinkedList<Shape>> {

  public ShapeParser (String filename)
      throws Exception, IOException, LexerException {
  }

  public LinkedList<Shape> parse () {
    while (!r.isEmpty()) instruction();
  }

  private void instruction () {
    if(r.are(Sym.DRAWC, Sym.DRAWR)){

    }
    else if(r.are(Sym.FILLC, Sym.FILLR)){

    }
    r.pop(Sym.BEGIN);
    instruction();
    r.pop(Sym.END);
  }

}
