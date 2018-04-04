package src.ast;

import java.awt.Shape;
import src.Env;

public class ASTDraw extends AST<Void> {

  public final AST<Shape> is;

  public ASTDraw(AST<Shape> is){
    this.is = is;
  }

  public Void eval(Env e) throws Exception {
    e.g.draw(is.eval(e));
    return null;
  }
}
