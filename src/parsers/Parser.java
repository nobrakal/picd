package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public abstract class Parser<T> {
  
  protected static LookAhead1 r;

  public abstract T parse(AST current) 
      throws Exception, UnexpectedSymbolException, IOException;

  public static void init (String filename) 
      throws Exception, IOException, LexerException {
    r = new LookAhead1(filename);
  }

}

