package src.ast;

import java.awt.Graphics2D;
import java.util.LinkedList; // Implement Queue
import java.util.HashMap;

import src.exceptions.*;

@SuppressWarnings("serial")
public class ASTCond extends AST<Boolean>{
  public ASTCond(Instr<Boolean> instr, AST<?> parent, AST<?> left, AST<?> right){
    super(instr, parent, left, right);
  }

  /**
   * Eval the entier AST
   * @param g Le Graphics2D
   */
  public void eval(Graphics2D g){
    if (instr.eval(g)) this.get(0).eval(g);
    else this.get(1).eval(g);
  }
}
