package src.ast;

import java.util.LinkedList;
import src.Env;

public class ASTSequence extends AST<Void> {

  protected LinkedList<AST<Void>> asts;

  public ASTSequence (LinkedList<AST<Void>> asts) {
    this.asts = asts;
  }

  public Void eval (Env e) throws Exception {
    Env sub = e.clone();
    for(AST<Void> ast: asts) ast.eval(sub);
    return null;
  }

  public String compile () throws Exception {
    String ret = "";
    for (AST<Void> ast: asts)
      ret += ast.compile();
    return ret;
  }

}
