package src.ast;

import src.Env;
import java.util.HashMap;
import java.util.ArrayList;

public class ASTRun extends AST<Void> {

  public final String id;
  public final ArrayList<AST<Integer>> realArgs;
  private final int line, column;

  public ASTRun (String id, ArrayList<AST<Integer>> args, int line, int column) {
    this.id=id;
    this.realArgs=args;
    this.line=line;
    this.column=column;
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
}
