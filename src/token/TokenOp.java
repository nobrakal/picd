package src.token;

import java.util.function.BiFunction;

public class TokenOp extends Token {

  public final BiFunction<Integer, Integer, Integer> op;

  public TokenOp (BiFunction<Integer, Integer, Integer> op,
                  int line, int col) {
    super (Sym.OP, line, col);
    this.op = op;
  }

}
