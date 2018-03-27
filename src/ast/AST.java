package src.ast;

import java.awt.Graphics2D;
import java.util.LinkedList; // Implement Queue
import java.util.HashMap;

@SuppressWarnings("serial")
public class AST extends LinkedList<AST>{
  public final Instr instr;
  private final HashMap<String,Integer> vars = new HashMap<String,Integer>();
  private final AST parent;

  public AST(Instr instr, AST par){
    super();
    this.instr = instr;
    this.parent = par;
  }

  public AST(Instr instr, AST par, AST...asts){
    for(AST a : asts) add(a);
    this.instr = instr;
    this.parent = par;
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

  public boolean hasVar(String id){
    if(vars.containsKey(id)) return true;
    if(parent != null) return parent.hasVar(id);
    return false;
  }
}
