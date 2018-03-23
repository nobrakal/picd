package src.exceptions;

import src.token.Sym;
import src.token.Token;

public class UnexpectedSymbolException extends Exception {

  public UnexpectedSymbolException (Token symbol, Sym expected) {
    super ("\nUnexpected symbol at line " + symbol.line +
        " at column " + symbol.column +
        ": was expecting " + expected +
        " and found " + symbol);
  }

}
