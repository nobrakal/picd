package src.token;

public class Token<O>{

  public final int line, column;
  protected O object;

  public Token(int line, int column){
    this.line   = line;
    this.column = column;
  }

  public Token(O obj,int line,int column){
    this(line, column);
    this.object = obj;
  }

  public String toString () {
    return object.toString();
  }

  public O getObject(){
    return object;
  }
}
