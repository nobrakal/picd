package src.ast;

import src.exceptions.*;

import src.Env;
import src.EnvCompiler;

public abstract class ExprLeaf extends AST<Integer> {

  public static class Int extends ExprLeaf {
    public final int val;

    public Int(int val){
      this.val = val;
    }

    public Integer eval(Env e) throws Exception {
      return this.val;
    }

    public String toString () {
      return val + "";
    }

    public void compile(EnvCompiler e) throws Exception {
      e.code += val + "";
    }

  }

  public static class Id extends ExprLeaf {

    public final String id;
    public final int line,column;

    public Id (String id, int line, int column) {
      this.id     = id;
      this.line   = line;
      this.column = column;
    }

    public Integer eval (Env e) throws Exception {
      return e.getVar(id, line, column);
    }

    public String toString () {
      return id;
    }

    public void compile (EnvCompiler e) throws Exception {
      if (!e.isVar(id))
        throw new CannotFindSymbolException(id, line, column);
      e.code += id;
    }

  }

}
