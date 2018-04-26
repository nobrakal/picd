package src.exceptions;

@SuppressWarnings("serial")
public class LexerException extends PrettyPrintException {

  public LexerException (String message, int line, int col) {
    super (Colors.fail("Syntax error") + " at line " + line +
           " at column " + col +
           ":\n" + Colors.warning(message), line, col);
  }

}
