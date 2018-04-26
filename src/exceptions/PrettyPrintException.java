package src.exceptions;

import java.io.*;

import src.parsers.Parser;

public class PrettyPrintException extends Exception {

  public PrettyPrintException (String message, int line, int col) {
    super(message+"\n   "+getLine(line)+"\n   "+genColString(col));
  }

  private static String getLine (int lineCount) {
    String line = "";

    try {
      BufferedReader buff = new BufferedReader(new FileReader(new File(Parser.getCurrentFile())));

      for (int i = 1; (line = buff.readLine()) != null && i < lineCount; i++);
      return line;
    } catch (IOException e) {
      // assert never happend
      e.printStackTrace();
    } finally {
      return line;
    }
  }

  private static String genColString (int col) {
    String line = "";
    for (int i = 0; i < col; i++)
      line += " ";
    return line + Colors.fail("^");
  }

}