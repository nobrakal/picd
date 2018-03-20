package src.Token;

public class TokenInt extends Token{
  public final int num;

  public TokenInt(Sym s, int n){
    super(s);
    this.num=n;
  }
}
