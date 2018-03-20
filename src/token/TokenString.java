package src.token;

public class TokenString extends Token{
  public final String str;

  public TokenString(Sym sy, String s){
    super(sy);
    this.str=s;
  }
}
