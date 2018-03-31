package src.ast;

import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class ASTCond extends AST<Boolean>{

  private final AST<?> ifTrue, otherwise;

  public ASTCond(Instr<Boolean> instr, AST<?> parent, AST<?> ifTrue, AST<?> otherwise) {
    super(instr, parent);

    this.ifTrue    = ifTrue;
    this.otherwise = otherwise;
  }

  public void run (Graphics2D g) {
    if (instr.eval(g)) ifTrue.run(g);
    else otherwise.run(g);
  }
}
