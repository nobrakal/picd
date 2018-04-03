package src.token;

import java.util.function.BiFunction;

public class TokenOp extends Token<Object> {
  public final BiFunction<Integer,Integer,Integer> fun;
  public final String str;
  public TokenOp (BiFunction<Integer, Integer, Integer> op, String str,
                  int line, int col) {
    super (line, col);
    this.fun=op;
    this.str=str;
  }

}
