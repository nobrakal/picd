package src.ast;

import src.Env;

public class ASTVar {

  public static class ConstDeclaration extends AST<Void> {
    
    private final String id;
    private final Expr value;

    public ConstDeclaration(String id, Expr value) {
      this.id = id;
      this.value = value;
    }

    public Void eval (Env e) throws Exception {
      e.addConst(id, value.eval(e));
      return null;
    }

  }

  public static class VarDeclaration extends AST<Void> {
  
    private final String id;
    private final Expr value;

    public VarDeclaration (String id, Expr value) {
      this.id = id;
      this.value = value;
    }

    public Void eval (Env e) throws Exception {
      e.addVar(id, value.eval(e));
      return null;
    }

  }

  public static class VarAffectation extends AST<Void> {
  
    private final String id;
    private final Expr value;

    public VarAffectation (String id, Expr value) {
      this.id = id;
      this.value = value;
    }

    public Void eval (Env e) throws Exception {
      e.setVar(id, value.eval(e));
      return null;
    }
  }
}

