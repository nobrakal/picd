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
      while (!r.isEmpty()){
        AST<?> a = instruction();
        if(a!=null) ast.add(a);
      }
      return ast;
    } catch(Exception e) {
      System.err.println(e);
      System.exit(-1);
    }
    return null;
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
      res = parseTo(Sym.END);
    }
    r.eat(Sym.SEMI);
    return res;
  }

  private AST<?> parseTo(Sym s) throws Exception{
      ASTParser astp = new ASTParser(ast);
      while (!r.is(s)) astp.instruction();
      r.eat(Sym.END);
      return astp.ast;
  }
}
