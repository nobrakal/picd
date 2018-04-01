package src.ast;

public class ExprLeaf implements Expr{
  public final int val;

  public ExprLeaf(int val){
    this.val=val;
  }

  public int eval(){
    return this.val;
  }
}
