package src.parsers;

import src.token.*;

public LookAhead1 {

  private Token current;
  private Lexer lexer;

  public LookAhead1 (String filename) 
    throws Exception, IOException, LexerException {
    Reader reader = new Reader(new File(filename));
    lexer = new Lexer(reader);
    current = lexer.yylex();
  }

  public boolean is (Sym s) {
    return current.symbol() == s;
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

