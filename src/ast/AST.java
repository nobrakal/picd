package src.ast;

import src.exceptions.*;
import src.Env;

@SuppressWarnings("serial")
public abstract class AST<O> {

  public abstract O eval (Env e) throws Exception;

  public abstract String compile () throws Exception;

}
