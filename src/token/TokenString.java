package src.token;

public class TokenString extends Token{
  public final String str;

  public TokenString(Sym sy, String s, int line, int col){
    super(sy, line, col);
    this.str = s;
  }
}
