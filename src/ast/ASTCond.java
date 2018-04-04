package src.ast;

import src.Env;

@SuppressWarnings("serial")
public class ASTCond extends AST<Void>{

  private final AST<Boolean> cond;
  private final AST<Void> ifTrue, otherwise;

  public ASTCond(AST<Boolean> cond, AST<Void> ifTrue, AST<Void> otherwise) {
    this.cond      = cond;
    this.ifTrue    = ifTrue;
    this.otherwise = otherwise;
  }

  public Void eval (Env e) throws Exception {
    if (cond.eval(e)) ifTrue.eval(e);
    else otherwise.eval(e);
    return null;
  }
}
