package src.ast;

import java.awt.Graphics2D;
import java.util.LinkedList; // Implement Queue
import java.util.HashMap;

import src.exceptions.*;

@SuppressWarnings("serial")
public class AST<O> {

  protected final Instr<O> instr;
  private final HashMap<String,Integer> csts, vars;
  protected AST<?> parent, next;

  public AST(Instr<O> instr, AST<?> parent) {
    super();
    this.instr  = instr;
    this.parent = parent;

    if (parent != null)
      parent.next = this;
    
    csts = new HashMap<>();
    vars = new HashMap<>();
  }

  public void run (Graphics2D g) {
    if (instr != null) instr.eval(g);
  }

  public void eval (Graphics2D g) {
    run(g);
    if (next != null) next.eval(g);
  }

  public String toString () {
    return "{" +
      "instr:" + instr + "," +
      "next: " + next + "}";
    
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
    if (csts.containsKey(id)) return csts.get(id);
    if(parent != null) return parent.getVar(id, line, column);
    throw new CannotFindSymbolException(id, line, column);
  }

  public boolean addVar(String id, int  value){
    if(vars.containsKey(id)) return false;
    vars.put(id, value);
    return true;
  }

  public boolean setVar (String id, int value) {
    if (vars.containsKey(id)) {
      vars.put(id, value);
      return true;
    }

    if (parent == null) return false;
    return parent.setVar(id, value);
  }

  public boolean addConst(String id, int value){
    if(csts.containsKey(id)) return false;
    csts.put(id, value);
    return true;
  }
}
