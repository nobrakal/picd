package src.ast;

import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class ASTWhile extends AST<Boolean> {

  private final AST<?> loop;

  public ASTWhile (Instr<Boolean> cond, AST<?> parent, AST<?> loop) {
    super(cond, parent);

    this.loop = loop;
  }

  public void run (Graphics2D g) {
    while (!instr.eval(g)) loop.run(g);
  }

}
