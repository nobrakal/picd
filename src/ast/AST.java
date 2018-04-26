package src.ast;

import src.exceptions.*;
import src.Env;
import src.EnvCompiler;

@SuppressWarnings("serial")
public abstract class AST<O> {

  public abstract O eval (Env e) throws Exception;

  public abstract void compile (EnvCompiler e) throws Exception;

}
