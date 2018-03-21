package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;

public abstract class Parser<T> {

  protected static LookAhead1 r;

  public abstract T parse() 
      throws Exception, UnexpectedSymbolException, IOException;

  public static void init () 
      throws Exception, IOException, LexerException {
    r = new LookAhead1(filename);
  }

}

