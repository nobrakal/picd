package src.parsers;

import java.io.*;

import src.exceptions.*;
import src.token.*;
import src.Lexer;

public class LookAhead1 {

  private Token current;
  private Lexer lexer;

  public LookAhead1 (String filename)
    throws Exception, IOException, LexerException {
    Reader reader = new FileReader(new File(filename));
    lexer = new Lexer(reader);
    current = lexer.yylex();
  }

  public boolean is (Sym s) {
    return current.sym == s;
  }

  public boolean are (Sym ... ss){
    for(Sym s: ss){
      if(current.sym ==s)
        return true;
    }
    return false;
  }

  public Token pop (Sym s) 
      throws UnexpectedSymbolException {
    if (!is(s)) throw new UnexpectedSymbolException(s, current, lexer.yyline(), lexer.yycolumn());
    
    Token t = current;
    current = lexer.yylex();
    return t;
  }

  public boolean isEmpty () {
    return current == null;
  }

}

