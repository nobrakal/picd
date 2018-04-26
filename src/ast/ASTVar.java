package src.ast;

import src.Env;

public class ASTVar {

  public static abstract class ASTV extends AST<Void>{
    protected final String id;
    protected final AST<Integer> value;

    public ASTV(String id, AST<Integer> value) {
      this.id = id;
      this.value = value;
    }

    public abstract Void eval (Env e) throws Exception;
  }

  public static class ConstDeclaration extends ASTV {

    public ConstDeclaration(String id, AST<Integer> value) {
     super(id,value); 
    }
    
    public Void eval (Env e) throws Exception {
      e.addConst(id, value.eval(e));
      return null;
    }

    public String compile () throws Exception {
      return "final int " + id + " = " + value.compile() + ";";
    }


  }

  public static class VarDeclaration extends ASTV {

    public VarDeclaration(String id, AST<Integer> value) {
     super(id,value); 
    }

    public Void eval (Env e) throws Exception {
      e.addVar(id, value.eval(e));
      return null;
    }

    public String compile () throws Exception {
      return "int " + id + " = " + value.compile() + ";";
    }

  }

  public static class VarAffectation extends ASTV {

    private final int line, column;
    
    public VarAffectation(String id, AST<Integer> value, int line, int column) {
      super(id,value);
      this.line   = line;
      this.column = column;
    }

    public Void eval (Env e) throws Exception {
      e.setVar(id, value.eval(e), line, column);
      return null;
    }

    public String compile () throws Exception {
      return id + " = " + value.compile() + ";";
    }
  }
}

