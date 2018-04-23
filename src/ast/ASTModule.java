package src.ast;

import java.util.LinkedList;
import src.Env;

public class ASTModule extends ASTSequence {

  public ASTModule (LinkedList<AST<Void>> asts) {
    super(asts);
  }

  //DO NOT CLONE THE ENV
  public Void eval (Env e) throws Exception {
    for(AST<Void> ast: asts) ast.eval(e);
    return null;
  }

}
