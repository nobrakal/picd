package src.ast;

import src.Env;

public class ASTBoolean extends AST<Boolean> {

  private AST<Integer> expr;

  public ASTBoolean (AST<Integer> expr) {
    this.expr = expr;
  }

  public Boolean eval (Env e) throws Exception {
    return expr.eval(e) != 0;
  }

  public String compile () throws Exception {
    return expr.compile() + "!= 0";
  }

}
