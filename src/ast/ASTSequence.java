package src.ast;

import java.util.LinkedList;
import src.Env;

public class ASTSequence extends AST<Void> {

  private LinkedList<AST<Void>> asts;

  public ASTSequence (LinkedList<AST<Void>> asts) {
    this.asts = asts;
  }

  public Void eval (Env e) throws Exception {
    Env sub = e.clone();
    System.out.println(asts);
    for(AST<Void> ast: asts) ast.eval(sub);
    return null;
  }

}
