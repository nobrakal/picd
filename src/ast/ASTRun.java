package src.ast;

import src.Env;
import java.util.HashMap;
import java.util.ArrayList;

public class ASTRun extends AST<Void> {

  public final String id;
  public final ArrayList<AST<Integer>> realArgs; 

  public ASTRun (String id, ArrayList<AST<Integer>> args) {
    this.id=id;
    this.realArgs=args;
  }

  public Void eval (Env e) throws Exception {
    ASTFun astf = e.getFun(id);
    Env newenv = new Env(e.g);
    for(int i=0; i<realArgs.size(); i++){
      e.addVar(astf.args.get(i), realArgs.get(i).eval(e));
    }
    astf.run(newenv);
    return null;
  }
}
