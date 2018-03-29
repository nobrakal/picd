package src.parsers;

import java.io.*;

import java.util.function.BiFunction;

import src.exceptions.*;
import src.token.*;
import src.Lexer;

public class LookAhead1 {

  private Token<?> current;
  private Lexer lexer;

  public LookAhead1 (String filename)
    throws Exception, IOException, LexerException {
    Reader reader = new FileReader(new File(filename));
    lexer = new Lexer(reader);
    current = lexer.yylex();
  }

  public boolean is (Class<?> c) {
    return c.isInstance(current.getObject());
  }

  public boolean is (Sym s){
    return current.getObject() == s;
  }

  public boolean isToken(Class<?> tok){
    return current.getClass() == tok;
  }

  /**
   * Generic pop
   * @param c The class of the excepted object
   * @return The token containg this object
   */
  public <C> Token<C> pop (Class<C> c) 
      throws UnexpectedSymbolException, Exception {
    if (!is(c)) throw new UnexpectedSymbolException(current, c);
   
    @SuppressWarnings({"unchecked"}) //It is in fact checked
    Token<C> t = (Token<C>)current;
    current = lexer.yylex();
    return t;
  }

 public BiFunction<Integer,Integer,Integer> popOp () 
      throws UnexpectedSymbolException, Exception {
    if (!isToken(TokenOp.class)) throw new UnexpectedSymbolException(current, TokenOp.class);
    
    TokenOp t = (TokenOp)current;
    current = lexer.yylex();
    return t.fun;
  }

  public void eat(Sym s) 
      throws UnexpectedSymbolException, Exception {
    if (!is(s)) throw new UnexpectedSymbolException(current, s);
    current = lexer.yylex();
  }

  public boolean isEmpty () {
    return current == null;
  }

  public int getLine(){
    return current.line;
  }

  public int getColumn(){
    return current.column;
  }

}

