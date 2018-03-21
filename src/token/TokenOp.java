package src.token;

import java.util.function.BiFunction;

public class TokenOp extends Token {

  public final BiFunction<Integer, Integer, Integer> op;

  public TokenOp (BiFunction<Integer, Integer, Integer> op) {
    super (Sym.OP);
    this.op = op;
  }

}
