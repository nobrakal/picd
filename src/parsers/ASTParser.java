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
      Instr<Shape> instrDraw = shapeParser.parse(ast);
      return new AST<Void>(new InstrDoGraphic((g)->g::draw, instrDraw), ast);
    } else if (r.is(Sym.FILL)) {
      r.eat(Sym.FILL);
      Instr<Shape> instrFill = shapeParser.parse(ast);
      return new AST<Void>(new InstrDoGraphic((g)->g::fill,instrFill), ast);
    } else if(r.are(Sym.CONST, Sym.VAR)){
      boolean cst = r.is(Sym.CONST);
      r.eat();
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      Expr value = new ParserExpr().parse(ast);
      if (cst) {
        if (!ast.addConst(id, value.eval()))
          throw new AlreadyDefined(id, r.getLine(), r.getColumn());
      } else if (!ast.addVar(id, value.eval()))
        throw new AlreadyDefined(id, r.getLine(), r.getColumn());
      return ast;
    } else if (r.is(String.class)) {
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      Expr value = parserExpr.parse(ast);
      if (!ast.setVar(id, value.eval()))
        throw new CannotFindSymbolException (id, r.getLine(), r.getColumn());
      return ast;
    } else if(r.is(Sym.IF)){
      r.eat(Sym.IF);
      Expr cond = parserExpr.parse(ast);
      r.eat(Sym.THEN);
      AST<?> ifTrue = instruction(ast);
      r.eat(Sym.ELSE);
      return new ASTCond(new InstrCond(cond),ast, ifTrue, instruction(ast));
    } else if (r.is(Sym.WHILE)) {
      r.eat(Sym.WHILE);
      Expr cond = parserExpr.parse(ast);
      r.eat(Sym.DO);
      return new ASTWhile(new InstrCond(cond), ast, instruction(ast));
    } 

    r.eat(Sym.BEGIN);
    AST<?> block = sequence(new AST<Void>(null, ast), Sym.END);
    r.eat(Sym.END);
    return block;
  }

}
