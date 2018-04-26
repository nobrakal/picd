package src.ast;

import src.Env;

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

  }

  public static class Id extends ExprLeaf {

    public final String id;
    public final int line,column;

    public Id (String id, int line, int column) {
      this.id = id;
      this.line=line;
      this.column=column;
    }

    public Integer eval (Env e) throws Exception {
      return e.getVar(id, line, column);
    }

    public String toString () {
      return id;
    }

  }

  public String compile () {
    return toString();
  }
}
