package src.ast;

import java.awt.Shape;
import src.Env;

public class ASTFill extends AST<Void> {

  public final AST<Shape> is;
 
  public ASTFill(AST<Shape> is){
    this.is = is;
  }

  public Void eval(Env e) throws Exception{
    e.g.fill(is.eval(e));
    return null;
  }
}
