package src.ast;

import src.Env;

public class ExprLeaf {

  public static class Int extends Expr {
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

  public static class Id extends Expr {

    public final String id;

    public Id (String id) {
      this.id = id;
    }

    public Integer eval (Env e) throws Exception {
      return e.getVar(id);
    }

    public String toString () {
      return id;
    }

  }
}
