package src.ast;

import java.awt.Graphics2D;
import java.util.LinkedList; // Implement Queue

@SuppressWarnings("serial")
public class AST extends LinkedList<AST>{
  public final Instr instr;

  public AST(Instr instr){
    super();
    this.instr = instr;
  }

  public AST(Instr instr, AST...asts){
    for(AST a : asts) add(a);
    this.instr = instr;
  }

  public void run (Graphics2D g) {
    if (instr != null) instr.eval(g);
    for (AST a: this) a.run(g);
  }

  public String toString(){
    String res = instr + "->\n";
    for(AST a: this)
      res += a;
    return res;
  }

  /**
   * Eval the entier AST
   * @param g Le Graphics2D
   */
  public void eval(Graphics2D g){
    if (instr != null) instr.eval(g);
    for(AST a: this) a.eval(g);
  }
}
