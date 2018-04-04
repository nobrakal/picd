package src.ast;

import src.Env;

public class ASTBoolean extends AST<Boolean> {

  private Expr expr;

  public ASTBoolean (Expr expr) {
    this.expr = expr;
  }

  public Boolean eval (Env e) throws Exception {
    return expr.eval(e) != 0;
  }

}
