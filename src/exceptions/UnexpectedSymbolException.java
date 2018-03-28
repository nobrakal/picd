package src.exceptions;

import src.token.Sym;
import src.token.Token;

@SuppressWarnings("serial")
public class UnexpectedSymbolException extends ParserException {

  public UnexpectedSymbolException (Token<?> symbol, Class<?> expected) {
    super ("\nUnexpected symbol: was expecting " + expected +
        " and found " + symbol, symbol.line, symbol.column);
  }

  public UnexpectedSymbolException (Token<?> symbol, Sym expected) {
    super ("\nUnexpected symbol: was expecting " + expected +
        " and found " + symbol, symbol.line, symbol.column);
  }

}
