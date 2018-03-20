package src.token;

public class TokenInt extends Token {
  public final int num;

  public TokenInt(int n){
    super(Sym.INT);
    this.num = n;
  }
}
