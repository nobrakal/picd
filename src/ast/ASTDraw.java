package src.ast;

import java.awt.Shape;
import src.Env;
import src.EnvCompiler;

public class ASTDraw extends AST<Void> {

  public final ASTShape is;

  public ASTDraw(ASTShape is){
    this.is = is;
  }

  public Void eval(Env e) throws Exception {
    e.g.draw(is.eval(e));
    return null;
  }

  public void compile (EnvCompiler e) throws Exception {
    e.code += ASTFill.getColor(is.color)+"g.draw";
    is.compile(e);
  }
}
