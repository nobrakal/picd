package src.ast;

import java.util.function.BiFunction;

import src.Env;

public class ExprOp extends AST<Integer> {

  public final BiFunction<Integer,Integer,Integer> op;
  public final String str;
  public final AST<Integer> left, right;

  public ExprOp(BiFunction<Integer,Integer,Integer> op, String str, AST<Integer> left, AST<Integer> right){
    this.op    = op;
    this.left  = left;
    this.right = right;
    this.str   = str;
  }

  public Integer eval(Env e) throws Exception {
    return this.op.apply(left.eval(e), right.eval(e));
  }

  public String toString(){
    return left+str+right;  
  }

  public String compile () {
    return toString();
  }

}
