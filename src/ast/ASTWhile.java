package src.ast;

import src.Env;
import src.EnvCompiler;

@SuppressWarnings("serial")
public class ASTWhile extends AST<Void> {

  private final AST<Boolean> cond;
  private final AST<Void> loop;

  public ASTWhile (AST<Boolean> cond, AST<Void> loop) {
    this.cond = cond;
    this.loop = loop;
  }

  public Void eval (Env e) throws Exception {
    while (cond.eval(e))
      loop.eval(e);
    return null;
  }

  public void compile (EnvCompiler e) throws Exception {
    e.code += "while (";
    cond.compile(e);
    e.code += ") {";
    loop.compile(e);
    e.code += "}";
  }

}
