package src.ast;

import src.Env;
import src.EnvCompiler;

public class ASTBoolean extends AST<Boolean> {

  private AST<Integer> expr;

  public ASTBoolean (AST<Integer> expr) {
    this.expr = expr;
  }

  public Boolean eval (Env e) throws Exception {
    return expr.eval(e) != 0;
  }

  public void compile (EnvCompiler e) throws Exception {
    expr.compile(e);
    e.code += "!= 0";
  }

}
