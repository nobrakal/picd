package src.ast;

import java.awt.Shape;
import src.Env;

public class ASTDraw extends AST<Void> {

  public final ASTShape is;

  public ASTDraw(ASTShape is){
    this.is = is;
  }

  public Void eval(Env e) throws Exception {
    e.g.draw(is.eval(e));
    return null;
  }

  public String compile () throws Exception {
    return ASTFill.getColor(is.color)+
           "g.draw" + is.compile();
  }
}
