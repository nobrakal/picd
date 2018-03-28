package src.exceptions;

@SuppressWarnings("serial")
abstract public class ParserException extends Exception {

  public ParserException (String message, int line, int col) {
    super ("\nParser error at line " + line +
           " at column " + col +
           ":\n" + message);
  }

}
