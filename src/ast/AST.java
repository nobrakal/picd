package src.ast;

import java.awt.Graphics2D;
import java.util.LinkedList; // Implement Queue
import java.util.HashMap;

import src.exceptions.*;

@SuppressWarnings("serial")
public class AST<O> extends LinkedList<AST<?>>{
  public final Instr<O> instr;
  private final HashMap<String,Integer> vars = new HashMap<String,Integer>();
  private final AST<?> parent;

  public AST(Instr<O> instr, AST<?> par){
    super();
    this.instr = instr;
    this.parent = par;
  }

  public AST(Instr<O> instr, AST<?> par, AST<?>...asts){
    for(AST<?> a : asts) add(a);
    this.instr = instr;
    this.parent = par;
  }

  public void run (Graphics2D g) {
    if (instr != null) instr.eval(g);
    for (AST<?> a: this) a.run(g);
  }

  public String toString(){
    String res = instr + "->\n";
    for(AST<?> a: this)
      res += a;
    return res;
  }

  /**
   * Eval the entier AST
   * @param g Le Graphics2D
   */
  public void eval(Graphics2D g){
    if (instr != null) instr.eval(g);
    for(AST<?> a: this) a.eval(g);
  }

  /**
   * Get the variable, if it exists in the HashMap of variables
   * @param id the Id
   * @param line the current line
   * @param column the current column
   * @return The variable
   */
  public int getVar(String id, int line, int column) throws CannotFindSymbolException{ 
    if(vars.containsKey(id)) return vars.get(id);
    if(parent != null) return parent.getVar(id, line, column);
    throw new CannotFindSymbolException(id, line, column);
  }

  public boolean add(String id, int a){
    if(vars.containsKey(id)) return false;
    vars.put(id,a);
    return true;
  }
}
