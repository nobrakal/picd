package src.parsers;

import java.io.IOException;
import java.util.ArrayList;
import java.awt.Shape;
import java.awt.Color;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public class ModuleParser extends ASTParser<Void> {

  public ASTSequence parse () throws Exception {
    return new ASTSequence(sequence());
  }

  protected AST<Void> instruction ()
      throws UnexpectedSymbolException, Exception {
    try {
      return super.instruction();
    } catch (Exception e) {}
    
    if(r.is(Sym.FUN)) return fundec();
    else if(r.is(Sym.IMPORT)){
      r.eat(Sym.IMPORT);
      String path = r.pop(String.class).getObject();
      LookAhead1 oldr = Parser.r;
      Parser.r = new LookAhead1(path);
      ASTSequence mainImported = parse();
      Parser.r = oldr;
      return new ASTFun("main/" + path, mainImported, null);
    }
    return beginEnd();
  }

  private ASTFun fundec() throws Exception{
    r.eat(Sym.FUN);
    return (ASTFun)funParser.parse();
  }
}
