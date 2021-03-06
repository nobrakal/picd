package src.exceptions;

@SuppressWarnings("serial")
abstract public class ParserException extends PrettyPrintException {

  public ParserException (String desc, String message, int line, int col, int length) {
    super (Colors.fail(desc) + " at line " + line +
           " at column " + (col + 1) +
           ":\n" + Colors.fail(message), line, col, length);
  }

}
