package src.ast;

import java.util.function.BiFunction;

public class ExprOp implements Expr{

  public final BiFunction<Integer,Integer,Integer> op;
  public final String str;
  public final Expr left, right;

  public ExprOp(BiFunction<Integer,Integer,Integer> op, String str, Expr left, Expr right){
    this.op=op;
    this.left=left;
    this.right=right;
    this.str= str;
  }

  public int eval(){
    return this.op.apply(left.eval(),right.eval());
  }

  public String toString(){
    return left+str+right;  
  }
}
