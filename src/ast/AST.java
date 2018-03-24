package src.ast;

import java.util.LinkedList; // Implement Queue
import java.awt.Graphics2D;

@SuppressWarnings("serial")
public class AST extends LinkedList<AST>{
  public final Instr instr;

  public AST(Instr instr){
    super();
    this.instr=instr;
  }

  public AST(Instr instr, AST...asts){
    for(AST a : asts) add(a);
    this.instr=instr;
  }

  public String toString(){
    String res=instr + "->\n";
    for(AST a: this){
        res+=a;
    }
    return res;
  }

  /**
   * Eval the entier AST
   * @param g Le Graphics2D
   */
  public void eval(Graphics2D g){
    instr.eval(g);
    for(AST a: this){
        a.eval(g);
    }
  }
}
