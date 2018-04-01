package src.ast;

import java.util.function.BiFunction;

public class ExprOp implements Expr{

  public final BiFunction<Integer,Integer,Integer> op;
  public final Expr left, right;

  public ExprOp(BiFunction<Integer,Integer,Integer> op, Expr left, Expr right){
    this.op=op;
    this.left=left;
    this.right=right;
  }

  public int eval(){
    return this.op.apply(left.eval(),right.eval());
  }
}
