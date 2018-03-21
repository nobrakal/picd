package src.exceptions;

public class UnexpectedSymbolException extends LexerException {

  public UnexpectedSymbolException (Sym symbol, Sym expected, int line, int col) {
    super ("\nUnexpected symbol at line " + line + " at column " + col + ": was expecting " + expected + " and found " + symbol, line, col);
  }

}
