package src;

import java.util.HashMap;
import java.awt.Graphics2D;
import java.util.concurrent.atomic.AtomicInteger;

import src.exceptions.*;
import src.ast.ASTFun;

public class Env{

  public final Graphics2D g;

  private final HashMap<String, AtomicInteger> csts, vars;
  private final HashMap<String,ASTFun> funs;

  public Env (Graphics2D g, HashMap<String, AtomicInteger> vars, HashMap<String, AtomicInteger> csts, HashMap<String,ASTFun> funs) {
    this.csts   = csts;
    this.vars   = vars;
    this.funs   = funs;
    this.g=g;
  }

  public Env(Graphics2D g){
    this(g, new HashMap<>(), new HashMap<>(), new HashMap<>());
  }

  private Env (Env e) {
    this(e.g,new HashMap<>(e.vars),new HashMap<>(e.csts),new HashMap<>(e.funs));
  }

  public static Env mkPartialEnv(Env e){
    return new Env(e.g, new HashMap<>(), new HashMap<>(), new HashMap<>(e.funs));
  }

  public int getVar(String id, int line, int column)
      throws CannotFindSymbolException { 
    if (vars.containsKey(id)) return vars.get(id).intValue();
    if (csts.containsKey(id)) return csts.get(id).intValue();
    throw new CannotFindSymbolException(id, line, column, id.length());
  }

  public void addVar(String id, int  value) {
    vars.put(id, new AtomicInteger(value));
  }

  public void setVar (String id, int value, int line, int column)
      throws CannotFindSymbolException {
    if (!vars.containsKey(id))
      throw new CannotFindSymbolException(id, line, column, id.length()); 
    vars.get(id).set(value);
  }

  public void addConst(String id, int value) {
    csts.put(id, new AtomicInteger(value));
  }

  public void addFun(String id, ASTFun value) {
    funs.put(id, value);
  }

  public ASTFun getFun(String id, int line, int column)
      throws CannotFindSymbolException{ 
    if (funs.containsKey(id)) return funs.get(id);
    throw new CannotFindSymbolException(id, line, column, id.length());
  }

  public Env clone () {
    return new Env(this);
  }

  public String toString () {
    return "{csts: " + csts + ",\n" +
           "vars: " + vars + "}";
  }
}
