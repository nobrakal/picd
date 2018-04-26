package src.exceptions;

@SuppressWarnings("serial")
public class LexerException extends PrettyPrintException {

  public LexerException (String message, int line, int col) {
    super ("Syntax error at line " + line +
           " at column " + col +
           ":\n" + message, line, col);
  }

}
