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

  private int line, column;

  public Env (Graphics2D g, HashMap<String, AtomicInteger> vars, HashMap<String, AtomicInteger> csts, HashMap<String,ASTFun> funs, int line, int column) {
    this.csts   = csts;
    this.vars   = vars;
    this.funs   = funs;
    this.line   = line;
    this.column = column;
    this.g=g;
  }

  public Env(Graphics2D g){
    this(g, new HashMap<>(), new HashMap<>(), new HashMap<>(),1,1 );
  }

  private Env (Env e) {
    this(e.g,new HashMap<>(e.csts),new HashMap<>(e.vars),new HashMap<>(e.funs), e.line,e.column);
  }

  public static Env mkPartialEnv(Env e){
    return new Env(e.g, new HashMap<>(), new HashMap<>(), new HashMap<>(e.funs), e.line, e.column);
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

  public void addFun(String id, ASTFun value) {
    funs.put(id, value);
  }

  public ASTFun getFun(String id) throws CannotFindSymbolException{ 
    if (funs.containsKey(id)) return funs.get(id);
    throw new CannotFindSymbolException(id, line, column);
  }

  public Env clone () {
    return new Env(this);
  }

  public String toString () {
    return "{csts: " + csts + ",\n" +
           "vars: " + vars + "}";
  }
}
