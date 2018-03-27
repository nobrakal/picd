package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.utils.Tuple;

public class ASTParser extends Parser<AST> {

  private AST ast;
  private ParserExpr parserExpr;
  private ShapeParser shapeParser;

  public ASTParser () {
    ast = new AST(null,null);
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
      r.eat(Sym.DRAW);
      Tuple<Shape,Color> tuple = shapeParser.parse();
      ast.add(new AST(new InstrDraw(tuple.fst, tuple.snd),ast));
    } else if (r.is(Sym.FILL)) {
      r.eat(Sym.FILL);
      Tuple<Shape,Color> tuple = shapeParser.parse();
      ast.add(new AST(new InstrFill(tuple.fst, tuple.snd),ast));
    }
    else if(r.is(Sym.CONST)){
      r.eat(Sym.CONST);
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      int a = new ParserExpr().parse();
    }
    else {
      r.eat(Sym.BEGIN);
      while (!r.is(Sym.END)) instruction();
      r.eat(Sym.END);
    }
    r.eat(Sym.SEMI);
  }
}
