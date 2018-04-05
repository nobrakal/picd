package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.utils.Tuple;

public class ASTParser extends Parser<Void> {

  private ParserExpr parserExpr;
  private ShapeParser shapeParser;

  public ASTParser(){
    parserExpr = new ParserExpr();
    shapeParser = new ShapeParser();
  }

  public AST<Void> parse () throws Exception {
    return new ASTSequence(sequence());
  }

  private LinkedList<AST<Void>> sequence () throws Exception {
    LinkedList<AST<Void>> seq = new LinkedList<>();
    while (!r.isEmpty()) {
      seq.add(instruction());
      r.eat(Sym.SEMI);
    }

    return seq;
  }

  private LinkedList<AST<Void>> sequence(Sym e) throws Exception {
    LinkedList<AST<Void>> seq = new LinkedList<>();
    while (!r.isEmpty() && !r.is(e)) {
      seq.add(instruction());
      r.eat(Sym.SEMI);
    }

    return seq;
  }

  private AST<Void> instruction () throws UnexpectedSymbolException, Exception {
    if (r.are(Sym.DRAW, Sym.FILL)) return shape();
    else if(r.are(Sym.CONST, Sym.VAR)) return declaration();
    else if (r.is(String.class)) return affectation();
    else if(r.is(Sym.IF)){
      r.eat(Sym.IF);
      AST<Integer> cond = parserExpr.parse(Sym.THEN);
      AST<Void> ifTrue = instruction();
      r.eat(Sym.ELSE);
      return new ASTCond(new ASTBoolean(cond), ifTrue, instruction());
    } else if (r.is(Sym.WHILE)) {
      r.eat(Sym.WHILE);
      AST<Integer> cond = parserExpr.parse(Sym.DO);
      return new ASTWhile(new ASTBoolean(cond), instruction());
    } 

    r.eat(Sym.BEGIN);
    ASTSequence block = new ASTSequence(sequence(Sym.END));
    r.eat(Sym.END);
    return block;
  }

  private AST<Void> shape () throws Exception {
    // assert Sym.DRAW or Sym.FILL
    boolean draw = r.is(Sym.DRAW);
    r.eat();
    AST<Shape> shp = shapeParser.parse();
    return draw ? new ASTDraw(shp) : new ASTFill(shp);
  }

  private AST<Void> declaration () throws Exception {
    // assert Sym.VAR or Sym.CONST
    boolean cst = r.is(Sym.CONST);
    r.eat();
    String id = r.pop(String.class).getObject();
    r.eat(Sym.EQ);
    AST<Integer> value = parserExpr.parse();
    return cst ? new ASTVar.ConstDeclaration(id, value) : 
                 new ASTVar.VarDeclaration(id, value); 
  }

  private AST<Void> affectation () throws Exception {
    String id = r.pop(String.class).getObject();
    r.eat(Sym.EQ);
    AST<Integer> value = parserExpr.parse();
    return new ASTVar.VarAffectation(id, value);
  }

}
