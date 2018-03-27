package src.exceptions;

import src.token.Sym;
import src.token.Token;

@SuppressWarnings("serial")
public class CannotFindSymbolException extends Exception {

  public CannotFindSymbolException (String str) {
    super ("\nCannot find symbol "+str);
  }
}
