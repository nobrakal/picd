package src.ast;

import java.util.ArrayList;
import java.util.HashMap;

import src.Env;
import src.EnvCompiler;
import src.exceptions.*;

public class ASTRun extends AST<Void> {

  public final String id;
  public final ArrayList<AST<Integer>> realArgs;
  private final int line, column;

  public ASTRun (String id, ArrayList<AST<Integer>> args, int line, int column) {
    this.id       = id;
    this.realArgs = args;
    this.line     = line;
    this.column   = column;
  }

  public Void eval (Env e) throws Exception {
    ASTFun astf = e.getFun(id, line, column);
    Env newenv = Env.mkPartialEnv(e);
    for(int i=0; i<realArgs.size(); i++){
      newenv.addVar(astf.args.get(i), realArgs.get(i).eval(e));
    }
    astf.run(newenv);
    return null;
  }

  public void compile (EnvCompiler e) throws Exception {
    if (!e.isFunc(id))
      throw new CannotFindSymbolException(id, line, column);
    e.code += id + "(g" + ((realArgs.size() != 0)?",":"");
    for (int i = 0; i < realArgs.size(); i++) {
      realArgs.get(i).compile(e);
      e.code += (i == realArgs.size() - 1 ? "" : ",");
    }
    
    e.code += ");";
  }
}
