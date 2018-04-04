package src;

import java.util.HashMap;
import java.awt.Graphics2D;

import src.exceptions.*;

public class Env {

  private final HashMap<String, Integer> csts, vars;

  private int line, column;

  public final Graphics2D g;

  public Env (Graphics2D g) {
    csts   = new HashMap<>();
    vars   = new HashMap<>();
    line   = 1;
    column = 1;
    this.g = g;
  }

  private Env (Env e) {
    csts   = new HashMap<>(e.csts);
    vars   = new HashMap<>(e.vars);
    line   = e.line;
    column = e.column;
    g      = e.g;
  }

  public int getVar(String id) throws CannotFindSymbolException{ 
    if(vars.containsKey(id)) return vars.get(id);
    if (csts.containsKey(id)) return csts.get(id);
    throw new CannotFindSymbolException(id, line, column);
  }

  public void addVar(String id, int  value) {
    vars.put(id, value);
  }

  public void setVar (String id, int value) throws CannotFindSymbolException {
    if (!vars.containsKey(id))
      throw new CannotFindSymbolException(id, line, column); 
    vars.put(id, value);
  }

  public void addConst(String id, int value) {
    csts.put(id, value);
  }

  public Env clone () {
    return new Env(this);
  }

  public String toString () {
    return "{csts: " + csts + ",\n" +
           "vars: " + vars + "}";
  }
}
