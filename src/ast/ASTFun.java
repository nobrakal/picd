package src.ast;

import src.Env;
import java.util.HashMap;
import java.util.ArrayList;

public class ASTFun extends AST<Void> {

  private final String id;
  public final ArrayList<String> args; 
  private final ASTSequence asts;

  public ASTFun (String id, ASTSequence asts, ArrayList<String> ids) {
    this.id   = id;
    args      = ids;
    this.asts = asts;
  }

  public Void eval (Env e) throws Exception {
    e.addFun(this.id, this);
    return null;
  }

  public void run (Env e) throws Exception{
    asts.eval(e);
  }
}
