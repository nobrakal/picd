package src.exceptions;

public class LexerException extends Exception {

  public LexerException (String message, int line, int col) {
    super ("\nSyntax error at line " + line + " column " + col + ":\n" + message);
  }

}