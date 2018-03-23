package src.token;

public class Token{

  public final Sym sym;
  public final int line, column;
  
  public Token(Sym s, int line, int column){
    this.sym    = s;
    this.line   = line;
    this.column = column;
  }
}
