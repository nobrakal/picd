package src.exceptions;

import src.token.Sym;
import src.token.Token;

@SuppressWarnings("serial")
public class CannotFindSymbolException extends ParserException {

  public CannotFindSymbolException (String str, int line, int column) {
    super ("Unknow symbol",
           "Could not find symbol " + str, line, column, str.length());
  }
}
