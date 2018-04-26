package src.ast;

import src.exceptions.*;
import src.Env;
import src.EnvCompiler;

public class ASTVar {

  public static abstract class ASTV extends AST<Void>{
    protected final String id;
    protected final AST<Integer> value;
    protected final int line, column;

    public ASTV(String id, AST<Integer> value, int line, int column) {
      this.id     = id;
      this.value  = value;
      this.line   = line;
      this.column = column;
    }

    public abstract Void eval (Env e) throws Exception;
  }

  public static class ConstDeclaration extends ASTV {

    public ConstDeclaration(String id, AST<Integer> value, int line, int col) {
     super(id,value, line, col);
    }
    
    public Void eval (Env e) throws Exception {
      e.addConst(id, value.eval(e));
      return null;
    }

    public void compile (EnvCompiler e) throws Exception {
      e.code += "final int " + id + " = ";
      value.compile(e);
      e.code += ";";
    }


  }

  public static class VarDeclaration extends ASTV {

    public VarDeclaration(String id, AST<Integer> value, int line, int col) {
     super(id,value, line, col);
    }

    public Void eval (Env e) throws Exception {
      e.addVar(id, value.eval(e));
      return null;
    }

    public void compile (EnvCompiler e) throws Exception {
      e.code += "int " + id + " = ";
      value.compile(e);
      e.code += ";";
      e.addVar(id);
    }

  }

  public static class VarAffectation extends ASTV {

    public VarAffectation(String id, AST<Integer> value, int line, int column) {
      super(id,value, line, column);
    }

    public Void eval (Env e) throws Exception {
      e.setVar(id, value.eval(e), line, column);
      return null;
    }

    public void compile (EnvCompiler e) throws Exception {
      if (!e.isVar(id)) throw new CannotFindSymbolException(id, line, column);
      e.code += id + " = ";
      value.compile(e);
      e.code += ";";
    }
  }
}

