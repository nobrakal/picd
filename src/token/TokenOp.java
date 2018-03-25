package src.token;

import java.util.function.BiFunction;

public class TokenOp extends Token<Object> {
  public final BiFunction<Integer,Integer,Integer> fun;
  public TokenOp (BiFunction<Integer, Integer, Integer> op,
                  int line, int col) {
    super (line, col);
    this.fun=op;
  }

}
