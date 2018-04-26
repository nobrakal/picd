package src;

import java.util.HashSet;
import java.util.HashMap;

import src.exceptions.*;
import src.ast.ASTFun;

public class EnvCompiler {

  private final HashSet<String> vars;
  public final HashMap<String,ASTFun> funs;
  public String code;

  public EnvCompiler (HashSet<String> vars, HashMap<String,ASTFun> funs) {
    code = "";
    this.vars   = vars;
    this.funs   = funs;
  }

  public EnvCompiler(){
    this(new HashSet<>(), new HashMap<>());
  }

  private EnvCompiler (EnvCompiler e) {
    this(new HashSet<>(e.vars), new HashMap<>(e.funs));
  }

  public static EnvCompiler mkPartialEnvCompiler(EnvCompiler e){
    return new EnvCompiler(new HashSet<>(), new HashMap<>(e.funs));
  }

  public void addVar(String id) {
    vars.add(id);
  }

  public boolean isVar (String id) {
    return vars.contains(id);
  }

  public void addFun(String id, ASTFun value) {
    funs.put(id, value);
  }

  public boolean isFunc (String id) {
    return funs.containsKey(id);
  }

  public ASTFun getFun(String id, int line, int column)
      throws CannotFindSymbolException{ 
    if (funs.containsKey(id)) return funs.get(id);
    throw new CannotFindSymbolException(id, line, column);
  }

  public EnvCompiler clone () {
    return new EnvCompiler(this);
  }

}
