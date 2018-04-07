package src;

import java.util.HashMap;
import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicInteger;

import src.exceptions.*;

public class Env {

  private final HashMap<String, AtomicInteger> csts, vars;

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
    if (vars.containsKey(id)) return vars.get(id).intValue();
    if (csts.containsKey(id)) return csts.get(id).intValue();
    throw new CannotFindSymbolException(id, line, column);
  }

  public void addVar(String id, int  value) {
    vars.put(id, new AtomicInteger(value));
  }

  public void setVar (String id, int value) throws CannotFindSymbolException {
    if (!vars.containsKey(id))
      throw new CannotFindSymbolException(id, line, column); 
    vars.get(id).set(value);
  }

  public void addConst(String id, int value) {
    csts.put(id, new AtomicInteger(value));
  }

  public Env clone () {
    return new Env(this);
  }

  public String toString () {
    return "{csts: " + csts + ",\n" +
           "vars: " + vars + "}";
  }
}
