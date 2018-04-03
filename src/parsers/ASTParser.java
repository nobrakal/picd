package src.parsers;

import java.io.IOException;
import java.util.LinkedList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.utils.Tuple;

public class ASTParser extends Parser<AST<?>> {

  private ParserExpr parserExpr;
  private ShapeParser shapeParser;

  public ASTParser () {
    this(null); 
  }

  public ASTParser(AST<?> parent){
    parserExpr = new ParserExpr();
    shapeParser = new ShapeParser();
  }

  public AST<?> parse (AST<?> ast) throws Exception {
    sequence(ast);
    return ast;
  }

  private AST<?> sequence (AST<?> ast) throws Exception {
      if (r.isEmpty()) return ast;

      AST<?> next = instruction(ast);
      r.eat(Sym.SEMI);
      return sequence(next);
  }

  private AST<?> sequence(AST<?> ast, Sym e) throws Exception {
    if (r.isEmpty() || r.is(e)) return ast;

    AST<?> next = instruction(ast);
    r.eat(Sym.SEMI);
    return sequence(next, e);
  }

  private AST<?> instruction (AST<?> ast) throws UnexpectedSymbolException, Exception{
    System.out.println("line " + r.getLine() + ": " + ast); 
    if (r.is(Sym.DRAW)) {
      r.eat(Sym.DRAW);
      Tuple<Shape,Color> tuple = shapeParser.parse(ast);
      return new AST<Void>(new InstrDraw(tuple.fst, tuple.snd), ast);
    } else if (r.is(Sym.FILL)) {
      r.eat(Sym.FILL);
      Tuple<Shape,Color> tuple = shapeParser.parse(ast);
      return new AST<Void>(new InstrFill(tuple.fst, tuple.snd), ast);
    } else if(r.are(Sym.CONST, Sym.VAR)){
      boolean cst = r.is(Sym.CONST);
      r.eat();
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      int value = new ParserExpr().parse(ast); // TODO: make ast for vars
      if (cst) {
        if (!ast.addConst(id, value))
          throw new AlreadyDefined(id, r.getLine(), r.getColumn());
      } else if (!ast.addVar(id, value))
        throw new AlreadyDefined(id, r.getLine(), r.getColumn());
      return ast;
    } else if (r.is(String.class)) {
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      Integer value = parserExpr.parse(ast);
      if (!ast.setVar(id, value))
        throw new CannotFindSymbolException (id, r.getLine(), r.getColumn());
      return ast;
    } else if(r.is(Sym.IF)){
      r.eat(Sym.IF);
      int cond = parserExpr.parse(ast);
      r.eat(Sym.THEN);
      AST<?> ifTrue = instruction(null);
      r.eat(Sym.ELSE);
      return new ASTCond(new InstrCond(cond),ast, ifTrue, instruction(null));
    } else if (r.is(Sym.WHILE)) {
      r.eat(Sym.WHILE);
      int cond = parserExpr.parse(ast);
      r.eat(Sym.DO);
      return new ASTWhile(new InstrCond(cond), ast, instruction(null));
    } 

    r.eat(Sym.BEGIN);
    AST<Void> block = new AST<Void>(null, null, ast);
    sequence(block, Sym.END);
    r.eat(Sym.END);
    return block;
  }

}
