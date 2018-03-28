package src.exceptions;

import src.token.Sym;
import src.token.Token;

@SuppressWarnings("serial")
public class ConstAlreadyDefined extends ParserException {

  public ConstAlreadyDefined (String str, int line, int column) {
    super ("Const already defined symbol "+str, line, column);
  }
}
