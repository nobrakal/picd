package src.token;

public class TokenInt extends Token {
  public final int num;

  public TokenInt(int n, int line, int col){
    super(Sym.INT, line, col);
    this.num = n;
  }
}
