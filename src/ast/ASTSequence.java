package src.ast;

import java.util.LinkedList;
import src.Env;
import src.EnvCompiler;

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

  public void compile (EnvCompiler e) throws Exception {
    e.code += "{";
    for (AST<Void> ast: asts)
      ast.compile(e);
    e.code += "}";
  }

}
