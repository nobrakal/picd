package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class ASTParser extends Parser<AST> {

  private AST ast;
  private ParserExpr parserExpr;
  private ShapeParser shapeParser;

  public ASTParser () {
    ast = new AST(null);
    parserExpr = new ParserExpr();
    shapeParser = new ShapeParser();
  }

  public AST parse () {
    try{
      while (!r.isEmpty()) instruction();
      return ast;
    } catch(Exception e) {
      System.err.println(e);
      System.exit(-1);
    }
    return null;
  }

  private void instruction () throws UnexpectedSymbolException, Exception{
    if (r.is(Sym.DRAW)) {
      r.pop(Sym.DRAW);
      Shape s = shapeParser.parse();
      ast.add(new AST(new InstrDraw(s, Color.black)));
    } else if (r.is(Sym.FILL)) {
      r.pop(Sym.FILL);
      Shape s = shapeParser.parse();
      ast.add(new AST(new InstrFill(s, Color.black)));
    } else {
      r.pop(Sym.BEGIN);
      while (!r.is(Sym.END)) instruction();
      r.pop(Sym.END);
    }

    r.pop(Sym.SEMI);
  }

}
