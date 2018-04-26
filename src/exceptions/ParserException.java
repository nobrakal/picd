package src.exceptions;

@SuppressWarnings("serial")
abstract public class ParserException extends PrettyPrintException {

  public ParserException (String desc, String message, int line, int col) {
    super (Colors.bold(Colors.fail(desc)) + " at line " + line +
           " at column " + (col + 1) +
           ":\n" + message, line, col);
  }

}
