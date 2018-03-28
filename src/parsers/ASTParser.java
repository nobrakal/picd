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

  private AST<?> ast;
  private ParserExpr parserExpr;
  private ShapeParser shapeParser;

  public ASTParser () {
    this(null); 
  }

  public ASTParser(AST<?> parent){
    ast = new AST<Void>(null,parent);
    parserExpr = new ParserExpr();
    shapeParser = new ShapeParser();
  }

  public AST<?> parse(AST<?> a){
    return parse();
  }

  public AST<?> parse () {
    try{
      suiteInstructions();
      return ast;
    } catch(Exception e) {
      System.err.println(e);
      System.exit(-1);
    }
    return null;
  }

  private void suiteInstructions() throws Exception{
      while (!r.isEmpty()){
        AST<?> a = instruction();
        r.eat(Sym.SEMI);
        if(a!=null) ast.add(a);
      }
  }

  private void suiteInstructionsUntil(Sym e) throws Exception{
      while (!r.is(e)){
        AST<?> a = instruction();
        r.eat(Sym.SEMI);
        if(a!=null) ast.add(a);
      }
  }

  private AST<?> instruction () throws UnexpectedSymbolException, Exception{
    AST<?> res=null;
    if (r.is(Sym.DRAW)) {
      r.eat(Sym.DRAW);
      Tuple<Shape,Color> tuple = shapeParser.parse(ast);
      res = new AST<Void>(new InstrDraw(tuple.fst, tuple.snd),ast);
    } else if (r.is(Sym.FILL)) {
      r.eat(Sym.FILL);
      Tuple<Shape,Color> tuple = shapeParser.parse(ast);
      res = new AST<Void>(new InstrFill(tuple.fst, tuple.snd),ast);
    }
    else if(r.is(Sym.CONST)){
      r.eat(Sym.CONST);
      String id = r.pop(String.class).getObject();
      r.eat(Sym.EQ);
      int a = new ParserExpr().parse(ast);
      if(!ast.add(id,a)) throw new ConstAlreadyDefined("Const "+id+" is already defined", r.getLine(), r.getColumn());
    }
    else if(r.is(Sym.IF)){
      r.eat(Sym.IF);
      int cond = parserExpr.parse(ast);
      r.eat(Sym.THEN);
      AST<?> left = instruction();
      r.eat(Sym.ELSE);
      res = new ASTCond(new InstrCond(cond),ast,left,instruction());
    }
    else {
      r.eat(Sym.BEGIN);
      ASTParser tmp = new ASTParser(ast);
      tmp.suiteInstructionsUntil(Sym.END);
      res=tmp.ast;
      r.eat(Sym.END);
    }
    return res;
  }

  private AST<?> parseFromTo(Sym s,Sym e) throws Exception{
      r.eat(s);
      ASTParser astp = new ASTParser(ast);
      while (!r.is(e)) astp.instruction();
      r.eat(e);
      return astp.ast;
  }
}
