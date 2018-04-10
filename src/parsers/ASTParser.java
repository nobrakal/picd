package src.parsers;

import java.io.IOException;
import java.io.File;
import java.util.LinkedList;
import java.util.ArrayList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;
import src.Env;

public class ASTParser extends Parser<Void> {

  private final ParserExpr parserExpr;
  private final ShapeParser shapeParser;
  private final FunParser funParser;

  public ASTParser(){
    parserExpr = new ParserExpr();
    shapeParser = new ShapeParser();
    funParser = new FunParser(this);
  }

  public ASTModule parse () throws Exception {
    return new ASTModule(sequence());
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
    else if(r.is(String.class)) return affectation();
    else if(r.is(Sym.FUN)) return fundec();
    else if (r.is(Sym.SLEEP)) return sleep();
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
    } else if(r.is(Sym.RUN)){
      r.eat(Sym.RUN);
      String id = r.pop(String.class).getObject();
      r.eat(Sym.LPAR);
      ArrayList<AST<Integer>> res = new ArrayList<>();
      while(!r.is(Sym.RPAR)){
        if(r.is(Sym.COMA)){
          r.eat(Sym.COMA);
        }
        res.add(parserExpr.parse());
      }  
      r.eat(Sym.RPAR);
      return new ASTRun(id,res);
    } else if(r.is(Sym.IMPORT)){
      r.eat(Sym.IMPORT);
      String path = r.pop(String.class).getObject();
      LookAhead1 oldr = Parser.r;
      Parser.r = new LookAhead1(path);
      ASTModule mainImported = parse();
      Parser.r = oldr;
      return mainImported;
    }
    return beginEnd();
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

  public ASTSequence beginEnd() throws Exception{
    r.eat(Sym.BEGIN);
    ASTSequence block = new ASTSequence(sequence(Sym.END));
    r.eat(Sym.END);
    return block;
  }

  private ASTFun fundec() throws Exception{
    r.eat(Sym.FUN);
    return (ASTFun)funParser.parse();
  }

  private AST<Void> sleep () throws Exception {
    r.eat(Sym.SLEEP);
    return new ASTSleep(parserExpr.parse());
  }
}
