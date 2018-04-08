package src.ast;

import src.Env;
import java.util.HashMap;

public class ASTFun extends AST<Void> {

  private final String id;
  public final String[] args; 
  private final ASTSequence asts;

  public ASTFun (String id, ASTSequence asts, String ... ids) {
    this.id=id;
    args = ids;
    this.asts=asts;
  }

  public Void eval (Env e) throws Exception {
    e.addFun(this.id,this);
    return null;
  }

  public void run (Env e) throws Exception{
    asts.eval(e);
  }
}
