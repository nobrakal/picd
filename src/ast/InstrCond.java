package src.ast;

import java.awt.Graphics2D;

public class InstrCond implements Instr<Boolean>{
  public final Expr test;

  public InstrCond(Expr test){
    this.test = test;
  }

  public Boolean eval(Graphics2D g){
    return test.eval() != 0;
  }
}
