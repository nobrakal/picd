package src.parsers;

import java.io.IOException;

import src.token.*;
import src.exceptions.*;
import src.ast.*;

public abstract class Parser<T> {
  
  protected static LookAhead1 r;

  public abstract AST<T> parse() 
      throws Exception, UnexpectedSymbolException, IOException;

  public static void init (String filename) 
      throws Exception, IOException, LexerException {
    r = new LookAhead1(filename);
  }

  public AST<T> parse(Sym s)  throws Exception, UnexpectedSymbolException, IOException{
    AST<T> ast = parse();
    r.eat(s);
    return ast;
  }
}

