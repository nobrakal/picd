package src.exceptions;

import src.token.Sym;
import src.token.Token;

@SuppressWarnings("serial")
public class AlreadyDefined extends ParserException {

  public AlreadyDefined (String str, int line, int column, int length) {
    super ("Already defined symbol", 
            str + " is already defined", line, column, length);
  }
}
